from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
#spark.conf.set("spark.sql.sources.partitionOverwriteMode", "dynamic")
spark.conf.set("spark.sql.parquet.compression.codec", "gzip")
df = spark.createDataFrame(
    [
        (0, 'name0', 10, 20, 20210226),
    ],
    ['id', 'name', 'col1', 'col2', 'partition_dt']
)
#df.show(10,False)

df.write.parquet("C:\\work\\tryout\\python\\python37Pandas\\sparkTest\\result")
# .partitionBy("partition_dt")
# .mode("overwrite")
# .parquet(“s3://spark-output”))
