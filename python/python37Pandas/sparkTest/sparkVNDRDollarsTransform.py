from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("test").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")
d = strftime("%Y%m%d_%H%M%S", gmtime())

dfVendorDollars = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/vendor_dollars/master/").cache()
#dfVendorDollars.printSchema()
dfVendorDollars.show(5,False)

# dupVendors = dfVendorDollars.groupBy('vendor_employee', 'cost_category', 'subperiod_code').count()
# dupVendors.show(20,False)

#dupVendors.show(5,False)