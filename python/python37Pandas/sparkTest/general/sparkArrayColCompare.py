from pyspark.sql import SparkSession
from pyspark.sql.functions import *

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('2', 'two', 'b'),
        ('3', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)
df2 = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('2', '', 'b'),
        ('3', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)
# df1 = df.withColumn('attr', array(lit('one'), lit('two')))
df1 = df.withColumn('attr', array(col('id'), col('col1'), col('col3')))
df1.show(5,False)

df2 = df2.withColumn('attr', array(col('id'), col('col1'), col('col3')))
df2.show(5,False)

res = df1.join(df2, df1['attr'] == df2['attr'])
res.show(10,False)