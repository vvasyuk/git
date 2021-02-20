from pyspark.sql import SparkSession
from pyspark.sql.functions import col,lit,udf,when,nanvl,coalesce
from time import gmtime, strftime

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("test").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")
d = strftime("%Y%m%d_%H%M%S", gmtime())

dfVendor = spark.read.parquet("c:/Users/jopa/Downloads/data/testData/vendor_hours/transform/").cache()
#dfVendorDollars.printSchema()
dfVendor.show(5,False)

# dupVendors = dfVendor.groupBy('vendor_employee', 'cost_category', 'subperiod_code').count()
# dupVendors.show(20,False)

#dupVendors.show(5,False)