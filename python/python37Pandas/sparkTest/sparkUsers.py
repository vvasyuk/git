from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\users\\")
dfRaw.show(20,False)

# master
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\users\\")
# dfRaw.show(20,False)