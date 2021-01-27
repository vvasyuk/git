import pandas as pd
import math

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
df = pd.read_csv("c:/Users/jopa/Downloads/data/testData/levels/datapoint/master.csv")
print(df.to_string())

class Node(object):
    def __init__(self, id, wap_id='', name='', currency='', shortName='', has_parent=False):
        self.id = id
        self.wap_id = wap_id
        self.name = name
        self.currency = currency
        self.shortName = shortName
        self.has_parent = has_parent
        self.childs = []

    def traverse(self, lvl, result):
        print(" "*lvl + str(self.id))
        tmp_result = {'id': self.wap_id,'name': self.name,'currency': self.currency,'shortName': self.shortName, 'children': []}
        result.append(tmp_result)
        for c in self.childs:
            c.traverse(lvl+1, tmp_result['children'])

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
        all_items[parent].name = row['proj_name']
        all_items[parent].currency = 'USD'
        all_items[parent].childs.append(all_items[id])
    return root

res = make_map(df)
result = []
res.traverse(1, result)

print(result[0]['children'][0]['children'])

# result = []
# result.append({'proj_name': 'name1', 'children': []})
# child = result[0]['children']
# child.append({'proj_name': 'name11', 'children': []})
# print(result)