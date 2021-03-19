from pyspark.sql import SparkSession
from pyspark.sql.functions import col
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

# df1 = spark.createDataFrame(
#     [(1, "a", 2.0),
#      (2, "b", 3.0),
#      (3, "c", 3.0)],
#     ("x1", "x2", "x3"))
#
# df2 = spark.createDataFrame(
#     [(11, "a", -1.0),
#      (2, "b", 0.0)],
#     ("x1", "x2", "x3"))
#
# df = df1.join(df2, (df1.x1 == df2.x1) | (df1.x2 == df2.x2))
# df.show()

df = spark.createDataFrame(
    [
        ('Amount', '540-120-001', '540-120', 'USA07508.00.02.01.0110', 'Akyev, Serdar - 002072030004', 'SP3_P7_FY21', '0'),
        ('Amount', '531-270-001', '531-270', 'LOU07502.00.01.01.0148', 'Tony, Jessica - 216151543184', 'SP3_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'SPE14027.03.01.SW.SHUT', 'Wallace, Jeff - 002180980001', 'SP3_P7_FY21', '0'),
    ],
    ['WDAP_ACCOUNT','GL_ACCOUNT','GL_ACCOUNT_SHORT','LEVEL_','VENDOR_EMPLOYEE','TIME_DIM','HRS']
)

dfMap = spark.createDataFrame(
    [
        ('540-120-001', 'one'),
        ('540-120', 'oneone'),
        ('531-270', 'two'),
        ('531-250-005', 'three'),
    ],
    ['ID','MAPPED']
)

dfJoined = df.join(dfMap,  (df.GL_ACCOUNT == dfMap.ID) | (df.GL_ACCOUNT_SHORT == dfMap.ID)).select(df['GL_ACCOUNT'], df['GL_ACCOUNT_SHORT'], dfMap['MAPPED'])

dfJoined.show(10,False)