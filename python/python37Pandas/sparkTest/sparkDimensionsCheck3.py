from pyspark.sql import SparkSession
from pyspark.sql.functions import *


def dfNotAndIn(df_dp,df_wp,cond,dp_col,wp_col,entity):
    joined = df_dp.alias('df').join(df_wp.alias('df2'), cond, 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
    print(f"{entity} not in count: {_not_in.count()}")
    return _not_in, _in


# def dfNotAndIn(df_dp,df_wp,dp_col,wp_col,entity):
#     df = df_dp.alias('df')
#     df2 = df_wp.alias('df2')
#     joined = df.join(df2, df[dp_col] == df2[wp_col], 'left').cache()
#     _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
#     _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
#     print(f"{entity} not in count: {_not_in.count()}")
#     #print(f"{entity} in count: {_in.count()}")
#     return _not_in, _in


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
dfRaw = spark.read.parquet("C:\\work\\project\\test\\dimensions_check\\vendor_dollars").withColumn('pk', monotonically_increasing_id()).cache()

print("Vendor Dollars matching with wdap d_time")
dfDtimeList = spark.read.parquet("C:/work/project/test/dimensions_check/d_time")
(dfDtimeNotIn, dfDtimeIn) = dfNotAndIn(df_dp=dfRaw, df_wp=dfDtimeList,cond=[(dfRaw['time_dim'] == dfDtimeList['dp_hrs'])],dp_col='time_dim', wp_col='dp_hrs', entity='dtime')
dfDtimeIn = dfDtimeIn.select('df.*', 'df2.subperiod_code')

print("Vendor Dollars matching with wdap accounts")
dfAccList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list").select(col('code'))
(dfAccNotIn, dfAccIn) = dfNotAndIn(df_dp=dfRaw, df_wp=dfAccList,cond=[(dfRaw['wdap_account'] == dfAccList['code'])],dp_col='wdap_account', wp_col='code', entity='wdap_account')
dfAccIn = dfAccIn.select('df.pk')

print("Vendor Dollars matching with wdap levels")
dfLevelsList = spark.read.parquet("C:/work/project/test/dimensions_check/levels_list").select(col('name'))
(dfLevNotIn, dfLevIn) = dfNotAndIn(df_dp=dfRaw, df_wp=dfLevelsList, cond=[(dfRaw['level_'] == dfLevelsList['name'])],dp_col='level_', wp_col='name', entity='levels')
dfLevIn = dfLevIn.select('df.pk')

print("Vendor Dollars matching with wdap vendor_employees")
dfEmpList = spark.read.parquet("C:/work/project/test/dimensions_check/vendor_employee").select(col('v_name'))
(dfEmpNotIn, dfEmpIn) = dfNotAndIn(df_dp=dfRaw,df_wp=dfEmpList,cond=[(dfRaw['vendor_employee'] == dfEmpList['v_name'])],dp_col='vendor_employee',wp_col='v_name',entity='employees')
dfEmpIn = dfEmpIn.select('df.pk')

print("Vendor Dollars matching with wdap cost category")
dfCostCatList=spark.read.option("header",True).csv("C:/work/project/test/dimensions_check/cost_category/cost_category.csv").withColumnRenamed("Cost Category", "cost_category")
# dfCostCatList.show(10,False)
(dfCostCatNotIn, dfCostCatIn) = dfNotAndIn(df_dp=dfRaw, df_wp=dfCostCatList,cond=[(dfRaw['gl_account'] == dfCostCatList['Account']) | (dfRaw['gl_account_short'] == dfCostCatList['Account'])],dp_col='gl_account', wp_col='Account', entity='cost_category')
dfCostCatIn = dfCostCatIn.select('df.*', 'df2.cost_category')


dtimeInIntersectCostCat = joinDfsWithPk(dfDtimeIn, dfAccIn, 'pk').select('df.*', 'df2.cost_category')
#dfCostCatIn.show(10,False)

# dfRaw = spark.read.parquet("C:\\work\\project\\test\\dimensions_check\\vendor_employee")
# dfRaw.show(10,False)