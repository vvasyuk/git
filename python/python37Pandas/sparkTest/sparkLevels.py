from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\levels\\part-00000-b61b6840-c775-45f0-ba00-dd56923a08dd.c000.snappy.parquet")
# dfRaw.show(20,False)

# wdap
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\latest\\raw_datapoint_levels")
# dfRaw.show(20,False)

# dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\latest\\raw_datapoint_levels")
# dfRaw.show(20,False)

# master
dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\latest\\part-00000-d1fb2fb9-d4ed-4fa8-ac9a-fc5f08f64646-c000.snappy.parquet")
dfRaw.filter(col('proj_id').like("%TWVA00001%")).show(20,False)