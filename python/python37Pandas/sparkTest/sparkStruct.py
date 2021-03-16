import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

spark = SparkSession.builder.master("local[1]") \
    .appName('SparkByExamples.com') \
    .getOrCreate()

structureData0 = [
    (("James0","","Smith"),"36636","M",3100),
    (("Michael0","Rose",""),"40288","M",4300),
    (("Robert0","","Williams"),"42114","M",1400),
    (("Maria","Anne","Jones"),"39192","F",5500),
    (("Jen","Mary","Brown"),"","F",-1)
  ]

structureData = [
    (("James","","Smith"),"36636","M",3100),
    (("Michael","Rose",""),"40288","M",4300),
    (("Robert","","Williams"),"42114","M",1400),
    (("Maria","Anne","Jones"),"39192","F",5500),
    (("Jen","Mary","Brown"),"","F",-1)
  ]
structureSchema = StructType([
        StructField('name', StructType([
             StructField('firstname', StringType(), True),
             StructField('middlename', StringType(), True),
             StructField('lastname', StringType(), True)
             ])),
         StructField('id', StringType(), True),
         StructField('gender', StringType(), True),
         StructField('salary', IntegerType(), True)
         ])

df0 = spark.createDataFrame(data=structureData0,schema=structureSchema)
df2 = spark.createDataFrame(data=structureData,schema=structureSchema)
# df2.printSchema()
# df2.show(truncate=False)
# df0.show(truncate=False)

res = df0.join(df2, (df0.id == df2.id) & (df0.name != df2.name), 'left')
res.show(truncate=False)

