from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\vendor_hours\\").cache()
# dfRaw.show(20,False)
# print(dfRaw.count())


# master
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\vendor_hours\\").cache()
# dfRaw.show(20,False)
# print(dfRaw.count())

# transform
dfRaw = spark.read.parquet("c:\\work\\project\\data\\transform\\datapoint\\vendor_hours\\").cache()
dfRaw.show(20,False)
print(dfRaw.count())


# d = strftime("%Y%m%d_%H%M%S", gmtime())
#
# dfVendor = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/vendor_hours/part-00000-6babb7a0-ef2d-4943-b41e-d6b4d2ce8f64.c000.snappy.parquet").cache()
# dfLkp = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/dtime/part-00000-3c3c9c81-5ffc-4cc2-88ea-33bbaede792b-c000.snappy.parquet").cache()
#
# print(f"dfVendor count: {dfVendor.count()}")
# print(f"lkp count: {dfLkp.count()}")
#
# dfWithCols = dfVendor.withColumn('wdap_account', lit('Hours')).withColumn('plc', lit('Senior Consultant II - S00029')).withColumn('billable', lit('BILLABLE'))
# dfJoined = dfWithCols.join(dfLkp, dfWithCols.time_dim == dfLkp.dp_hrs, 'left').select(
#     dfWithCols['wdap_account']
#     ,dfWithCols['gl_account']
#     ,dfWithCols['gl_account_short']
#     ,dfWithCols['level_']
#     ,dfWithCols['vendor_employee']
#     ,dfWithCols['time_dim']
#     ,dfLkp['subperiod_code']
#     ,dfWithCols['hrs']
#     ,dfWithCols['plc']
#     ,dfWithCols['billable']
# ).cache()
#
# print(f"dfJoined count: {dfJoined.count()}")
#
# master = dfJoined.filter(col('wdap_account') == lit('0')).cache()
# #master.show(20,False)
# print(f"master count: {master.count()}")
#
# remainingMaster = master.join(dfJoined, ['wdap_account'], 'left_anti').cache()
# print(f"remainingMaster count: {master.count()}")
# #remainingMaster.show(10,False)
#
# newMaster = dfJoined.union(remainingMaster).repartition(1).cache()
# print(f"newMaster count: {newMaster.count()}")
#newMaster.show(10,False)

# dfLkp.show(1000,False)
# distinctCols = df.select(col("time_dim")).distinct()

# joined = distinctCols.join(dfLkp, distinctCols.time_dim == dfLkp.dp_hrs, 'left')
# joined.show(100,False)
