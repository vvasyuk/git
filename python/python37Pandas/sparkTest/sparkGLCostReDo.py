from pyspark.sql import SparkSession
from pyspark.sql import Row
from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import DecimalType
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("test").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")
date = '20210610'

start = time.perf_counter()
dfDatapoint = spark.read.parquet("C:\\work\\project\\bugs\\dev_03_GL_Cost\\gl_cost\\new")
print(dfDatapoint.count())
x = dfDatapoint.withColumn("code_no_prefix", regexp_replace(col("Account"), "CTD_Amts.", ""))
x.explain()
end = time.perf_counter()
print(f"read parq and count: {end - start:0.4f}")

# count without cache 4.8060
# count with cache 7.6459

# count without cache but with add column 4.8060

# read parq and count with cache: 21.6611
# read parq and count without cache: 14



# dfDatapoint.dropDuplicates(["Account"]).show(1000000,False)


# dfPivot = dfDatapoint.withColumn('amt', col('amt').cast(DecimalType(22,2))).withColumn('Split%Label', lit('')).withColumn('partition_dt', lit(date))\
#     .groupBy("Account", "Level", "Split%Label", "Allowability", "partition_dt").pivot('subperiod_code').sum('amt')\
#     .na.fill(0).cache()
# dfCount = dfPivot.count()
# print("dfPivot count: " + str(dfCount)) # 5578
# dfPivot.createOrReplaceTempView("df_pivot");
#
# dfSheetAcc = spark.read.parquet("C:\\work\\project\\bugs\\dev_03_GL_Cost\\sheet_acc").cache()
# dfSheetAcc = dfSheetAcc.filter(col('isgroup') == '0')
# print("dfSheetAcc count: " + str(dfSheetAcc.count()))
#
# dfSheets = spark.read.parquet("C:\\work\\project\\bugs\\dev_03_GL_Cost\\sheets").cache()
# dfSheets = dfSheets.filter((col('sheet_name').like('%Travel/ODC Forecast%')) & (col('sheet_type') == 'cube'))
# print("dfSheets count: " + str(dfSheets.count()))
#
# dfSheets_1641 = spark.read.parquet("C:\\work\\project\\bugs\\dev_03_GL_Cost\\sheet_1641").cache()
# print(dfSheets_1641.count())
# dfSheets_1641.show(10,False)

# accLkp = dfSheetAcc.alias("sheetAcc").join(dfSheets.alias("sheets"),dfSheetAcc["sheet_id"] == dfSheets["sheet_id"])\
#     .select(col('sheetacc_code'))
#
# # where pivot account not in accLkp
# dfResultStandard = dfPivot.alias('dfPivot').join(accLkp.alias('accLkp'), dfPivot["Account"] == accLkp["sheetacc_code"], 'left_anti')\
#     .select('dfPivot.*').cache()
# dfCount = dfResultStandard.count()
# print(f"dfResultStandard count: {dfCount}")
#
#
# # where pivot account is in accLkp
# dfResultCube = dfPivot.alias('dfPivot').join(accLkp.alias('accLkp'), dfPivot["Account"] == accLkp["sheetacc_code"])\
#     .select('dfPivot.*').drop("Split%Label").cache()
# dfCount = dfResultCube.count()
# print(f"dfResultCube count: {dfCount}")
