from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import DecimalType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        (1, 11, '111'),
        (2, 21, '211'),
        (2, 22, '221')
    ],
    ['id','col1','col2']
)

df.printSchema()

df = df.withColumn('concated', concat(col("id"), lit(" unmatched in some")))
df.show(5,False)

