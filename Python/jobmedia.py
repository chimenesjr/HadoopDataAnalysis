from pyspark.sql import SparkSession
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType,StructField, StringType, IntegerType 
from pyspark.sql.types import ArrayType, DoubleType, BooleanType, DateType
from pyspark.sql.functions import col,array_contains

class jobmediaclass:

    def __init__(self, fileData):
        self.file = fileData

    def start(self):
        spark = SparkSession.builder.appName("SimpleApp").getOrCreate()
        rdd = spark.sparkContext.textFile(self.file)
        print(rdd.count())
        print("test")