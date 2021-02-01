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
# df = pd.read_parquet("c:/Users/jopa/Downloads/data/testData/levels/datapoint/part-00000-dde741f0-cc81-46e8-b72f-3ac74bbd4024-c000.snappy.parquet")
# # HOU12037, IOU12037, GOU12037, KOU12037
# dfFiltered = df.loc[df['proj_id'].str.contains('HOU12037')\
#                     | df['proj_id'].str.contains('IOU12037')\
#                     | df['proj_id'].str.contains('GOU12037')\
#                     | df['proj_id'].str.contains('KOU12037')\
#                     | df['proj_id'].str.contains('SLHE')\
#                     | df['proj_id'].str.contains('GOVT') \
#                     | df['proj_id'].str.contains('CALIFORNIA') \
#                     | df['proj_id'].str.contains('ICALIFORNIA') \
#                     | df['proj_id'].str.contains('HUD') \
#                     | df['proj_id'].str.contains('CALIFORNIA DEPT OF PUBLIC') \
#                     | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP') \
#                     | df['proj_id'].str.contains('ICALIFORNIA DEPT OF PUBLIC') \
#                     ]
# print(dfFiltered.sort_values(by=['proj_id']).to_string())


# # local Test
df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/master_small.txt", dtype={'wap_id': str,'wap_prnt_id':str})
df1 = df.where(pd.notnull(df), None)
dfFiltered = df1.loc[df['proj_id'].str.contains('IOU12037.00.01.01.0110')\
                        | df['proj_id'].str.contains('SLHE')
                        # | df['proj_id'].str.contains('GOU12037.00.01.01.0110')\
                        # | df['proj_id'].str.contains('KOU12037.00.01.01.0110')
]
dfFiltered2 = df1.loc[df['proj_id'].str.contains('HOU12037')\
                        | df['proj_id'].str.contains('HOUSING AND URBAN DEVELOP')\
                        | df['proj_id'].str.contains('HUD')\
                        | df['proj_id'].str.contains('CIVIL GOVT')
]
print(dfFiltered.to_string())

class Node(object):
    def __init__(self, id, wap_id='', name='', currency='', shortName='', sector='', account='', cust_name='', has_parent=False):
        self.id = id
        self.wap_id = wap_id
        self.name = name
        self.currency = currency
        self.shortName = shortName
        self.sector = sector
        self.account = account
        self.cust_name = cust_name
        self.has_parent = has_parent
        self.childs = []

    def toMap(self, id):
        return {'id': id, 'name': self.id, 'currency': self.currency, 'shortName': self.shortName, 'children': []}

    def add_child_if_not_exists(self, node):
        if not [n.id for n in self.childs].__contains__(node.id):
            self.childs.append(node)

    def traverse(self, lvl):
        print(" "*lvl + f"{self.id}: {self.wap_id}: {self.name}")
        for c in self.childs:
            c.traverse(lvl+1)

    def traverse_into_dict(self, result):
        tmp_result = self.toMap(self.id)
        result.append(tmp_result)
        for c in self.childs:
            c.traverse_into_dict(tmp_result['children'])

    def traverse_until_wap_helper(self, result):
        for c in self.childs:
            c.traverse_until_wap_into_dict(result, False, "root")
        return result

    def traverse_until_wap_into_dict(self, result, isStarted, prev_wap):
        def _iterate(id):
            tmp_result = self.toMap(id)
            result.append(tmp_result)
            for c in self.childs:
                c.traverse_until_wap_into_dict(tmp_result['children'], isStarted, self.wap_id)
        if isStarted:
            _iterate(self.wap_id)
        elif prev_wap == "root" and (self.wap_id is None or self.wap_id == ''):
            isStarted = True
            _iterate(self.wap_id)
        elif not isStarted:
            if (self.wap_id is None or self.wap_id == '') and prev_wap is not None:
                isStarted = True
                _iterate(prev_wap)
            else:
                for c in self.childs:
                    c.traverse_until_wap_into_dict(result, isStarted, self.wap_id)

def make_map(df):
    root = Node("root")
    all_items = {}

    def _get_node(id):
        if id not in all_items:
            all_items[id] = Node(id=id)
        return all_items[id]

    def _link_top_levels(id, sector, account, cust_name):
        (cust_name_obj, account_obj, sector_obj) = (_get_node(cust_name), _get_node(account), _get_node(sector))
        sector_obj.add_child_if_not_exists(account_obj)
        account_obj.add_child_if_not_exists(cust_name_obj)
        cust_name_obj.add_child_if_not_exists(all_items[id])
        root.add_child_if_not_exists(all_items[sector])

    for index, row in df.iterrows():
        (id,name,ppmd,pm,parent,sector,account,cust_name,wap_id,wap_prnt_id) = row
        if id in all_items:
            node_obj = all_items[id]
            if name is not None:
                node_obj.name = name
            node_obj.wap_id = wap_id
            node_obj.currency = 'USD'
        if id not in all_items:
            all_items[id] = Node(id=id, wap_id=wap_id, name=name, currency='USD', shortName='', sector=sector, account=account, cust_name=cust_name, has_parent=True)
        if parent not in all_items:
            if parent is None and sector is not None and account is not None and cust_name is not None:
                _link_top_levels(id, sector, account, cust_name)
            else:
                all_items[parent] = Node(id=parent, wap_id=wap_id, name=name, currency='USD', shortName='', sector=sector, account=account, cust_name=cust_name, has_parent=False)
                all_items[parent].add_child_if_not_exists(all_items[id])
        if parent in all_items:
            all_items[parent].add_child_if_not_exists(all_items[id])

    def _substract_last_dot(input):
        splitted = input.split(".")
        new_parent_id = splitted[:len(splitted) - 1]
        new_parent_id_str = '.'.join([str(elem) for elem in new_parent_id])
        return new_parent_id_str

    def _generate_parent(node):
        if not node.has_parent and str(node.id).__contains__('.'):
            new_parent_id_str = _substract_last_dot(str(node.id))
            if new_parent_id_str in all_items:
                all_items[new_parent_id_str].add_child_if_not_exists(node)
            else:
                parent_obj = Node(id=new_parent_id_str, wap_id=node.wap_id, name=node.id, currency='USD', shortName='', sector=node.sector, account=node.account, cust_name=node.cust_name, has_parent=False)
                parent_obj.add_child_if_not_exists(node)
                node.has_parent = True
                all_items[new_parent_id_str]=parent_obj
                _generate_parent(parent_obj)
        elif not str(node.id).__contains__('.') and node.sector and node.account and node.cust_name:
            _link_top_levels(node.id, node.sector, node.account, node.cust_name)

    for (k, node) in list(all_items.items()):
        _generate_parent(node)

    return root

res = make_map(dfFiltered)
res.traverse(1)
# result = []
# res.traverse_into_dict(result)
# print(result[0]['children'])

result_until_wap = []
res.traverse_until_wap_helper(result_until_wap)
print(result_until_wap)