from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

dfLevels = spark.read.option("header",True).csv("C:\\work\\project\\test\\levels_new\\LEVELS_FRESH_EXTRACT 05132021.csv")
dfLevels.orderBy(col("ENGAGEMENT_ID")).show(100,False)