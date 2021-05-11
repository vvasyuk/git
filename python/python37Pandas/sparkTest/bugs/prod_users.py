from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

# raw wdap 1
dfWdap1 = spark.read.parquet("C:\\work\\project\\bugs\\prod_10_users_full_reload\\raw_wdap\\parquet_wdap_users").cache()
print(dfWdap1.count())

dfWdap1.show(10000,False)

# raw wdap 2
# dfWdap2 = spark.read.parquet("C:\\work\\project\\bugs\\prod_10_users_full_reload\\raw_wdap\\part-00000-af8be973-1564-4065-87ce-0d056bc56942-c000.snappy.parquet").cache()
# print(dfWdap2.count())
# dfWdap2.show(10000,False)

# raw wdap
# dfWdap = spark.read.parquet("C:\\work\\project\\bugs\\prod_10_users_full_reload\\raw_wdap\\").cache()
# print(dfWdap.count())

# master
# dfMaster = spark.read.parquet("C:\\work\\project\\bugs\\prod_10_users_full_reload\\master").filter(col("operation")=="update").cache()
# print(dfMaster.count())
# dfMaster.show(10000,False)