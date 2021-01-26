import pandas as pd

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

data0 = [
    [1, ''],
    [2, 1],
    [3, 2]
]
data = [
    [13, '', '001'],
    [2, 13, ''],
    [3, 13, ''],
    [4, 2, '']
]
df = pd.DataFrame(data, columns = ['id', 'parent', 'ext_id'])

class Node(object):
    def __init__(self, v, has_parent=False):
        self.value = v
        self.has_parent = has_parent
        self.childs = []

    def traverse(self, lvl):
        print(" "*lvl + str(self.value))
        for c in self.childs:
            c.traverse(lvl+1)

def make_map(df):
    root = Node(0)
    all_items = {}
    for index, row in df.iterrows():
        if row['parent'] not in all_items:
            all_items[row['parent']] = Node(row['parent'])
        if row['id'] not in all_items:
            all_items[row['id']] = Node(row['id'], True)
        all_items[row['parent']].childs.append(all_items[row['id']])

    for key, value in all_items.items():
        if not value.has_parent:
            root.childs.append(value)
    return root

res = make_map(df)
res.traverse(1)