from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time
from pyspark.sql.types import IntegerType,DecimalType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

date = 20210630

datapointMapped = spark.read.parquet("C:\\work\\project\\bugs\\prod_13_actualHours0")
# datapointMapped.printSchema()
# datapointMapped.show(100,False)
dfPivoted = datapointMapped\
    .withColumn('hrs', col('hrs').cast(DecimalType(22,2))).withColumn('partition_dt', lit(date))\
    .groupBy("Account", "Level", "Employee", "PLC", "Allowability", "partition_dt").pivot('subperiod_code').sum('hrs')\

pivotNoNulls = dfPivoted.na.fill(0)

pivotNoNulls.show(10,False)
