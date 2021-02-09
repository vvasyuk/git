from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# dfRaw0 = spark.read.parquet("D:/work/tryout/coursera/aws/glue/parquet/raw/*")
# dfMaster = spark.read.parquet("D:/work/tryout/coursera/aws/glue/parquet/master/*")

firstLine = spark.createDataFrame(
    [
        (0, 'name0', 10, 20),
    ],
    ['id', 'name', 'col1', 'col2']
)

secondLine = spark.createDataFrame(
    [
        (0, 'name0', 10, 20),
    ],
    ['id', 'name', 'col1', 'col2']
)

p1 = time.time()
cachedFirst = firstLine.cache()
cachedSecond = secondLine.cache()

cachedFirst.drop(col('col2')).show(20,False)
cachedSecond.drop(col('col2')).show(20,False)
p2 = time.time()
print(p2-p1)

print(cachedFirst.collect() == cachedSecond.collect())
p3 = time.time()
print(p3-p2)



# dfRaw = zeorLine.union(dfRaw0)
#
# dfRaw.show(20,False)
# dfMaster.show(20,False)
#
# ta = dfRaw.alias('ta')
# tb = dfMaster.alias('tb')
#
# joined = ta.join(tb, ta.id == tb.id, 'full').filter( (col('ta.col2') != col('tb.col2')) | (col('tb.id').isNull())).withColumn("operation", when(tb.id.isNull(), 'insert').otherwise('update')).select(col('ta.id'),col('ta.name'),col('ta.col1'),col('ta.col2'),col('operation'))
# joined.show(20,False)