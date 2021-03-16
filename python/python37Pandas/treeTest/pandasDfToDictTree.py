import pandas as pd
from wdapTest.wdap.node import TreeNode
from wdapTest.wdap.node import *
from wdapTest.wdap.client import WDAPClient
import time
import os

# def make_map(df):
#     root = Node(0)
#     all_items = {}
#     for index, row in df.iterrows():
#         id = row['proj_id']
#         parent = row['prnt_proj_id']
#         if parent not in all_items:
#             all_items[parent] = Node(parent)
#             if isinstance(parent, float) and math.isnan(parent):
#                 root.childs.append(all_items[parent])
#         if id not in all_items:
#             wap_id_raw = row['wap_id']
#             wap_id = '' if isinstance(wap_id_raw, float) and math.isnan(wap_id_raw) else wap_id_raw
#             all_items[id] = Node(id, wap_id, str(row['proj_name']), 'USD', '',  True)
#         all_items[parent].name = row['proj_name']
#         all_items[parent].currency = 'USD'
#         all_items[parent].childs.append(all_items[id])
#     return root
# def make_map(list_child_parent):
#     has_parent = set()
#     all_items = {}
#     for child, parent, ext_id in list_child_parent:
#         if parent not in all_items:
#             all_items[parent] = {}
#         if child not in all_items:
#             all_items[child] = {}
#         all_items[parent][child] = all_items[child]
#         has_parent.add(child)
#
#     result = {}
#     for key, value in all_items.items():
#         if key not in has_parent:
#             result[key] = value
#     return result

# def make_map(df):
#     has_parent = set()
#     all_items = {}
#     for index, row in df.iterrows():
#         if row['parent'] not in all_items:
#             all_items[row['parent']] = {}
#         if row['id'] not in all_items:
#             all_items[row['id']] = {}
#         all_items[row['parent']][row['id']] = all_items[row['id']]
#         has_parent.add(row['id'])
#
#     result = {}
#     for key, value in all_items.items():
#         if key not in has_parent:
#             result[key] = value
#     print(result)
#     return result

# data0 = [
#     [1, ''],
#     [2, 1],
#     [3, 2]
# ]
# data = [
#     [13, '', '001'],
#     [2, 13, ''],
#     [3, 13, ''],
#     [4, 2, '']
# ]
# df = pd.DataFrame(data, columns = ['id', 'parent', 'ext_id'])
#df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/master.csv")
#print(df.to_string())

# raw DATAPOINT
# df = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/datapoint/raw/part-00000-26a0e33d-e0a0-4f57-a208-9061d322ef55.c000.snappy.parquet")[["proj_id", "proj_name", "sector", "accounts", "client_name"]]
# dfFiltered = df.loc[df['proj_id'] == 'NAV00380']
# # #df[['l1','l2','l3','l4','l5']] = df.PROJ_ID.str.split('.', expand=True)
# # #dfDatapointLevelUnique = df.l1.unique()
# # #dfFiltered = df.loc[df['PROJ_ID'].str.contains('CDC00005')]
# # dfFiltered = df.loc[df['PROJ_ID'].str.contains('.')]
# # # #df.PROJ_ID = df.PROJ_ID.str.replace('.',',')
# # # print(df.l1.unique())
# print(dfFiltered.to_string())

# WDAP
#dfWdap = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/wdap/raw_datapoint_levels")[["id", "name", "parentId"]]
#dfWdapFiltered = dfWdap.loc[dfWdap['name'] == 'NAV00380']
# dfWdapFiltered = dfWdap.loc[dfWdap['name'].str.contains('CDC00005')]
# #dfWdapFiltered = dfWdap.loc[dfWdap['name'].isin(dfDatapointLevelUnique)]
#print(dfWdapFiltered.head(10).to_string())
#print(dfWdapFiltered.to_string())

# # MASTER
# start = time.perf_counter()
# df = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/datapoint/part-00000-838790b5-3e16-4ce3-8df1-cf9053a1e8b5-c000.snappy.parquet", use_nullable_dtypes=True)
# end = time.perf_counter()
# print(f"reading parquet: {start - end:0.4f}")
# #old
# #df = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/datapoint/part-00000-dde741f0-cc81-46e8-b72f-3ac74bbd4024-c000.snappy.parquet", use_nullable_dtypes=True)
# #df = df.loc[df['proj_id'] == 'FDA00084']
# # df1 = df1.isna()
# # df1 = df1.where(df1.isna(), np.NaN)
# # print(df1.to_string())
# # print(df.dtypes)
# # df["ppmd_id"] = df['ppmd_id'].astype('int')
# # print(df.dtypes)
# # df = df.fillna('')
# #
# # print(df1.to_string())
#
# # # HOU12037, IOU12037, GOU12037, KOU12037
# # dfFiltered = df.loc[df['proj_id'].str.contains('HOU12037')\
# #                     | df['proj_id'].str.contains('IOU12037')\
# #                     | df['proj_id'].str.contains('GOU12037')\
# #                     | df['proj_id'].str.contains('KOU12037')\
# #                     | df['proj_id'].str.contains('SLHE')\
# #                     | df['proj_id'].str.contains('GOVT') \
# #                     | df['proj_id'].str.contains('CALIFORNIA') \
# #                     | df['proj_id'].str.contains('ICALIFORNIA') \
# #                     | df['proj_id'].str.contains('HUD') \
# #                     | df['proj_id'].str.contains('CALIFORNIA DEPT OF PUBLIC') \
# #                     | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP') \
# #                     | df['proj_id'].str.contains('ICALIFORNIA DEPT OF PUBLIC') \
# #                     ]
# # print(dfFiltered.sort_values(by=['proj_id']).to_string())
#
#
# # # local Test
# # df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/master_small.txt", dtype={'wap_id': str,'wap_prnt_id':str})
# # df1 = df.where(pd.notnull(df), None)
# # dfFiltered = df1.loc[df['proj_id'].str.contains('IOU12037.00.01.01.0110')\
# #                         | df['proj_id'].str.contains('SLHE')
# #                         # | df['proj_id'].str.contains('GOU12037.00.01.01.0110')\
# #                         # | df['proj_id'].str.contains('KOU12037.00.01.01.0110')
# # ]
# # dfFiltered2 = df1.loc[df['proj_id'].str.contains('HOU12037')\
# #                         | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP')\
# #                         | df['proj_id'].str.contains('HUD')\
# #                         | df['proj_id'].str.contains('CIVIL GOVT')
# # ]
# # print(df.to_string())
#
# # df1 = df.loc[df['proj_id'] == 'FDA00084']
# # df1.info()
# # print(df1.to_string())
#



