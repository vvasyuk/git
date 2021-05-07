from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

# master
dfMaster = spark.read.parquet("C:\\work\\project\\bugs\\prod_cost\\duplication\\master").filter(col('Account').like("521%")).cache()
dfMaster.printSchema()
dfMaster.show(10000,False)

# transform
dfCube = spark.read.parquet("C:\\work\\project\\bugs\\prod_cost\\duplication\\transform\cube").filter(col("Account") == "521_220_038").cache()
dfCube.show(10,False)
dfCube = spark.read.parquet("C:\\work\\project\\bugs\\prod_cost\\duplication\\transform\standard").filter(col("Account") == "521_220_038").cache()
dfCube.show(10,False)


# dfRaw = spark.read.parquet("C:\\work\\project\\bugs\\prod_cost\\raw_datapoint").cache()
# dfRaw.show(10,False)
# print("Raw count: " + str(dfRaw.count()))
#
# dfDTime = spark.read.parquet("C:\\work\\project\\bugs\\prod_cost\\d_time").cache()
# dfDTime.show(10,False)
# print("Time count: " + str(dfDTime.count()))
#
# dfRaw.createOrReplaceTempView("df_raw");
# dfDTime.createOrReplaceTempView("df_time");
# dfMaster = spark.sql(f"""
#   SELECT
#     replace(rw.account,'-','_') as Account
#     , rw.level_ as Level
#     , rw.allowability as Allowability
#     , rw.time_dim as dp_hrs
#     , tm.subperiod_code
#     , nvl(rw.amt,'0') as amt
#     , '20200505' as partition_dt
#   FROM df_raw as rw
#     left outer join df_time as tm
#       on rw.time_dim = tm.dp_hrs
# """).cache()
# Count = dfMaster.count()
# print(f"Master count: {Count}")
# dfMaster.createOrReplaceTempView("df_master");
#
# dfMaster.show(10,False)
#
#
# dfMasterSPNull = spark.sql(f"""
#   select *
#   from df_master
#   where subperiod_code is null
# """).cache()
# Count = dfMasterSPNull.count()
# print(f"Master subperiod_code is null count:")
# dfMasterSPNull.show(10,False)
#
#
# dfMasterSPNotNull = spark.sql(f"""
#   select *
#   from df_master
#   where subperiod_code is not null
# """).cache()
# Count = dfMasterSPNotNull.count()
# print(f"Master subperiod_code is not null count:")
# dfMasterSPNotNull.show(10,False)