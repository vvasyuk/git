from pyspark.sql import SparkSession
from pyspark.sql.functions import col, coalesce, lit, when
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

# df1 = spark.createDataFrame(
#     [(1, "a", 2.0),
#      (2, "b", 3.0),
#      (3, "c", 3.0)],
#     ("x1", "x2", "x3"))
#
# df2 = spark.createDataFrame(
#     [(11, "a", -1.0),
#      (2, "b", 0.0)],
#     ("x1", "x2", "x3"))
#
# df = df1.join(df2, (df1.x1 == df2.x1) | (df1.x2 == df2.x2))
# df.show()

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
        ('3', 'three', 'i'),
        ('2', 'three', 'c'),
        ('4', 'fout', 'd'),
    ],
    ['id2','col2', 'col4']
)

df = df.alias('df')
df2 = df2.alias('df2')

ignoreList = ['i', 'g', 'n', 'o', 'r', 'e']
dfJoined = df.join(df2, (df.id == df2.id2), 'left')\
    .withColumn('operation', when(df2.col4.isin(ignoreList), 'ignore')
                .when(df2.col2 == 'three', 'insert')
                .otherwise('update'))


# (coalesce(col('dp.first_name'),lit('')) != coalesce(col('dpM.first_name'),lit('')))

dfJoined.show(10,False)