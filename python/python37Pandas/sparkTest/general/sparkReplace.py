from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('CTD_Amts.510', 'one', 'a'),
        ('CTD_Amts.510_100', 'two', 'b'),
        ('CTD_Amts.Labor', 'twotwo', 'bb'),
        ('CTD_Amts.510_100_001', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)

df.withColumn("id_no_prefix", regexp_replace(col("id"), "CTD_Amts.", "")).show(5,False)