# data = [
#     ['TWVA00001.00.01', 'Test 3', '286251', '285972', 'TWVA00001.00', 'XCIVIL GOVT', 'XHUD', 'XHOUSING AND URBAN DEVELOPMENT', np.nan,np.nan],
#     ['TWVA00001.00', 'Test 2 ', '286251', '285972', 'TWVA00001', 'XCIVIL GOVT', 'XHUD', 'XHOUSING AND URBAN DEVELOPMENT', None,None],
#     ['TWVA00001', 'Test 1', '286251', '285972', None, 'XCIVIL GOVT', 'XHUD', 'XHOUSING AND URBAN DEVELOPMENT', None,None],
#     #['HOU12037', 'HOU12037', '286251', '285972', None, 'XCIVIL GOVT', 'XHUD', 'XHOUSING AND URBAN DEVELOPMENT', None,None],
#     #['HOU12037.00', 'HOU12037.00', '286251', '285972', 'HOU12037', 'XCIVIL GOVT', 'XHUD','XHOUSING AND URBAN DEVELOPMENT', None, None],
#     #['HOU12037.00.01', 'StratMan New Core TO 05', '286251', '285972', 'HOU12037.00', 'XCIVIL GOVT', 'XHUD','XHOUSING AND URBAN DEVELOPMENT', None, None],
#     #['IOU12037.00.01.01.0110', 'AEM Phase 2I - L&C', '286251', '285972', 'IOU12037.00.01.01', 'XSLHE', 'XICALIFORNIA','XICALIFORNIA DEPT OF PUBLIC', None, None],
#     #['GOU12037.00.01.01.0110', 'AEM Phase 2G - L&C', '286251', '285972', 'GOU12037.00.01.01', 'XSLHE', 'XCALIFORNIA','XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
#     #['KOU12037.00.01.01.0110', 'AEM Phase 2K - L&C', '286251', '285972', 'KOU12037.00.01.01', 'XSLHE', 'XCALIFORNIA','XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
#     ['XCALIFORNIA', None, None, None, None, None, None, None, '68', '42'],
#     ['XCALIFORNIA DEPT OF CONSUMER AFFAIRS', None, None, None, None, None, None, None, '210', '68'],
#     ['XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None, None, None, None, None, None, '212', '68'],
#     ['XCIVIL GOVT', None, None, None, None, None, None, None, '44', '1'],
#     #['XHOUSING AND URBAN DEVELOPMENT', None, None, None, None, None, None, None, '369', '103'],
#     ['XHUD', None, None, None, None, None, None, None, '103', '44'],
#     ['XSLHE', None, None, None, None, None, None, None, '42', '1'],
#     ['XUNIV CALIFORNIA', None, None, None, None, None, None, None, '130', '42'],
#     ['XUNIVERSITY OF CALIFORNIA, REGE', None, None, None, None, None, None, None, '608', '130'],
#     ['XWATER RESOURCES, CALIFORNIA DE', None, None, None, None, None, None, None, '663', '68'],
# ]
#df0 = pd.DataFrame(data, columns=['proj_id', 'proj_name', 'ppmd_id', 'pm_id', 'prnt_proj_id', 'sector', 'accounts', 'cust_name', 'wap_id', 'wap_prnt_id'])
# # df0.info()
#print(df0.to_string())
# #
# # df0 = df0.where(pd.notnull(df0), None)
# # #print(df0.to_string())
# #
#
# #print(df.to_string())
#
# test = df.loc[df['proj_id'] == 'TWVA00001']
#               # | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP')\
#               # | df['proj_id'].str.contains('HUD') | df['proj_id'].str.contains('CIVIL GOVT')]
# print(test.to_string())
    #filter(col('proj_id').like('%TWVA00001%'))

df = pd.read_parquet("C:\\work\\project\\data\\master\\datapoint\\levels\\", use_nullable_dtypes=True)
# df = df.tail(10)
# print(df.to_string())
# df = df.loc[df['proj_id'].str.contains('HOU12037') | df['proj_id'].str.contains('HUD') | df['proj_id'].str.contains('CIVIL GOVT') | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP')]
# df = df.loc[(df['proj_id'].str.contains('IAMTEST')) & (df['proj_name'] == '')]
start = time.perf_counter()
res = get_node(df)
end = time.perf_counter()
print(f"create tree: {start - end:0.4f}")
res.traverse(1)
start = time.perf_counter()
result_until_wap = []
res.traverse_until_wap_helper(result_until_wap)
end = time.perf_counter()
print(f"format tree: {start - end:0.4f}")
print(result_until_wap)

# start = time.perf_counter()
# wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
# print(result_until_wap)
# xml = wdap.create_levels(result_until_wap, sim=False)
# end = time.perf_counter()
# print(f"create/send xml: {start - end:0.4f}")
#print(xml)
