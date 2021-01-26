import pandas as pd

def make_map(list_child_parent):
    has_parent = set()
    all_items = {}
    for child, parent, ext_id in list_child_parent:
        if parent not in all_items:
            all_items[parent] = {}
        if child not in all_items:
            all_items[child] = {}
        all_items[parent][child] = all_items[child]
        has_parent.add(child)

    result = {}
    for key, value in all_items.items():
        if key not in has_parent:
            result[key] = value
    return result
data0 = [
    [1, ''],
    [2, 1],
    [3, 2]
]
data = [
    [1, '', '001'],
    [2, 1, ''],
    [2.5, 1, ''],
    [3, 2, '']
]
df = pd.DataFrame(data, columns = ['id', 'parent', 'ext_id'])
print(df.to_string())

res = make_map(data)
print(res)