from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\")
dfRaw.show(20,False)