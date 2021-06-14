from pyspark.sql import SparkSession
from pyspark.sql.functions import *


def dfNotAndIn(df_dp,df_wp,cond,dp_col,wp_col,entity):
    df_dp.show(5,False)
    df_wp.show(5,False)
    joined = df_dp.alias('df').join(df_wp.alias('df2'), cond, 'left').cache()
    _not_in = joined.filter(col(f"df2.{wp_col}").isNull()).select('df.*').withColumn("error", concat(col(f"df.{dp_col}"), lit(f" unmatched in wdap {entity}"))).cache()
    _in = joined.filter(col(f"df2.{wp_col}").isNotNull())
    print(f"{entity} not in count: {_not_in.count()}")
    _not_in.show(100,False)
    return _not_in, _in


spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
date = '20210521'

dfRaw = spark.read.parquet("C:\\work\\project\\bugs\\dev_02_vendor_dollars\\in\\ok").withColumn('pk', monotonically_increasing_id()).cache()
dfRaw.printSchema()
#.filter(col('vendor_employee') == 'ABOH, MICHAEL - 002242270003')
#dfRaw.select(col('vendor_employee'),col('amt')).show(5,False)
# dfRaw.show(50,False)

print("Actual Hours matching with wdap accounts")
dfAccList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list").select(col('code'))
(dfAccNotIn, dfAccIn) = dfNotAndIn(df_dp=dfRaw,df_wp=dfAccList,cond=[(dfRaw['wdap_account'] == dfAccList['code'])],dp_col='wdap_account',wp_col='code',entity='accounts')
dfAccIn = dfAccIn.select('df.pk')

# dfAccNotIn.show(40,False)

# dfBad = spark.read.parquet("C:\\work\\project\\bugs\\dev_02_vendor_dollars\\bad").filter(col('amt').isNull())
# dfBad.show(40,False)
