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

def make_map(df):
    has_parent = set()
    all_items = {}
    for index, row in df.iterrows():
        if row['parent'] not in all_items:
            all_items[row['parent']] = {}
            all_items[row['parent']]['child'] = {}
        if row['id'] not in all_items:
            all_items[row['id']] = {}
            all_items[row['id']]['child'] = {}
        all_items[row['parent']][row['id']] = {}
        all_items[row['parent']][row['id']]['child'] = all_items[row['id']]
        has_parent.add(row['id'])

    result = {}
    for key, value in all_items.items():
        if key not in has_parent:
            result[key] = value
    print(result)
    return result

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

make_map(df)
# print(res)