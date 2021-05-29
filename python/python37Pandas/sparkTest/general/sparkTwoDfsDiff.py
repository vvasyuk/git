from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import IntegerType
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

start = time.perf_counter()
df = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('11', 'one', 'a'),
        ('2', 'two', 'b'),
        ('22', 'two', 'b'),
        ('3', 'threee', 'c'),
        ('33', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)

df2 = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('11', 'one', 'a'),
        ('2', 'two', 'b'),
        ('22', 'two', 'b')
    ],
    ['id2', 'col11', 'col33']
)

df3 = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('11', 'one', 'a')
    ],
    ['id2', 'col11', 'col33']
)
# df.show(10,False)
# df2.show(10,False)

def dfNotAndIn(df_dp,df_wp,dp_col,wp_col,error_string):
    # dfnotIn = df.join(df2, df[DpCol] == df2[WpCol], 'left_anti')
    # dfIn = df.join(df2, df[DpCol] == df2[WpCol])
    df = df_dp.alias('df')
    df2 = df_wp.alias('df2')
    #joined = df.join(df2, df[DpCol] == df2[WpCol], 'left').select('df.*').cache()
    joined = df.join(df2, df[dp_col] == df2[wp_col], 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", lit(error_string)).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).select('df.*').cache()
    return _not_in, _in

df = df.withColumn('pk', monotonically_increasing_id())

(df2_not_in, df2_in) = dfNotAndIn(df,df2,'id','id2', error_string='unmatched in df2')
(df3_not_in, df3_in) = dfNotAndIn(df,df3,'id','id2', error_string='unmatched in df3')
# print("not in")
# df2_not_in.show(10,False)
# print("in")
# df2_in.show(10,False)

# print("bad df2")
# df2_bad = df2_not_in.limit(2).agg(concat_ws(",", expr("sort_array(collect_list(id))")).alias("agg_list")).collect()
# print(f"unmatched in df2 (limit 20 ids): {df2_bad[0]['agg_list']}")
# print("bad df3")
# df3_bad = df3_not_in.limit(2).agg(concat_ws(",", expr("sort_array(collect_list(id))")).alias("agg_list")).collect()
# print(f"unmatched in df3 (limit 20 ids): {df3_bad[0]['agg_list']}")

df2_not_in.alias('df').join(df3_not_in.alias('df2'), df2_not_in['pk'] == df3_not_in['pk'], 'full').select(
    coalesce(col('df.id'),col('df2.id')).alias('id'),
    coalesce(col('df.col1'),col('df2.col1')).alias('col1'),
    coalesce(col('df.col3'),col('df2.col3')).alias('col3'),
    concat_ws(',','df.error', 'df2.error').alias('error')
).show(20,False)

# res = df3_in.alias('df3_in').join(df2_in.alias('df2_in'), df3_in['pk'] == df2_in['pk']).select('df3_in.*').drop(col('pk'))
# print("result")
# res.show(10,False)

end = time.perf_counter()
print(f"time elapsed: {end - start:0.4f}")

# time elapsed: 24.4028