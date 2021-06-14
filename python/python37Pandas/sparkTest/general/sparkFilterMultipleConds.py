from pyspark.sql import SparkSession
from pyspark.sql.functions import col
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df2 = spark.createDataFrame(
    [
        ('3', 'three', 'c'),
        ('3', 'threee', 'd'),
    ],
    ['id','col2', 'col4']
)


df2 = df2.alias('df2')

df2.filter((col('id') == '3') & (col('col2') == 'three'))\
    .show(10,False)