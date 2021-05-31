from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import IntegerType

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
        ('3', 'three', 'c'),
        ('4', 'fout', 'd'),
    ],
    ['id','col2', 'col4']
)

df = df.alias('df')
df2 = df2.alias('df2')

dfJoined = df.join(df2, (df.id == df2.id), 'full')\
#dfJoined.show(10,False)

# dfJoinedWithError = dfJoined.withColumn('Error',when(col('df.id') == 1, 'this is one').when(col('df2.id') == 4, 'this is four').otherwise(col('df.id')))
dfJoinedWithError = dfJoined.withColumn('Error',when(col('df.id').isNull(), 'missing in t1').when(col('df2.id').isNull(), 'missing in t2').otherwise(lit('all ok')))
dfJoinedWithError.show(10,False)
# (coalesce(col('dp.first_name'),lit('')) != coalesce(col('dpM.first_name'),lit('')))