from functools import reduce
import pandas as pd

def concatList(l):
    return reduce(lambda a, b: str(a) + '|' + str(b), l, '')[1:]

df = pd.read_parquet('c:/work/project/data/transform/vendor_hours/part-00000-3fd81426-2744-4d6a-926d-6f488b8a4501.c000.snappy.parquet')
#print(df.to_string())
colsList = df.columns
print(colsList)
cols = concatList(colsList)
print(cols)

dfDict = df.to_dict('records')
vendor_hours = map(concatList, map(lambda m: list(m.values()),dfDict))
print(type(vendor_hours))

for r in vendor_hours:
    print(f"<row>{r}</row>")

