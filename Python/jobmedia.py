from pyspark.sql import SparkSession
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType,StructField, StringType, IntegerType 
from pyspark.sql.types import ArrayType, DoubleType, BooleanType, DateType
from pyspark.sql.functions import col,array_contains

class jobmediaclass:

    def __init__(self, dataFile):
        self.dataFile = dataFile

    def start(self):
        spark = SparkSession.builder.appName("SimpleApp").getOrCreate()
        rdd = spark.sparkContext.textFile(self.dataFile).repartition(1)

        line = rdd.map(lambda x: x[1:-1].split("\",\""))
        #['01/01/2010', '5 Braemor Drive, Churchtown, Co.Dublin', '', 'Dublin', '343,000.00', 'No', 'No', 'Second-Hand Dwelling house /Apartment', '']
        
        groupedByCity = line.map(lambda x: (x[0][-4:] + ":" + x[3], x[4].replace(",","") + ":1"))
        #('2010:Dublin', '343000.00:1')
        
        sum = groupedByCity.reduceByKey(lambda a, b: str(float(a.split(":")[0])+float(b.split(":")[0])) + ":" + str(int(a.split(":")[1])+int(b.split(":")[1])))
        # ('2011:Dublin', '1825609529.210002:5904')

        result = sum.map(lambda x: (int(x[0].split(":")[0]), x[0].split(":")[1], float(x[1].split(":")[0])/int(x[1].split(":")[1])))
        #('2010', 'Dublin', 332967.37502378575)

        result.saveAsTextFile("file:///usr/local/jobmedia")

        rdd2010 = result.sortBy(lambda x: x[1]).filter(lambda x: x[0] == 2010).collect()
        rdd2021 = result.sortBy(lambda x: x[1]).filter(lambda x: x[0] == 2021).collect()
        # ('2021', 'Dublin', 531246.3120730022)

        avg = []
        i = 0
        while i < len(rdd2010):
            diff = rdd2021[i][2] - rdd2010[i][2]
            curr = (rdd2010[i][0], rdd2010[i][1], diff)

            avg.append(curr)

            i += 1
        
        finalResult = spark.sparkContext.parallelize(avg)
        finalResult.saveAsTextFile("file:///usr/local/final")