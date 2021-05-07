from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

#df = spark.read.parquet("C:\\work\\project\\bugs\\bot").withColumn("roleId", lit("123456"))
# df.printSchema()
# |-- id: string (nullable = true)
# |-- login: string (nullable = true)
# |-- email: string (nullable = true)
# |-- name: string (nullable = true)
# |-- roleId: string (nullable = true)
# |-- guid: string (nullable = true)
# |-- timeZone: string (nullable = true)
# |-- groupIds: string (nullable = true)
#df.show(1000,False)

#df.write.parquet("C:\\work\\project\\bugs\\bot_updated")

df = spark.read.parquet("C:\\work\\project\\bugs\\bot_updated").withColumn("roleId", lit("123456"))
df.show(1000,False)