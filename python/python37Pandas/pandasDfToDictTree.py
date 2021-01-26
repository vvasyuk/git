import pandas as pd

data = [
    [1, '', '001'],
    [2, 1, ''],
    [3, 2, '']
]
df = pd.DataFrame(data, columns = ['id', 'parent', 'ext_id'])

print(df.to_string())