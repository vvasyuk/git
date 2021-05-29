from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# df = spark.read.parquet("C:\\work\\project\\test\\users_to_delete").withColumn("wdap_role", lit("241"))
#     .filter(col('empl_name').like('%Ian%') | col('empl_name').like('%Ruth%'))

df = spark.read.parquet("C:\\work\\project\\test\\users_to_delete").filter(col('empl_name').like('%Bhimasenachar%'))
df.show(100,False)
#print(df.count()) 1962
#df.write.parquet("C:\\work\project\\test\\users_to_delete\\deleted")

# dfDeleted = spark.read.parquet("C:\\work\\project\\test\\users_to_delete\\deleted")
# dfDeleted.show(100,False)

# dfMaster = spark.read.parquet("C:\\work\\project\\test\\users_to_delete\\master")
# dfMaster.show(100,False)