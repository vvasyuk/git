from pyspark.sql import SparkSession
from pyspark.sql.functions import col, lit
from pyspark.sql.types import IntegerType

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PivotTest").getOrCreate()
spark.sparkContext.setLogLevel("ERROR")

df = spark.createDataFrame(
    [
        ('Amount', '540-120-001', '540-120', 'USA07508.00.02.01.0110', 'Akyev, Serdar - 002072030004', 'SP3_P7_FY21', '0'),
        ('Amount', '531-270-001', '531-270', 'LOU07502.00.01.01.0148', 'Tony, Jessica - 216151543184', 'SP3_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'SPE14027.03.01.SW.SHUT', 'Wallace, Jeff - 002180980001', 'SP3_P7_FY21', '0'),
        ('Amount', '531-270-001', '531-270', 'COL07519.00.01.01.0110', 'Bijja, Swathi - 203683502707', 'SP3_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'DEF12397.02.01.07.0710', 'Knepp, Jeremy - 002026050003', 'SP3_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'AGR10541.00.01.01.0110', 'Suraktal, Phani - 002067840002', 'SP4_P7_FY21','0'),
        ('Amount', '531-250-005', '531-250', 'AFP10108.00.01.03.0310', 'Goglia, John - 002068610012', 'SP4_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'HOU12092.00.01.01.0110', 'Hart, Nick - 002038680017', 'SP4_P7_FY21', '0'),
        ('Amount', '531-250-005', '531-250', 'SPE07558.00.01.19.1910', 'Dodson, Donald - 002070960013', 'SP4_P8_FY21','0'),
        ('Amount', '531-250-005', '531-250', 'FED00240.00.02.02.0210', 'Conforti, Irene - 002160020005', 'SP4_P8_FY21','0'),
        ('Amount', '531-250-005', '531-250', 'HOU12092.02.01.08.0810', 'Sadowski, James - 002038680004', 'SP4_P8_FY21','0'),
        ('Amount', '540-120-001', '540-120', 'USA07508.00.02.01.0110', 'Akyev, Serdar - 002072030004', 'SP4_P8_FY21', '0'),
    ],
    ['WDAP_ACCOUNT','GL_ACCOUNT','GL_ACCOUNT_SHORT','LEVEL_','VENDOR_EMPLOYEE','TIME_DIM','HRS']
)

#df.show(20,False)

pivoted = df.withColumn('HRS', col('HRS').cast(IntegerType())).withColumn('space here', lit(''))\
    .groupBy("WDAP_ACCOUNT", "GL_ACCOUNT", "GL_ACCOUNT_SHORT", "LEVEL_", "VENDOR_EMPLOYEE", "space here").pivot("TIME_DIM").sum("HRS")
#pivoted.columnRenamed

pivotNoNulls = pivoted.na.fill(0)
pivotNoNulls.show(20,False)

pivotNoNulls.write.parquet("C:\\work\\tryout\\python\\python37Pandas\\sparkTest\\parq.parquet")
#pyspark.sql.utils.AnalysisException: Attribute name "space here" contains invalid character(s) among " ,;{}()\n\t=". Please use alias to rename it.;