import pandas as pd
from pandas._libs.missing import *
import numpy as np

s = pd.Series([1, 2, None], dtype="Int64")
#s1 = pd.Series(["a", "b", np.nan], dtype="string")
s1 = pd.Series(["a", "b", np.nan], dtype="string")
# print(s.to_string())
# print(s1.to_string())

d = {'one': s1, 'two': s1}
df = pd.DataFrame(d)
# print(df.dtypes)
# print(df.to_string())

# df1 = df.isna()
# print(df1.to_string())
# df1 = df.where(df.notna(), '')
# df1.convert_dtypes
# print(df1.to_string())
#df = df.fillna('')
#df.where(df.isna(), 123)
#df.info()
#print(df.to_string())
# df1 = df.where(df.isna(), None)
# print(df1.to_string())
# df2 = df.loc[df['one'].isna()]


for row in df.itertuples(index=False):
    def _replace_na_to_none(df):
        res = []
        for x in df:
            if isinstance(x, NAType):
                res.append(None)
            else:
                res.append(x)
        return tuple(res)

    one, two = _replace_na_to_none(row)

    if isinstance(one, NAType):
        print("found NA")
    else:
        print(one)

