from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")
d = strftime("%Y%m%d_%H%M%S", gmtime())

new = spark.createDataFrame(
    [(10, 'name10', 10)
    ,(20, 'name22', 10)
    ,(40, 'name40', 10)
     ],
    ['id', 'name', 'lkpId']
)

lkp = spark.createDataFrame(
    [(10, 'lkp10'),],
    ['id', 'value']
)

master = spark.createDataFrame(
    [(10, 'name10', 'lkp10', '20210212_120000'),
    (20, 'name20','lkp20', '20210212_120000'),
    (30, 'name30','lkp30', '20210212_120000'),
     ],
    ['id', 'name', 'lkp', 'date']
)

print("master")
master.show(10,False)

joined = new.join(lkp, new.lkpId == lkp.id, 'left').select(new['id'], new['name'], lkp['value'])
joinedWithDate = joined.withColumn('date', lit(d)).cache()

print("joinedWithDate")
joinedWithDate.show(10,False)

print("remainingMaster")
remainingMaster = master.join(joinedWithDate, ['id'], 'left_anti')
remainingMaster.show(10,False)

print("newMaster")
newMaster = joinedWithDate.union(remainingMaster).repartition(1).cache()
newMaster.show(10,False)

