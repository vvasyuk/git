from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

# dfMaster = spark.read.parquet("C:\\work\\project\\bugs\\prod_actual_hours_nulls\\master")
# dfMaster.show(1000,False)

dfTransform = spark.read.parquet("C:\\work\\project\\bugs\\prod_actual_hours_nulls\\transform")
dfTransform.show(1000,False)
