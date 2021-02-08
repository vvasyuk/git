from pyspark.sql import SparkSession
from pyspark.sql import Row
#import requests
from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

# def f1(str):
#     payload = {'key1': str}
#     r2 = requests.post("https://httpbin.org/post", data=payload)
#     # print(r2.text)
#     #return (''+str).upper()
#     return r2.text
#
# f1UDF = udf(lambda z: f1(z))
#
# spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
#
# df = spark.read.option("header", "true").csv("D:/work/tryout/coursera/aws/glue/moviedata.csv")
#
# df1 = df.withColumn("new1", lit('1'))
# df2 = df1.withColumn("new2", f1UDF(col("title")))
# df2.show(20,False)

# dfRaw0 = spark.read.parquet("D:/dev/report/test_data/raw/*")
# dfMaster = spark.read.parquet("D:/dev/report/test_data/master/*")



spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# spark.read.parquet("C:/Users/Jopa/Downloads/wdap_users_parquet5").show(10,False)

wdapDF = spark.createDataFrame(
    [
        (1, 'yenny.mandola.DELOITTE1@deloitte2.com', 'implementer@sciquest.com', '* SUPPORT: Yenny Mandola', '1', 'B71574EDA6BA1D91E0532A061E0AC777', 'US/Pacific'),
        (21, 'ehutchison@deloitte2.com', '', 'Evelyn Hutchison', '1', 'B71574EDA6BB1D91E0532A061E0AC777', 'US/Pacific'),
        (61, 'FEMARTINEZ@DONOTUSEDELOITTE.COM', 'bensminger@deloitte.com', 'Brian Ensminger', '1', 'B71574EDA6CA1D91E0532A061E0AC777', 'US/Pacific'),
    ],
    ['id', 'login', 'email', 'name', 'roleId', 'guid', 'timeZone']
)

rawDF = spark.createDataFrame(
    [
        (108194, 'Seaton', 'Deena', 'DSEATON@DELOITTE.COM'),
        (306321, 'Martinez', 'Felix', 'FEMARTINEZ@DONOTUSEDELOITTE.COM'),
        (337756, 'Wilcox', 'Nathan', 'NWILCOX@DELOITTE.COM')
    ],
    ['empl_id', 'last_name', 'first_name', 'email_id']
)

masterDF = spark.createDataFrame(
    [
        (1, 'old1', 'old1', 'old1@DELOITTE.COM', '111', '20201231', 'I'),
        (108194, 'Seaton', 'Deena', 'DSEATON@DELOITTE2.COM', '112', '20201231', 'I')
    ],
    ['empl_id', 'last_name', 'first_name', 'email_id', 'wap_guid', 'date_from', 'dml_flag']
).select(col('empl_id'),col('last_name'),col('first_name'),col('email_id'))



d = strftime("%Y%m%d_%H%M%S", gmtime())

#rawDF.show(10,False)

ta = rawDF.alias('ta')
tb = masterDF.alias('tb')
#+-------+---------+----------+-------------------------------+-------+---------+----------+--------------------+--------+---------+--------+
#|empl_id|last_name|first_name|email_id                       |empl_id|last_name|first_name|email_id            |wap_guid|date_from|dml_flag|
#+-------+---------+----------+-------------------------------+-------+---------+----------+--------------------+--------+---------+--------+
#|306321 |Martinez |Felix     |FEMARTINEZ@DONOTUSEDELOITTE.COM|null   |null     |null      |null                |null    |null     |null    |
#|null   |null     |null      |null                           |1      |old1     |old1      |old1@DELOITTE.COM   |111     |20201231 |I       |
#|337756 |Wilcox   |Nathan    |NWILCOX@DELOITTE.COM           |null   |null     |null      |null                |null    |null     |null    |
#|108194 |Seaton   |Deena     |DSEATON@DELOITTE.COM           |108194 |Seaton   |Deena     |DSEATON@DELOITTE.COM|112     |20201231 |I       |
#+-------+---------+----------+-------------------------------+-------+---------+----------+--------------------+--------+---------+--------+
# ta.show(10,False)
# tb.show(10,False)
orclJoinedDf = ta.join(tb, ta.empl_id == tb.empl_id, 'full')\
    .filter((coalesce(col('ta.last_name'),lit('')) != coalesce(col('tb.last_name'),lit(''))) |
            (coalesce(col('ta.first_name'),lit('')) != coalesce(col('tb.first_name'),lit(''))) |
            (coalesce(col('ta.email_id'),lit('')) != coalesce(col('tb.email_id'),lit('')))) \
    .withColumn('operation', when(tb.empl_id.isNull(), 'insert').when(ta.empl_id.isNull(), 'delete').otherwise('update'))\
    .withColumn('from_date', lit(d))\
    .select(coalesce(col('ta.empl_id'),col('tb.empl_id')).alias('empl_id'),
            coalesce(col('ta.last_name'),col('tb.last_name')).alias('last_name'),
            coalesce(col('ta.first_name'),col('tb.first_name')).alias('first_name'),
            coalesce(col('ta.email_id'),col('tb.email_id')).alias('email_id'),
            col('from_date'),
            col('operation'))

orclJoinedDf.join(wdapDF, orclJoinedDf.email_id == wdapDF.login, 'left')\
    .select(col('empl_id'),col('last_name'),col('email_id'),col('empl_id'),col('from_date'),col('operation'),col('guid'))\
    .show(10,False)


# use sha2?
# rename all folders
# lambda: read parquet
# lambda: for each row call

    #.withColumn("new", coalesce(col('ta.last_name'),lit('')))
    #.where("ta.last_name <=> tb.last_name")
    #.filter( rawDF['last_name'].eqNullSafe(masterDF['last_name']))\
    #.filter((col('ta.last_name') ==  col('ta.first_name')))\
    #| (col('ta.first_name') <= col('tb
    # .first_name')) | (col('ta.email_id') != col('tb.email_id'))
    # .select(col('ta.empl_id'),col('ta.last_name'),col('ta.first_name'),col('ta.email_id'))


# zeorLine.withColumn("wap_guid", lit('B71574EDA6BA1D91E0532A061E0AC777'))\
#     .withColumn("date_from", lit(d))\
#     .withColumn("dml_flag", lit('INS')).show(20,False)

# dfRaw = zeorLine.union(dfRaw0)
#
# dfRaw.show(20,False)
# dfMaster.show(20,False)
#
# ta = dfRaw.alias('ta')
# tb = dfMaster.alias('tb')
#
# joined = ta.join(tb, ta.id == tb.id, 'full')\
#     .filter( (col('ta.col2') != col('tb.col2')) | (col('tb.id').isNull()))\
#     .withColumn("operation", when(tb.id.isNull(), 'insert').otherwise('update'))\
#     .select(col('ta.id'),col('ta.name'),col('ta.col1'),col('ta.col2'),col('operation'))
# joined.show(20,False)