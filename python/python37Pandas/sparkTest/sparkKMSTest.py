from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# kms 1
#df = spark.read.parquet("c:\\work\\project\\test\\KMS\\1\\")
#df.show(20,False)

# kms 2
#df2 = spark.read.parquet("c:\\work\\project\\test\\KMS\\2\\")
#df2.show(20,False)


# kms 5
df5 = spark.read.parquet("C:\\work\\project\\test\\KMS\\5")
df5.show(20,False)
