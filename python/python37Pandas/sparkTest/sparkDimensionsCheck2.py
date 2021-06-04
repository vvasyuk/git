from pyspark.sql import SparkSession
from pyspark.sql.functions import *


def dfNotAndIn(df_dp,df_wp,dp_col,wp_col,entity):
    df = df_dp.alias('df')
    df2 = df_wp.alias('df2')
    joined = df.join(df2, df[dp_col] == df2[wp_col], 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
    print(f"{entity} not in count: {_not_in.count()}")
    #print(f"{entity} in count: {_in.count()}")
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
dfAH = spark.read.parquet("C:/work/project/test/dimensions_check/actuakl_hours").withColumn('pk', monotonically_increasing_id()).cache()


print("Actual Hours matching with wdap d_time")
dfDtimeList = spark.read.parquet("C:/work/project/test/dimensions_check/d_time")
(dfDtimeNotIn, dfDtimeIn) = dfNotAndIn(df_dp=dfAH, df_wp=dfDtimeList, dp_col='time_dim', wp_col='dp_hrs', entity='dtime')
dfDtimeIn = dfDtimeIn.select('df.*', 'df2.subperiod_code')

print("Actual Hours matching with wdap accounts")
dfAccList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list")#.select(col('code'))
(dfAccNotIn, dfAccIn) = dfNotAndIn(df_dp=dfAH, df_wp=dfAccList, dp_col='account', wp_col='code', entity='accounts')
dfAccIn = dfAccIn.select('df.pk')

print("Actual Hours matching with wdap levels")
dfLevelsList = spark.read.parquet("C:/work/project/test/dimensions_check/levels_list").select(col('name'))
    #.filter(col('name').like('%TEN07501.00.07 - TEDS Contact Center%'))
(dfLevNotIn, dfLevIn) = dfNotAndIn(df_dp=dfAH, df_wp=dfLevelsList, dp_col='level_', wp_col='name', entity='levels')
dfLevIn = dfLevIn.select('df.pk')

print("Actual Hours matching with wdap employees")
dfEmployeeList = spark.read.parquet("C:/work/project/test/dimensions_check/employee_list").select(col('v_name'))
    #.filter(col('v_name').like('%00562230%')).show(100,False)
# Carter,Richardson - 00410993
# Gray,Steven - 00428447
# Isabella,Goldsmith - 00562230
(dfEmpNotIn, dfEmpIn) = dfNotAndIn(df_dp=dfAH, df_wp=dfEmployeeList, dp_col='employee', wp_col='v_name', entity='employees')
dfEmpIn = dfEmpIn.select('df.pk')

print("Actual Hours matching with wdap plc")
dfPlcList = spark.read.parquet("C:/work/project/test/dimensions_check/plc_list")
(dfPlcNotIn, dfPlcIn) = dfNotAndIn(df_dp=dfAH, df_wp=dfPlcList, dp_col='plc', wp_col='v_name', entity='plc')
dfPlcIn = dfPlcIn.select('df.pk')

badAcc = joinBadData(dfDtimeNotIn, dfAccNotIn)
badAccLev = joinBadData(badAcc, dfLevNotIn)
badAccLevEmp = joinBadData(badAccLev, dfEmpNotIn)
badAccLevEmpPlc = joinBadData(badAccLevEmp, dfPlcNotIn).cache()

#badAccLevEmpPlc.repartition(1).write.parquet("C:\\work\\project\\test\\dimensions_check\\bad_data_parquet")

#bad notify
print("generating bad notify")
badNotification = aggToString(badAccLevEmpPlc, 'error')
print(badNotification)

#good
dtimeInIntersectAcc = joinDfsWithPk(dfDtimeIn, dfAccIn, 'pk')
dtimeInIntersectAccLev = joinDfsWithPk(dtimeInIntersectAcc, dfLevIn, 'pk')
dtimeInIntersectAccLevEmp = joinDfsWithPk(dtimeInIntersectAccLev, dfEmpIn, 'pk')
dtimeInIntersectAccLevEmpPlc = joinDfsWithPk(dtimeInIntersectAccLevEmp, dfPlcIn, 'pk')

dtimeInIntersectAccLevEmpPlc\
    .withColumn('Account',when(col('account') == 'Hours', 'HoursActuals.Hours_ActualsImport').when(col('account') == 'Cost', 'HoursActuals.Cost_ActualsImport').otherwise(col('account')))\
    .withColumn('hrs',when(col('hrs').isNull(), 0).otherwise(col('hrs')))\
    .withColumnRenamed('level_', 'Level').withColumnRenamed('employee', 'Employee').withColumnRenamed('plc', 'PLC')\
    .withColumnRenamed('non_billable', 'Allowability').withColumnRenamed('time_dim', 'dp_hrs')\
    .select('Account','Level','Employee','PLC','Allowability','dp_hrs','subperiod_code','hrs')\
    .withColumn('partition_dt', lit(str(date)))\
    .show(10,False)

# save result dataset into Master
# if Count > 0:
#     dfMasterSPNotNull.write.mode("overwrite").format("parquet").partitionBy("partition_dt").save(
#         f's3a://{s3_bucket}/master/datapoint/actuals_hours')
print("Completed!")