from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw
# df = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\users\\").cache()
# df.show(20,False)
#|Jenna,Jefferson  |JJenna9879@OBT.DELOITTE.COM    |164      |
# print(df.count())
# print(df.filter(col('wdap_role').isNotNull()).count())

# raw test
# df = spark.createDataFrame(
#     [('Atest,Atest', 'Atest@OBT.DELOITTE.COM', '164')],
#     ['empl_name', 'empl_email', 'wdap_role']
# )
# df.repartition(1).write.parquet("c:\\work\\project\\data\\raw\\datapoint\\users\\test")

# raw wdap
# dfW = spark.read.parquet("c:\\work\\project\\data\\master\\wdap\\users\\")
# dfW.show(20,False)

# ignore_list = ['206']
#
# dfJoined = df.join(dfW,  df.empl_email == dfW.login, 'left')\
#     .select(col('empl_name'),col('empl_email'),col('wdap_role'),col('login'),col('name'),col('guid'),col('roleId'))\
#     .withColumnRenamed('empl_name', 'dp_empl_name')\
#     .withColumnRenamed('empl_email', 'dp_empl_email')\
#     .withColumnRenamed('wdap_role', 'dp_wdap_role')\
#     .filter((coalesce(col('dp_empl_name'),lit('')) != coalesce(col('name'),lit(''))) |
#             (coalesce(col('dp_wdap_role'),lit('')) != coalesce(col('roleId'),lit('')))
#       )\
#     .withColumn('operation', when(dfW.roleId.isin(ignore_list), 'ignore')
#                 .when(dfW.login.isNull(), 'insert')
#                 .otherwise('update'))
#
# dfJoined.show(10,False)

# master
# dfRaw = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\users\\")
# dfRaw.show(20,False)


# transform
dfRaw = spark.read.parquet("c:\\work\\project\\data\\transform\\users\\")
dfRaw.show(20,False)
# print(dfRaw.count())