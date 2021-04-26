from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

dfRawWp = spark.read.parquet("C:\\work\\project\\test\\KMS\\encrypted_parquet")
dfRawWp.show(20,False)