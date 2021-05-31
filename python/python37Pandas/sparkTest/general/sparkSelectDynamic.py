from pyspark.sql import SparkSession
from pyspark.sql.functions import *
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('1', 'one', 'a'),
        ('2', 'two', 'b'),
        ('3', 'threee', 'c'),
    ],
    ['id','col1', 'col3']
)

df2 = spark.createDataFrame(
    [
        ('3', 'three', 'c'),
        ('4', 'fout', 'd'),
    ],
    ['id','col2', 'col4']
)

df = df.alias('df')
df2 = df2.alias('df2')

l = ["coalesce(col('df.id'), col('df2.id')).alias('account')",
     "coalesce(col('df.col1'), col('df2.col2')).alias('employee')",
     "concat_ws(',', 'df.col3', 'df2.col4').alias('error')"]

str1 = "coalesce(col('df.id'), col('df2.id'))," \
       "coalesce(col('df.col1'), col('df2.col2'))," \
       "concat_ws(',', 'df.col3', 'df2.col4')"

str2 = '''coalesce(col('df.id'), col('df2.id'))'''


mandatory_col = ['col1', 'col2', 'col3', 'col4']
str1 = ''
for ele in mandatory_col:
    str1 = str1 + '''coalesce(col('df.id'), col('df2.id'))'''
    #str1 = str1 + '''trim('abgc')'''



print(str1[:-5])

#dfJoined = df.join(df2, (df.id == df2.id), 'full').select([c for c in l])
#dfJoined = df.join(df2, (df.id == df2.id), 'full').select(coalesce(col('df.id'), col('df2.id')).alias('account'))
dfJoined = df.join(df2, (df.id == df2.id), 'full').select(expr(str2))

# .select(
#     coalesce(col('df.id'), col('df2.id')).alias('account'),
#     coalesce(col('df.col1'), col('df2.col2')).alias('employee'),
#     concat_ws(',', 'df.col3', 'df2.col4').alias('error')
# )

# .select(
#     coalesce(col('df.id'), col('df2.id')).alias('account'),
#     coalesce(col('df.col1'), col('df2.col2')).alias('employee'),
#     concat_ws(',', 'df.col3', 'df2.col4').alias('error')
# )

dfJoined.show(10,False)