from pyspark.sql import SparkSession
from pyspark.sql.functions import *

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('2', 'two', 'b'),
        ('2', 'twotwo', 'bb'),
        ('3', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)

df.select(col('id')).dropDuplicates()\
    .show(10,False)