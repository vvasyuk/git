from pyspark.sql import SparkSession
from pyspark.sql.functions import *


def dfNotAndIn(df_dp,df_wp,dp_col,wp_col,entity):
    df = df_dp.alias('df')
    df2 = df_wp.alias('df2')
    joined = df.join(df2, df[dp_col] == df2[wp_col], 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", lit(f"unmatched in wdap {entity}")).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).cache()
    print(f"{entity} not in count: {_not_in.count()}")
    #print(f"{entity} in count: {_in.count()}")
    return _not_in, _in


def aggToString(df, column):
    return df.limit(20).agg(concat_ws(",", expr(f"sort_array(collect_list({column}))")).alias("agg_list")).collect()


def joinDfsWithPk(df, df2, pk):
    return df.alias('df').join(df2.alias('df2'), df[pk] == df2[pk]).select('df.*')


def joinBadData(df, df2, pk):
    bad = df.alias('bad')
    bad2 = df2.alias('bad2')
    return bad.join(bad2, bad['pk'] == bad2['pk'], 'full').select(
        coalesce(col('bad.account'), col('bad2.account')).alias('account'),
        coalesce(col('bad.levels_'), col('bad2.levels_')).alias('employee'),
        coalesce(col('bad.employee'), col('bad2.employee')).alias('employee'),
        coalesce(col('bad.plc'), col('bad2.plc')).alias('plc'),
        coalesce(col('bad.non_billable'), col('bad2.non_billable')).alias('non_billable'),
        coalesce(col('bad.time_dim'), col('bad2.time_dim')).alias('time_dim'),
        coalesce(col('bad.hrs'), col('bad2.hrs')).alias('hrs'),
        concat_ws(',', 'bad.error', 'bad2.error').alias('error'),
        coalesce(col('bad.pk'), col('bad2.pk')).alias('pk'),
    )

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
date = '20210521'
df_ah = spark.read.parquet("C:/work/project/test/dimensions_check/actuakl_hours").withColumn('pk', monotonically_increasing_id())

# | -- account: string(nullable=true)
# | -- level_: string(nullable=true)
# | -- employee: string(nullable=true)
# | -- plc: string(nullable=true)
# | -- non_billable: string(nullable=true)
# | -- time_dim: string(nullable=true)
# | -- hrs: string(nullable=true)

print("Actual Hours matching with wdap d_time")
df_dtime_list = spark.read.parquet("C:/work/project/test/dimensions_check/d_time")
(df_dtime_not_in, df_dtime_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_dtime_list,dp_col='time_dim',wp_col='dp_hrs',entity='dtime')
df_dtime_in = df_dtime_in.select('df.*','df2.subperiod_code')

print("Actual Hours matching with wdap accounts")
df_acc_lList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list").select(col('code'))
(df_acc_not_in, df_acc_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_acc_lList,dp_col='account',wp_col='code',entity='accounts')
df_acc_in = df_acc_in.select('df.pk')

print("Actual Hours matching with wdap levels")
df_levels_list = spark.read.parquet("C:/work/project/test/dimensions_check/levels_list").select(col('name'))
(df_lev_not_in, df_lev_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_levels_list,dp_col='level_',wp_col='name',entity='levels')
df_lev_in = df_lev_in.select('df.pk')

print("Actual Hours matching with wdap employees")
df_employee_list = spark.read.parquet("C:/work/project/test/dimensions_check/employee_list").select(col('v_name'))
(df_emp_not_in, df_emp_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_employee_list,dp_col='employee',wp_col='v_name',entity='employees')
df_emp_in = df_emp_in.select('df.pk')

print("Actual Hours matching with wdap plc")
df_plc_list = spark.read.parquet("C:/work/project/test/dimensions_check/plc_list")
(df_plc_not_in, df_plc_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_plc_list,dp_col='plc',wp_col='v_name',entity='plc')
df_plc_in = df_plc_in.select('df.pk')


bad_acc = joinBadData(df_dtime_not_in, df_acc_not_in, 'pk')
bad_acc_lev = joinBadData(bad_acc, df_lev_not_in, 'pk')
bad_acc_lev_emp = joinBadData(bad_acc_lev, df_emp_not_in, 'pk')
bad_acc_lev_emp_plc = joinBadData(bad_acc_lev_emp, df_plc_not_in, 'pk')
bad_acc_lev_emp_plc.show(10,False)


# good
# dtime_in_intersect_acc = joinDfsWithPk(df_dtime_in, df_acc_in, 'pk')
# dtime_in_intersect_acc_lev = joinDfsWithPk(dtime_in_intersect_acc, df_lev_in, 'pk')
# dtime_in_intersect_acc_lev_emp = joinDfsWithPk(dtime_in_intersect_acc_lev, df_emp_in, 'pk')
# dtime_in_intersect_acc_lev_emp_plc = joinDfsWithPk(dtime_in_intersect_acc_lev_emp, df_plc_in, 'pk')
#
# dtime_in_intersect_acc_lev_emp_plc\
#     .withColumn('Account',when(col('account') == 'Hours', 'HoursActuals.Hours_ActualsImport').when(col('account') == 'Cost', 'HoursActuals.Cost_ActualsImport').otherwise(col('account')))\
#     .withColumn('hrs',when(col('hrs').isNull(), 0).otherwise(col('hrs')))\
#     .withColumnRenamed('level_', 'Level').withColumnRenamed('employee', 'Employee').withColumnRenamed('plc', 'PLC')\
#     .withColumnRenamed('non_billable', 'Allowability').withColumnRenamed('time_dim', 'dp_hrs')\
#     .select('Account','Level','Employee','PLC','Allowability','dp_hrs','subperiod_code','hrs')\
#     .withColumn('partition_dt', lit(str(date)))\
#     .show(10,False)

# save result dataset into Master
# if Count > 0:
#     dfMasterSPNotNull.write.mode("overwrite").format("parquet").partitionBy("partition_dt").save(
#         f's3a://{s3_bucket}/master/datapoint/actuals_hours')
print("Completed!")