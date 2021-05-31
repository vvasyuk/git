from pyspark.sql import SparkSession
from pyspark.sql.functions import *


spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()


def dfNotAndIn(df_dp,df_wp,dp_col,wp_col,error_string):
    df = df_dp.alias('df')
    df2 = df_wp.alias('df2')
    joined = df.join(df2, df[dp_col] == df2[wp_col], 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", lit(error_string)).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull()).select('df.*').cache()
    return _not_in, _in


def aggToString(df, column):
    return df.limit(20).agg(concat_ws(",", expr(f"sort_array(collect_list({column}))")).alias("agg_list")).collect()

# actual_Hours
df_ah = spark.read.parquet("C:/work/project/test/dimensions_check/actuakl_hours").withColumn('pk', monotonically_increasing_id())
#print(df_ah.count())
#df_ah.show(10,False)

# account_list
df_acc_lList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list").select(col('code'))
(df_acc_not_in, df_acc_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_acc_lList,dp_col='account',wp_col='code',error_string='unmatched in account')
print(f"accounts not in count: {df_acc_not_in.count()}")
print(f"accounts in count: {df_acc_in.count()}")

# # levels_list
# df_levels_list = spark.read.parquet("C:/work/project/test/dimensions_check/levels_list")
# (df_lev_not_in, df_lev_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_levels_list,dp_col='level_',wp_col='name')
# print(f"levels not in count: {df_lev_not_in.count()}")
# print(f"levels in count: {df_lev_in.count()}")
#
# # employee_list
# df_employee_list = spark.read.parquet("C:/work/project/test/dimensions_check/employee_list")
# (df_emp_not_in, df_emp_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_employee_list,dp_col='employee',wp_col='v_name')
# print(f"employee not in count: {df_emp_not_in.count()}")
# print(f"employee in count: {df_emp_in.count()}")

# plc_list
df_plc_list = spark.read.parquet("C:/work/project/test/dimensions_check/plc_list")
(df_plc_not_in, df_plc_in) = dfNotAndIn(df_dp=df_ah,df_wp=df_plc_list,dp_col='plc',wp_col='v_name',error_string='unmatched in plc')
print(f"plc not in count: {df_plc_not_in.count()}")
print(f"plc in count: {df_plc_in.count()}")

# bad data
df_acc_not_in.alias('bad').join(df_plc_not_in.alias('bad2'), df_acc_not_in['pk'] == df_plc_not_in['pk'], 'full').select(
    coalesce(col('bad.account'),col('bad2.account')).alias('account'),
    coalesce(col('bad.employee'),col('bad2.employee')).alias('employee'),
    coalesce(col('bad.plc'),col('bad2.plc')).alias('plc'),
    coalesce(col('bad.non_billable'),col('bad2.non_billable')).alias('non_billable'),
    coalesce(col('bad.time_dim'),col('bad2.time_dim')).alias('time_dim'),
    coalesce(col('bad.hrs'),col('bad2.hrs')).alias('hrs'),
    concat_ws(',','bad.error', 'bad2.error').alias('error')
)

# bad notify
print("bad notify")
bad_notification = ""
acc_bad = aggToString(df_acc_not_in, 'account')
if acc_bad[0]['agg_list']:
    bad_notification = bad_notification + f"unmatched in acc (limit 20 ids): {acc_bad[0]['agg_list']}\n"
plc_bad = aggToString(df_plc_not_in, 'plc')
if plc_bad[0]['agg_list']:
    bad_notification = bad_notification + f"unmatched in plc (limit 20 ids): {plc_bad[0]['agg_list']}\n"
print(bad_notification)

# good
res = df_acc_in.alias('df_acc_in').join(df_plc_in.alias('df_plc_in'), df_acc_in['pk'] == df_plc_in['pk']).select('df_acc_in.*').drop(col('pk'))
print("result")
res.show(10,False)