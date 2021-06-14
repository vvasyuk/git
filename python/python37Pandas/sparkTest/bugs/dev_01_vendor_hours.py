from pyspark.sql import SparkSession
from pyspark.sql.functions import *


def dfNotAndIn(df_dp,df_wp,cond,dp_col,wp_col,entity):
    joined = df_dp.alias('df').join(df_wp.alias('df2'), cond, 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
    print(f"{entity} not in count: {_not_in.count()}")
    return _not_in, _in


def aggToString(df, column):
    aggregated = df.limit(20).agg(concat_ws(",", expr(f"sort_array(collect_list({column}))")).alias("agg_list")).collect()
    return aggregated[0]['agg_list']


def joinDfsWithPk(df, df2, pk):
    return df.alias('df').join(df2.alias('df2'), df[pk] == df2[pk]).select('df.*')


def joinBadData(df, df2):
    bad = df.alias('bad')
    bad2 = df2.alias('bad2')
    dfRes = bad.join(bad2, bad['pk'] == bad2['pk'], 'full').select(
        coalesce(col('bad.account'), col('bad2.account')).alias('account'),
        coalesce(col('bad.level_'), col('bad2.level_')).alias('level_'),
        coalesce(col('bad.employee'), col('bad2.employee')).alias('employee'),
        coalesce(col('bad.plc'), col('bad2.plc')).alias('plc'),
        coalesce(col('bad.non_billable'), col('bad2.non_billable')).alias('non_billable'),
        coalesce(col('bad.time_dim'), col('bad2.time_dim')).alias('time_dim'),
        coalesce(col('bad.hrs'), col('bad2.hrs')).alias('hrs'),
        concat_ws(',', 'bad.error', 'bad2.error').alias('error'),
        coalesce(col('bad.pk'), col('bad2.pk')).alias('pk')
    )
    return dfRes


spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
date = '20210521'

# df1 = spark.createDataFrame(
#     [(1, "A"),
#      (2, "B"),
#      (3, "C")],
#     ["A1", "A2"])
#
# df2 = spark.createDataFrame(
#     [(1, "F"),
#      (2, "B")],
#     ["B1", "B2"])
#
# df = df1.join(df2, (df1.A1 == df2.B1) | (df1.A2 == df2.B2))
# df.show()

df = spark.createDataFrame(
    [
        ('540-250-008', '590-540')
    ],
    ['GL_ACCOUNT','GL_ACCOUNT_SHORT']
)

df2 = spark.createDataFrame(
    [
        ('540-250-008', 'a'),
        ('590-540', 'b')
    ],
    ['Account', 'col2']
)
# #(dfCostCatNotIn, dfCostCatIn) = dfNotAndIn(df_dp=df, df_wp=df2,cond=[(df['gl_account'] == df2['Account']) | (df['gl_account_short'] == df2['Account'])],dp_col='gl_account', wp_col='Account', entity='cost_category')
dfCostCatIn = df.join(df2,[(df['GL_ACCOUNT'] == df2['Account']) or (df['GL_ACCOUNT_SHORT'] == df2['Account'])],'left')
# dfCostCatIn = df.join(df2,[(df['GL_ACCOUNT'] == df2['Account'])],'left')
#dfCostCatIn = df2.join(df,[(df2['Account'] == df['GL_ACCOUNT']) | (df2['Account'] == df['GL_ACCOUNT_SHORT'])],'right')
dfCostCatIn.show(5,False)

#dfRaw = spark.read.parquet("C:\\work\\project\\bugs\\dev_01_vendor_dollars_duplicates\\vendor_dollars").withColumn('pk', monotonically_increasing_id()).cache()
#
# #print(dfRaw.count())
# dfRaw.show(40,False)
#
# print("Vendor Dollars matching with wdap cost category")
# dfCostCatList=spark.read.option("header",True).csv("C:\\work\\project\\bugs\\dev_01_vendor_dollars_duplicates\\cost_cat\\cost_category.csv").withColumnRenamed("Cost Category", "cost_category")
# (dfCostCatNotIn, dfCostCatIn) = dfNotAndIn(df_dp=dfRaw, df_wp=dfCostCatList,cond=[(dfRaw['gl_account'] == dfCostCatList['Account']) | (dfRaw['gl_account_short'] == dfCostCatList['Account'])],dp_col='gl_account', wp_col='Account', entity='cost_category')
# dfCostCatIn = dfCostCatIn.dropDuplicates(['pk']).select('df.*', 'df2.cost_category')
#
# #print(dfCostCatIn.count())
# dfCostCatIn.show(40,False)

#dtimeInIntersectCostCat = joinDfsWithPk(dfDtimeIn, dfAccIn, 'pk').select('df.*', 'df2.cost_category')
#dfCostCatIn.show(10,False)

# dfRaw = spark.read.parquet("C:\\work\\project\\test\\dimensions_check\\vendor_employee")
# dfRaw.show(10,False)