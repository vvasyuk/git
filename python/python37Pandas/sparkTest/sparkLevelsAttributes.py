from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import StringType, MapType
from pyspark.sql.functions import from_json
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
# df = spark.read.parquet("C:\\work\\tryout\python\\python37Pandas\\sparkTest\\parq")
# df.show(10,False)
# dfRawWp = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\levels_attributes\\")\
#     .withColumn('attributes',struct(\
#     col("attr_account").alias('account'),\
#     col("attr_client").alias('client'),\
#     col("attr_client_name").alias('client_name'),\
#     col("attr_contract_type").alias('contract_type'),\
#     col("attr_division").alias('division'),\
#     col("attr_engagement_business").alias('engagement_business'),\
#     col("attr_engagement_manager").alias('engagement_manager'),\
#     col("attr_engagement_name").alias('engagement_name'),\
#     col("attr_engagement_ppmd").alias('engagement_ppmd'),\
#     col("attr_financial_advisor").alias('financial_advisor'),\
#     col("attr_l1").alias('l1'),\
#     col("attr_l2").alias('l2'),\
#     col("attr_l3").alias('l3'),\
#     col("attr_l4").alias('l4'),\
#     col("attr_pc_sub-account").alias('pc_sub_account'),\
#     col("attr_pnr_flag").alias('pnr_flag'),\
#     col("attr_pop_end").alias('pop_end'),\
#     col("attr_pop_start").alias('pop_start'),\
#     col("attr_planning_level").alias('planning_level'),\
#     col("attr_sector").alias('sector')\
# ))\
# .select(col('proj_id'), col('attributes')).filter(col('proj_id') == 'HOU12037.00.01')
# # dfRawWp.show(20,False)
# #dfRawWp.printSchema()
# #dfRawWp.show(20,False)
# dfRawWp.write.parquet("C:\\work\\tryout\\python\\python37Pandas\\sparkTest\\parq")


# raw wdap
dfRawWp = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\latest")
dfRawWp.printSchema()
dfRawWp.withColumnRenamed('attributes', 'attributes_old')\
    .withColumn('attributes',struct(
    col("attributes_old.Account").alias('Account'),
    col("attributes_old.Client").alias('Client'),
    col("attributes_old.Client Name").alias('Client%Name'),
    col("attributes_old.Contract Type").alias('Contract%Type'),
    col("attributes_old.Division").alias('Division'),
    col("attributes_old.Engagement Business").alias('Engagement%Business'),
    col("attributes_old.Engagement Manager").alias('Engagement%Manager'),
    col("attributes_old.Engagement PPMD").alias('Engagement%PPMD'),
    col("attributes_old.Financial Advisor").alias('Financial%Advisor'),
    col("attributes_old.L1").alias('L1'),
    col("attributes_old.L2").alias('L2'),
    col("attributes_old.L3").alias('L3'),
    col("attributes_old.L4").alias('L4'),
    col("attributes_old.PC Sub-Account").alias('PC%Sub-Account'),
    col("attributes_old.PNR FLAG").alias('PNR%FLAG'),
    col("attributes_old.POP End").alias('POP%End'),
    col("attributes_old.POP Start").alias('POP%Start'),
    col("attributes_old.Planning Level").alias('Planning%Level'),
    col("attributes_old.Sector").alias('Sector')))\
    .select(col('id'),col('name'),col('attributes'))\
    .show(10,False)

# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\latest\\part-00000-d1fb2fb9-d4ed-4fa8-ac9a-fc5f08f64646-c000.snappy.parquet")
# dfRaw.filter(col('proj_id').like("%TWVA00001%")).show(20,False)