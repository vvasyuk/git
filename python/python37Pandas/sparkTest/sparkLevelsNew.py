from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# dfLevels = spark.read.option("header",True).csv("C:\\work\\project\\test\\levels_new\\LEVELS_FRESH_EXTRACT 05132021.csv")
# dfLevels.orderBy(col("ENGAGEMENT_ID")).show(100,False)

dfDatapoint = spark.read.parquet("C:\\work\\project\\test\\levels_new\\ingest")
dfDatapoint = dfDatapoint\
    .withColumn('engagement_id', regexp_replace(col('engagement_id'), " - .*", "")) \
    .select(col('parent_name'), col('engagement_id'), col('engagement_name'), array(col('account'),col('client'),col('client_name'),col('l1'),col('l2'),col('l3'),col('l4'),col('sector')).alias('attributes'))\
    .filter((col('engagement_id').like('%AGR10490%')) |
                   (col('engagement_id') == ('AGRICULTURE, US DEPARTMEN')) |
                   (col('engagement_id') == 'USDA') |
                   (col('engagement_id') == 'CIVIL GOVT') |
                   (col('engagement_id') == 'Engagements')
                   )\

#dfDatapoint.show(10, False)


dfWdap = spark.read.parquet("C:\\work\\project\\test\\levels_new\\wdap_levels") \
    .withColumn('name_join', regexp_replace(col('name'), " - .*", "")) \
    .select(col('parentid'), col('id'), col('name'), col('name_join'),
            array(col('attributes.Account'), col('attributes.Client'), col('attributes.Client Name'),
                  col('attributes.L1'), col('attributes.L2'), col('attributes.L3'), col('attributes.L4'),
                  col('attributes.Sector')).alias('attributes'))\
    .filter((col('name').like('%AGR10490%')) |
            (col('id') == '20221') |
            (col('id') == '20220') |
            (col('id') == '19602') |
            (col('id') == '1')
            )
    #.filter((col('name_join').like('%AGRICULTURE, US DEPARTMEN%')))
#dfWdap.show(10,False)
#
# # join should be 12350
dfWdap.alias('wp').join(dfDatapoint.alias('dp'), dfWdap['name_join'] == dfDatapoint['engagement_id'], 'left')\
    .select(col('parent_name'), col('engagement_id'), col('engagement_name'), col('dp.attributes'),
            col('id').alias('wap_id'), col('name').alias('wap_name'), col('wp.attributes').alias('wap_attributes'))\
    .withColumn('ins_upd',when(col('wap_id').isNull(), 'Y')
                .when( (col('engagement_name').isNotNull()) & (col('engagement_name') != col('wap_name')), 'Y')
                .when( (col('dp.attributes').isNotNull()) & (col('wap_attributes') != col('dp.attributes')), 'Y')
                .otherwise(lit('N')))\
    .show(500,False)

# .withColumn('ins_upd',
#              when(col('dp.attributes').isNotNull() & col('wp.attributes') != col('dp.attributes'), 'Y')
#             .when(col('engagement_name') != col('wap_name'), 'Y')
#             .when(col('wap_id').isNull(), 'Y')
#             .otherwise(lit('N')))\
# #print(joined.count())
