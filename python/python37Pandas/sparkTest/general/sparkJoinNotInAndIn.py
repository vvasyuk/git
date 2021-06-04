from pyspark.sql import SparkSession
from pyspark.sql.functions import *

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

def dfNotAndIn(df_dp,df_wp,cond,dp_col,wp_col,entity):
    df = df_dp.alias('df')
    df2 = df_wp.alias('df2')
    joined = df.join(df2, cond, 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
    print(f"{entity} not in count: {_not_in.count()}")
    return _not_in, _in

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

wp_col = 'id'
dp_col = 'id'
cond = [(df[wp_col] == df2[wp_col]) | (df[wp_col] == df2[wp_col])]
(not_in, _in) = dfNotAndIn(df, df2, cond, dp_col, wp_col, 'test')
not_in.show(10,False)
_in.show(10,False)

