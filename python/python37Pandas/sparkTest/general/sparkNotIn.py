from pyspark.sql import SparkSession
from pyspark.sql.functions import col, coalesce, lit
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('11', 'one', 'a'),
        ('22', 'two', 'b'),
        ('33', 'twotwo', 'bb'),
        ('44', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)

df2 = spark.createDataFrame(
    [
        ('22', 'two', 'b'),
    ],
    ['id', 'col1', 'col3']
)

df3 = spark.createDataFrame(
    [
        ('33', 'two', 'b'),
    ],
    ['id', 'col1', 'col3']
)

# dfnotIn = df.join(df2, df['id']==df2['id'], 'left_anti')
# dfnotIn.show(10,False)

# not in twice
dfnotInTwice = df.join(df2, df['id']==df2['id'], 'left_anti')\
    .join(df3, df['id']==df3['id'], 'left_anti')
dfnotInTwice.show(10,False)

# only in first lookup
dfInFirst = df.join(df2, df['id']==df2['id'])
dfInFirst.show(10,False)

# only in second lookup
dfInSecond = df.join(df3, df['id']==df3['id'])
dfInSecond.show(10,False)