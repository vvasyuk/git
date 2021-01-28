import pandas as pd
import math

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
# df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/projects_20210125.csv")#[["PROJ_ID", "PROJ_NAME", "SECTOR", "ACCOUNTS", "CLIENT_NAME"]]
# #df[['l1','l2','l3','l4','l5']] = df.PROJ_ID.str.split('.', expand=True)
# #dfDatapointLevelUnique = df.l1.unique()
# #dfFiltered = df.loc[df['PROJ_ID'].str.contains('CDC00005')]
# dfFiltered = df.loc[df['PROJ_ID'].str.contains('.')]
# # #df.PROJ_ID = df.PROJ_ID.str.replace('.',',')
# # print(df.l1.unique())
# print(dfFiltered.to_string())

# WDAP
# dfWdap = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/wdap/raw_datapoint_levels")[["id", "name", "parentId"]]
# #dfWdapFiltered = dfWdap.loc[dfWdap['name'] == 'CDC00005']
# dfWdapFiltered = dfWdap.loc[dfWdap['name'].str.contains('CDC00005')]
# #dfWdapFiltered = dfWdap.loc[dfWdap['name'].isin(dfDatapointLevelUnique)]
# print(dfWdapFiltered.head(10).to_string())

# MASTER
# df = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/datapoint/part-00000-325a044e-a782-43be-8cac-91be1a312ea4-c000.snappy.parquet")
# dfFiltered = df.loc[df['proj_id'].str.contains('CDC00005') | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP')]
# print(dfFiltered.to_string())


# # local Test
df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/master_small.txt", dtype={'wap_id': str,'wap_prnt_id':str})
df1 = df.where(pd.notnull(df), None)
print(df1.to_string())

class Node(object):
    def __init__(self, id, wap_id='', name='', currency='', shortName='', has_parent=False):
        self.id = id
        self.wap_id = wap_id
        self.name = name
        self.currency = currency
        self.shortName = shortName
        self.has_parent = has_parent
        self.childs = []

    def traverse(self, lvl):
        print(" "*lvl + f"{self.id}")
        for c in self.childs:
            c.traverse(lvl+1)

    def traverse_into_dict(self, result):
        tmp_result = {'id': self.wap_id,'name': self.name,'currency': self.currency,'shortName': self.shortName, 'children': []}
        result.append(tmp_result)
        for c in self.childs:
            c.traverse_into_dict(tmp_result['children'])

def make_map(df):
    root = Node(0)
    all_items = {}
    for index, row in df.iterrows():
        id = row['proj_id']
        parent = row['prnt_proj_id']
        if parent not in all_items:
            all_items[parent] = Node(parent)
            if isinstance(parent, float) and math.isnan(parent):
                root.childs.append(all_items[parent])
        if id not in all_items:
            wap_id_raw = row['wap_id']
            wap_id = '' if isinstance(wap_id_raw, float) and math.isnan(wap_id_raw) else wap_id_raw
            all_items[id] = Node(id, wap_id, str(row['proj_name']), 'USD', '',  True)
        if id in all_items:
            all_items[id].name = row['proj_name']
            all_items[id].currency = 'USD'
        all_items[parent].childs.append(all_items[id])
    return root

res = make_map(df)
res.traverse(1)
result = []
res.traverse_into_dict(result)
print(result[0]['children'][0]['children'])
