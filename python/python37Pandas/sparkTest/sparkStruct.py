import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType, IntegerType
from pyspark.sql.functions import col, lit, struct

spark = SparkSession.builder.master("local[1]") \
    .appName('SparkByExamples.com') \
    .getOrCreate()

df = spark.createDataFrame(
    [
        (108194, 'Seaton', 'Deena', 'DSEATON@DELOITTE.COM'),
        (306321, 'Martinez', 'Felix', 'FEMARTINEZ@DONOTUSEDELOITTE.COM'),
        (337756, 'Wilcox', 'Nathan', 'NWILCOX@DELOITTE.COM')
    ],
    ['empl_id', 'last_name', 'first_name', 'email_id']
)
#df.show(20,False)

dfStruct = df.withColumn("my_struct",struct(col("last_name").alias('col1'),col("first_name"))).drop("last_name", "first_name")
dfStruct = df.withColumn("my_struct2",struct(col("my_struct.last_name").alias('col11')))
dfStruct.show(20,False)
dfStruct.printSchema()

# structureData0 = [
#     (("James0","","Smith"),"36636","M",3100),
#     (("Michael0","Rose",""),"40288","M",4300),
#     (("Robert0","","Williams"),"42114","M",1400),
#     (("Maria","Anne","Jones"),"39192","F",5500),
#     (("Jen","Mary","Brown"),"","F",-1)
#   ]
#
# structureData = [
#     (("James","","Smith"),"36636","M",3100),
#     (("Michael","Rose",""),"40288","M",4300),
#     (("Robert","","Williams"),"42114","M",1400),
#     (("Maria","Anne","Jones"),"39192","F",5500),
#     (("Jen","Mary","Brown"),"","F",-1)
#   ]
# structureSchema = StructType([
#         StructField('name', StructType([
#              StructField('firstname', StringType(), True),
#              StructField('middlename', StringType(), True),
#              StructField('lastname', StringType(), True)
#              ])),
#          StructField('id', StringType(), True),
#          StructField('gender', StringType(), True),
#          StructField('salary', IntegerType(), True)
#          ])
#
# df0 = spark.createDataFrame(data=structureData0,schema=structureSchema)
# df2 = spark.createDataFrame(data=structureData,schema=structureSchema)
# # df2.printSchema()
# # df2.show(truncate=False)
# # df0.show(truncate=False)
#
# res = df0.join(df2, (df0.id == df2.id) & (df0.name != df2.name), 'left')
# res.show(truncate=False)

