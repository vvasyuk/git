from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\vendor_hours\\part-00000-06716f6e-9d43-46eb-933a-f97d8c0cebfd.c000.snappy.parquet").cache()
#
# pivot = dfRaw.withColumn('hrs', col('hrs').cast(IntegerType()))\
#     .select(col('wdap_account').alias("Account"), col('level_').alias("Level"), col('employee').alias("Vendor_Employee"), col('plc').alias("PLC"), col('allowability').alias("Allowability"), col('subperiod_code'), col('hrs'))\
#     .groupBy("Account", "Level", "Vendor_Employee", "PLC", "Allowability").pivot('subperiod_code').sum('hrs')
# pivotNoNulls = pivot.na.fill(0)
# pivotNoNulls.show(20,False)

dfRaw = spark.read.parquet("c:\\work\\project\\data\\transform\\vendor_hours\\part-00000-12693c2d-db27-4b2b-a63d-f919cc8c99bb.c000.snappy.parquet").cache()
dfRaw.show(20,False)