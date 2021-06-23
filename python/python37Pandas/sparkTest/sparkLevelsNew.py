from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# dfLevels = spark.read.option("header",True).csv("C:\\work\\project\\test\\levels_new\\LEVELS_FRESH_EXTRACT 05132021.csv")
# dfLevels.orderBy(col("ENGAGEMENT_ID")).show(100,False)

dfDatapoint = spark.read.parquet("C:\\work\\project\\test\\levels_new\\ingest")
#print(dfDatapoint.count()) # 12350
# dfDatapoint.filter((col('engagement_id').like('%AGR10490%')) |
#                    (col('engagement_id').like('%AGRICULTURE, US DEPARTMEN - 0001200355%')) |
#                    (col('engagement_id') == 'USDA') |
#                    (col('engagement_id') == 'CIVIL GOVT') |
#                    (col('engagement_id') == 'Engagements')
#                    )\
#     .select(col('parent_name'), col('engagement_id'), col('engagement_name'), array(col('account'),col('client'),col('client_name'),col('l1'),col('l2'),col('l3'),col('l4'),col('sector')).alias('attributes'))\
#     .show(12500,False)

dfWdap = spark.read.parquet("C:\\work\\project\\test\\levels_new\\wdap_levels")\
    .filter((col('name').like('%AGR10490%')) |
            (col('id') == '20221') |
            (col('id') == '20220') |
            (col('id') == '19602') |
            (col('id') == '1')
            )\
    .select(col('parentid'), col('id'), col('name'), array(col('attributes.Account'), col('attributes.Client'), col('attributes.Client Name'), col('attributes.L1'), col('attributes.L2'), col('attributes.L3'), col('attributes.L4'), col('attributes.Sector')).alias('attributes'))
dfWdap.show(10,False)