from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("PopularMovies").getOrCreate()

# raw oracle
dfUsers = spark.read.parquet("c:\\work\\project\\data\\raw\\datapoint\\users_levels_assign\\")
#dfProjects.filter(col("ROLE_ADDED_AS") == "PPMD").show(100,False)

# raw levels wdap
dfWpLevels = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\").select(col("id"), col("name"))
# dfLevels.show(20,False)

# raw users wdap
#dfWpUsers = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\users\\")#.select(col("id"),col("login"))
#dfUsers.show(20,False)


# logic
dfLevelsJoin = dfUsers.join(dfWpLevels, dfUsers['levels'] == dfWpLevels['name'], 'left').cache()
dfLevelsJoined = dfLevelsJoin.filter(col('name').isNotNull())
dfLevelsJoinedNot = dfLevelsJoin.filter(col('name').isNull())

# dfLevelsJoined.show(5,False)
rows = dfLevelsJoinedNot.agg(concat_ws(",", expr("sort_array(collect_list(levels))")).alias("agg_list")).collect()

print("errors")
for row in rows:
    print(row['agg_list'])

# dfLevelsJoined.show(1000,False)













# +-------------------------+------------------+--------------------------+---------------+
# |LEVELS                   |LAST_FIRST_NAME   |UPDATE_DATE               |ROLE_ADDED_AS  |
# +-------------------------+------------------+--------------------------+---------------+
# |FDA                      |Vera,Hewitt       |2021-03-30 21:37:37.866277|EFA SECTOR LEAD|
# |FDA                      |Vera,Hewitt       |2021-03-30 21:38:13.806152|EFA SECTOR LEAD|
# |Anoka-Hennepin Independen|Oliver,Wright     |2021-03-30 21:37:37.866277|EFA SECTOR LEAD|
# |Engagements              |Mathis,Joanne     |2021-03-30 21:37:37.83855 |EFA SECTOR LEAD|

# |CAL00529.00.01.01.0110|Nicole,Rose      |2021-03-30 21:37:37.742058|Engagement Manager|
# |AIR00113.02.02        |Adora,Nathan     |2021-03-30 21:37:37.742058|Engagement Manager|
# |LSC00022.00.01        |Javier,Thornton  |2021-03-30 21:37:37.742058|Engagement Manager|
# |PEN00279.00.02        |Vicky,Dixon      |2021-03-30 21:37:37.742058|Engagement Manager|

# |LOU00142.00.01.01.0110|Payne,Una        |2021-03-30 21:37:37.724179|PPMD         |
# |FED00235.00.01        |Milford,Claire   |2021-03-30 21:37:37.724179|PPMD         |
# |USA11004.00.01        |Edmunds,Trevor   |2021-03-30 21:37:37.724179|PPMD         |
# |NAV11734.04.02.01     |Allan,Elizabeth  |2021-03-30 21:37:37.724179|PPMD         |
# +----------------------+-----------------+--------------------------+-------------+

#dfProjects.select(col("ROLE_ADDED_AS")).distinct().show(100,False)
# +------------------+
# |ROLE_ADDED_AS     |
# +------------------+
# |PPMD              |
# |EFA SECTOR LEAD   |
# |Engagement Manager|
# +------------------+
# dfProjects.printSchema()


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
# dfProjects.printSchema()
# print(dfProjects.count())    # 38?

# dfProjectsPPMD = dfProjects.filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))\
#      .groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPPMD.count())    # 38?
# dfProjectsPPMD.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('PM_ID').isNotNull()) & (col('PM_ID') != ' '))\
#      .groupBy("PM_ID").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())    # 46?
# dfProjectsPM.show(10,False)

### new cols
# dfProjectsPM = dfProjects.filter((col('ppmd_del_id').isNotNull()) & (col('ppmd_del_id') != ' ') & (col('ppmd_del_id') != ''))\
#      .groupBy("ppmd_del_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('pm_del_id').isNotNull()) & (col('pm_del_id') != ' ') & (col('pm_del_id') != ''))\
#      .groupBy("pm_del_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('efa_id').isNotNull()) & (col('efa_id') != ' ') & (col('efa_id') != ''))\
#      .groupBy("efa_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('lcsp_id').isNotNull()) & (col('lcsp_id') != ' ') & (col('lcsp_id') != ''))\
#      .groupBy("lcsp_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('sectorlead_id').isNotNull()) & (col('sectorlead_id') != ' ') & (col('sectorlead_id') != ''))\
#      .groupBy("sectorlead_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)
#
# dfProjectsPM = dfProjects.filter((col('efa_acct_id').isNotNull()) & (col('efa_acct_id') != ' ') & (col('efa_acct_id') != ''))\
#      .groupBy("efa_acct_id").agg(concat_ws(",", expr("sort_array(collect_list(wap_id))")).alias("agg_list")).cache()
# print(dfProjectsPM.count())
# dfProjectsPM.show(10,False)

#
# dfUnioned = dfProjectsPPMD.union(dfProjectsPM)\
#     .groupBy("PPMD_ID").agg(concat_ws(",", expr("sort_array(collect_list(agg_list))")).alias("agg_list")).cache()
# print(dfUnioned.count())    # 114

## logic2 check counts (2)?
# dfProjects = spark.read.parquet("c:\\work\\project\\data\\master\\datapoint\\levels\\").filter(col('wap_id').isNotNull()).cache()
# print(dfProjects.count())    # 38985
# cnt1 = dfProjects.filter((col('PPMD_ID').isNotNull()) & (col('PPMD_ID') != ' '))
# print(cnt1.count())    # 6
# cnt1.show(10,False)

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

# PPMD_DEL_ID
# PM_DEL_ID
# EFA_ID
# LCSP_ID
# SECTORLEAD_ID
# EFA_ACCT_ID

# 'PPMD_DEL_ID', 'PM_DEL_ID', 'EFA_ID', 'LCSP_ID', 'SECTORLEAD_ID', 'EFA_ACCT_ID'

# dfProjects = spark.read.parquet("c:\\work\\project\\data\\raw\\wdap\\levels\\")\
#     .filter((col('PPMD_DEL_ID').isNotNull()) & (col('PPMD_DEL_ID') != ' '))
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


