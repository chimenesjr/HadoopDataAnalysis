from pyspark.sql import SparkSession
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType,StructField, StringType, IntegerType 
from pyspark.sql.types import ArrayType, DoubleType, BooleanType, DateType
from pyspark.sql.functions import col,array_contains

class jobgapclass:

    def __init__(self, dataFile):
        self.dataFile = dataFile

    def start(self):
        spark = SparkSession.builder.appName("SimpleApp").getOrCreate()
        rdd = spark.sparkContext.textFile(self.dataFile).repartition(1)

        line = rdd.map(lambda x: x[1:-1].split("\",\""))
        #['01/01/2010', '5 Braemor Drive, Churchtown, Co.Dublin', '', 'Dublin', '343,000.00', 'No', 'No', 'Second-Hand Dwelling house /Apartment', '']

        groupedByCity = line.map(lambda x: (x[3], x[4].replace(",","")))
        # [('Dublin', '343000.00')]

        min = groupedByCity.reduceByKey(lambda a, b: float(a) if float(a) < float(b) else float(b))

        max = groupedByCity.reduceByKey(lambda a, b: float(a) if float(a) > float(b) else float(b))

        i = 0
        gap = []

        while i < len(max):
            currGap = max[i] - min[i]

            gap.append(currGap)

            i += 1

        finalResult = spark.sparkContext.parallelize(gap)

        header = [('City', 'Min', 'Max', 'Gap')]
        schema = spark.sparkContext.parallelize(header)
        final = schema.union(finalResult).repartition(1)
        
        finalResultLines = final.map(lambda line: str(line).replace("'", "")[1:-1])
        finalResultLines.saveAsTextFile("file:///usr/local/gap")