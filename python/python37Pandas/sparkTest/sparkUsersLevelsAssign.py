from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

## logic
# dfProjects = spark.read.parquet("C:\\work\\project\\docs\\users_levels_assign\\data\\projects_ppmd").cache()
# dfProjectsPPMD = dfProjects.filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))
# dfProjectsPM = dfProjects.filter((col('PM_ID').isNotNull()) & (col('PM_ID') != ' '))
# groupedPPMD = dfProjectsPPMD.groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(PROJ_ID))")).alias("agg_list"))
# groupedPM = dfProjectsPM.groupBy("PM_ID").agg(concat_ws(",", expr("sort_array(collect_list(PROJ_ID))")).alias("agg_list"))
# # print(groupedPPMD.count())  # 38
# # print(groupedPM.count())    # 76
# dfUnioned = groupedPPMD.union(groupedPM)
# # print(dfUnioned.count())    # 114
# groupedUnioned = dfUnioned.groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(agg_list))")).alias("agg_list")).cache()
# print(groupedUnioned.count())    # 114
# groupedUnioned.show(10,False)

## logic2
# dfProjects = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\").filter(col('wap_id').isNotNull()).cache()
# print(dfProjects.count())    # 38?
#
# dfProjectsPPMD = dfProjects.filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))\
#      .groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPPMD.count())    # 38?
# dfProjectsPPMD.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('PM_ID').isNotNull()) & (col('PM_ID') != ' '))\
#      .groupBy("PM_ID").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())    # 46?
# dfProjectsPM.show(10,False)
#
# dfUnioned = dfProjectsPPMD.union(dfProjectsPM)\
#     .groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(agg_list))")).alias("agg_list")).cache()
# print(dfUnioned.count())    # 114

## logic2 check counts (2)?
dfProjects = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\").filter(col('wap_id').isNotNull()).cache()
print(dfProjects.count())    # 38985
cnt1 = dfProjects.filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))
print(cnt1.count())    # 6
cnt1.show(10,False)

# master levels
# dfLevelMaster = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\")
# dfLevelMaster_with_email = dfLevelMaster.filter
# dfLevelMaster.show(20,False)

# raw users
# dfUsersWdap = spark.read.parquet("c:\\work\\project\\data\\master\\wdap\\users\\")
# #dfLevelWdap_with_email = dfLevelWdap.filter
# dfUsersWdap.show(20,False)

# raw levels
# dfLevelsWdap = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\")
# # #dfLevelWdap_with_email = dfLevelWdap.filter
# dfLevelsWdap.show(20,False)

# investigate if PM_ID is empl_id
# --- projects
# dfProjects = spark.read.option("header", "true").csv("C:/work/project/data/testData/levels-attributes/Deloitte_Projects_w_Attributes.csv")\
#      .select(col('PROJ_ID'),col('PROJ_NAME'),col('PM_ID'),col('PPMD_ID')).repartition(1)
# # #dfProjects.printSchema()
# dfProjects.write.parquet("C:\\work\\project\\docs\\users_levels_assign\\data\\projects_ppmd")

# dfProjects = spark.read.parquet("C:\\work\\project\\docs\\users_levels_assign\\data\\projects_ppmd")\
#     .filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))
    # .filter((col('PM_ID').isNotNull()) & (col('PM_ID') != ' '))
#print(dfProjects.count())
# PM_ID
# dfProjects.show(40,False)

# --- users
# dfDistinctPPMD = dfProjects.select(col('PPMD_ID')).distinct()
# dfDistinctPM = dfProjects.select(col('PM_ID')).distinct()
#
# dfUsers = spark.read.option("header", "true").csv("C:/work/project/data/testData/users/users_20210319.csv")\
#     .select(col('EMPL_ID'),col('LAST_NAME'),col('FIRST_NAME'),col('EMAIL_ID')).cache()

# ppmd = dfDistinctPPMD.join(dfUsers, dfDistinctPPMD['PPMD_ID'] == dfUsers['EMPL_ID'], 'left').cache()
# emplCount = ppmd.filter(col('EMPL_ID').isNotNull()).count()
# ppmdCount = ppmd.filter(col('PPMD_ID').isNotNull()).count()
# print(str(ppmdCount) + ' ' + str(emplCount))

# pm = dfDistinctPM.join(dfUsers, dfDistinctPM['PM_ID'] == dfUsers['EMPL_ID'], 'left').cache()
# pmEmplCount = pm.filter(col('EMPL_ID').isNotNull()).count()
# pmCount = pm.filter(col('PM_ID').isNotNull()).count()
# print(str(pmCount) + ' ' + str(pmEmplCount))
# #ppmd.show(40,False)

#     .filter(col('EMPL_ID') == '00129569')   # PM_ID
#     #.filter(col('EMPL_ID') == '00107699')   # PPMD_ID
# # dfUsers.printSchema()
# dfUsers.show(40,False)


