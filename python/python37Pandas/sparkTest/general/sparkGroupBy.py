from pyspark.sql import SparkSession
from pyspark.sql.functions import col, lit, concat_ws, expr
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('1', '11', '111'),
        ('2', '21', '211'),
        ('2', '22', '221'),
        ('3', '31', '311'),
        ('3', '32', '321'),
        ('3', '33', '331')
    ],
    ['id','col1','col2']
)

# df.groupBy("id").agg(concat_ws(",", expr("sort_array(collect_list(col1))")).alias("agg_list"))\
#     .show(10,False)

collected = df.filter(col("id")==0).groupBy("id").agg(concat_ws(",", expr("sort_array(collect_list(col1))")).alias("agg_list")).collect()
print(len(collected))
