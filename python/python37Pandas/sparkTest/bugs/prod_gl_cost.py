from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

# transform
dfTransform = spark.read.parquet("C:\\work\\project\\bugs\\prod_11_gl_cost").cache()
print(dfTransform.count())
dfTransform.show(100,False)