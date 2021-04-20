from pyspark.sql import SparkSession
from pyspark.sql.functions import col, coalesce, lit
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\users_levels_assign\\")

list_rows = map(lambda row: row.asDict(), df.collect())
print(type(list_rows))
for r in list(list_rows):
    print(r)
# df.show(20,False)