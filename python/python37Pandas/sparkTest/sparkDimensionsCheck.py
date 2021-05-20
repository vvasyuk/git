from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# account_list
dfAccList = spark.read.parquet("C:/work/project/test/dimensions_check/account_list")
dfAccList.show(10,False)

# dimensions_list
#dfDimList = spark.read.parquet("C:/work/project/test/dimensions_check/dimensions_list")
#dfDimList.show(100,False)

# levels_list
#dfLevelsList = spark.read.parquet("C:/work/project/test/dimensions_check/levels_list")
#dfLevelsList.show(100,False)

# sheets_list
#dfSheetsList = spark.read.parquet("C:/work/project/test/dimensions_check/sheets_list")
#dfSheetsList.show(100,False)
