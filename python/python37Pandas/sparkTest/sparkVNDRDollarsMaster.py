from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("test").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")


dfRaw = spark.read.parquet("C:\\work\\project\\data\\master\\datapoint\\vendor_dollars")
dfRaw.show(20,False)