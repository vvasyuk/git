from pyspark.sql import SparkSession
from pyspark.sql.functions import col, coalesce, lit
from pyspark.sql.types import IntegerType

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

df2 = spark.createDataFrame(
    [
        ('2', 'two', 'b'),
    ],
    ['id', 'col1', 'col3']
)
# df.show(10,False)
# df2.show(10,False)

dfnotIn = df.join(df2, df['id']==df2['id'], 'left_anti')
dfnotIn.show(10,False)