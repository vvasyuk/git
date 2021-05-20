from pyspark.sql import SparkSession
from pyspark.sql.functions import *
import time

spark = SparkSession.builder.config("spark.sql.warehouse.dir", "file:///C:/temp").appName("ImportantStuff").getOrCreate()

# users
dfUsers = spark.read.parquet("C:/work/project/bugs/prod_12_bot_update/users")\
    .filter(col("empl_name").like("%Joana%")).cache()
#dfUsers.show(20,False)

# wdap_users
dfWdapUsers = spark.read.parquet("C:/work/project/bugs/prod_12_bot_update/wdap_users")\
    .filter(col("name").like("%Joana%")).cache()
#dfWdapUsers.select(col("name"),col("roleId"),col("groupIds")).show(20,False)

# roles
dfRoles = spark.read.parquet("C:/work/project/bugs/prod_12_bot_update/roles")
    #.filter(col("name").like("%Joana%")).cache()
#dfRoles.show(20,False)

dfUsers.createOrReplaceTempView("dfd");
dfWdapUsers.createOrReplaceTempView("dfw");
dfRoles.createOrReplaceTempView("dfrl");

dfRoleResult = spark.sql(f"""
with ddd as
(select 
  COALESCE(dfw.name,dfd.empl_name) as user_name
  ,dfw.roleid as role_remove 
  ,dfd.wdap_role as role_add
FROM dfd
  left outer join dfw
    on dfd.empl_email = dfw.login
where dfw.roleid != dfd.wdap_role
and dfw.roleid not in ('3','201','202','203','204','205','206'))
select
  '20210519' as action_date,
  'ADD' as action,
  user_name,
  role_add as user_group
from ddd
where role_add is not null
union all
select 
  '20210519' as action_date,
  'REMOVE' as action,
  user_name,
  role_remove as user_group
from ddd
where role_remove is not null
order by user_group, action
""").cache()

dfRoleResult.show(10,False)
