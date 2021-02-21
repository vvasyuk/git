import sys
sys.path[0] = 'C:\\work\\project\\data\\glueTest'
from pyspark.sql import SparkSession
from pyspark.context import SparkContext
from awsglue.context import GlueContext
from awsglue.transforms import *
from awsglue.dynamicframe import DynamicFrame
from pyspark.sql.types import *
from pyspark.sql import Row
spark = SparkSession \
    .builder \
    .appName("Glue-PyCharm-Example") \
    .config("spark.jars", "C:\\work\\tryout\\python\\glue\\venv\\AWSGlueETLPython-1.0.0-jar-with-dependencies.jar") \
    .config("spark.local.dir","C:\\Users\\jopa\\AppData\\Local\\Temp") \
    .getOrCreate()

sc = spark.sparkContext
glueContext = GlueContext(sc)
order_list = [
               ['1005', '623', 'YES', '1418901234', '75091'],\
               ['1006', '547', 'NO', '1418901256', '75034'],\
               ['1007', '823', 'YES', '1418901300', '75023'],\
               ['1008', '912', 'NO', '1418901400', '82091'],\
               ['1009', '321', 'YES', '1418902000', '90093']\
             ]

# Define schema for the order_list
order_schema = StructType([
                      StructField("order_id", StringType()),
                      StructField("customer_id", StringType()),
                      StructField("essential_item", StringType()),
                      StructField("timestamp", StringType()),
                      StructField("zipcode", StringType())
                    ])
df_orders = spark.createDataFrame(order_list, schema = order_schema)
#df_orders.show()

dyf_orders = DynamicFrame.fromDF(df_orders, glueContext, "dyf")
dyf_applyMapping = ApplyMapping.apply( frame = dyf_orders, mappings = [
  ("order_id","String","order_id","Long"),
  ("customer_id","String","customer_id","Long"),
  ("essential_item","String","essential_item","String"),
  ("timestamp","String","timestamp","Long"),
  ("zipcode","String","zip","Long")
])
dyf_applyMapping.printSchema()

inputGDF = glueContext.create_dynamic_frame_from_options(connection_type = "file", connection_options = {"paths": ["C:\\work\\project\\data\\\mappings\\cost_category.csv"]}, format = "csv")
inputGDF.toDF().show(10,False)
#outputGDF = glueContext.write_dynamic_frame.from_options(frame = inputGDF, connection_type = "s3", connection_options = {"path": "s3://my-bucket-name/glue_new_ex"}, format = "csv")