from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\vendor_hours\\part-00000-06716f6e-9d43-46eb-933a-f97d8c0cebfd.c000.snappy.parquet").cache()
dfRaw.show(20,False)