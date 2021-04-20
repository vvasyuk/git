from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\gl_revenue\\").cache()
dfRaw.printSchema()
dfRaw.show(20,False)
# print(dfRaw.count())


# master
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\gl_revenue\\").cache()
# dfRaw.show(20,False)
# print(dfRaw.count())

# transform
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\transform\\gl_revenue\\").cache()
# dfRaw.show(20,False)
# print(dfRaw.count())