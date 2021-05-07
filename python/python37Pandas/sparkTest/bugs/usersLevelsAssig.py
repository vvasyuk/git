from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

dfUsers = spark.read.parquet("C:\\work\\project\\bugs\\prod_levels_assign\\ingest").cache()
dfUsers.show(10,False)

# dfWdapUsers = spark.read.parquet("C:\\work\\project\\bugs\\prod_levels_assign\\master\\wdap_users").cache()
# dfWdapUsers.show(10,False)

dfWdapLevels = spark.read.parquet("C:\\work\\project\\bugs\\prod_levels_assign\\raw_wdap_levels")\
    .select(col("id"), col("name")).withColumnRenamed("id", "wdap_level_id").withColumnRenamed("name", "wdap_level_name")\
    .cache()
dfWdapLevels.show(1000,False)

dfLevelsJoin = dfUsers.join(dfWdapLevels, dfUsers['LEVELS'] == dfWdapLevels['wdap_level_name'], 'left').cache()
dfLevelsJoined = dfLevelsJoin.filter(col('wdap_level_name').isNotNull()).cache()
dfLevelsJoinedNot = dfLevelsJoin.filter(col('wdap_level_name').isNull()).cache()

dfLevelsJoinedNot.show(1000,False)