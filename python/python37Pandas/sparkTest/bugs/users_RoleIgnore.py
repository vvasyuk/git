from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

dfIngest = spark.read.parquet("C:\\work\\project\\bugs\\users_role_ignore\\ingest")
dfIngest.filter(col('wdap_role') == '3').show(20,False)

# df = spark.read.parquet("C:\\work\\project\\bugs\\users_role_ignore")
# df.filter(col('dp_wdap_role') == '3').show(20,False)