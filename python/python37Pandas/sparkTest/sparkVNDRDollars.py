from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("test").getOrCreate()
# spark.sparkContext.setLogLevel("ERROR")
# date = strftime("%Y%m%d_%H%M%S", gmtime())
#
# dfVendorDollars = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/vendor_dollars/raw/part-00000-a2ecaeea-b8f2-49c9-b08e-37f91696e6bc.c000.snappy.parquet").cache()
# dfMap = spark.read.option("header", "true").csv("c:/work/project/data/mappings/cost_category.csv").cache()
# dfDTime = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/dtime/part-00000-3c3c9c81-5ffc-4cc2-88ea-33bbaede792b-c000.snappy.parquet").cache()
#
# print(f"dfVendor count: {dfVendorDollars.count()}")
# print(f"dfMap count: {dfMap.count()}")
# print(f"lkp count: {dfDTime.count()}")
#
# dfWithCols = dfVendorDollars.withColumn('wdap_account', lit('Vendor Hours Actuals &amp; Forecast')).withColumn('date', lit(date))
# dfJoined = dfWithCols.join(dfDTime, dfWithCols.time_dim == dfDTime.dp_hrs, 'left').select(
#     dfWithCols['wdap_account']
#     ,dfWithCols['gl_account']
#     ,dfWithCols['gl_account_short']
#     ,dfWithCols['level_']
#     ,dfWithCols['vendor_employee']
#     ,dfDTime['subperiod_code']
#     ,dfWithCols['amt']
#     ,dfWithCols['date']
# ).cache()
#
# # dfJoined.show(10,False)
# # dfMap.show(10,False)
#
# dfMapped = dfJoined.join(dfMap, (dfJoined.gl_account == dfMap.Account) | (dfJoined.gl_account_short == dfMap.Account))\
#     .select(dfJoined['wdap_account'],dfJoined['gl_account'], dfJoined['gl_account_short'], dfJoined['level_'], dfJoined['vendor_employee'], dfJoined['subperiod_code'], dfJoined['amt'], dfJoined['date'], dfMap['Cost Category'])
#
# dfMapped.show(10,False)

dfRaw = spark.read.parquet("C:\\work\\project\\data\\raw\\datapoint\\vendor_dollars")
dfRaw.show(20,False)