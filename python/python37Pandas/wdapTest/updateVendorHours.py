from functools import reduce
from wdapTest.wdap.client import WDAPClient
import os
import pandas as pd

df = pd.read_parquet('c:/Users/jopa/Downloads/data/testData/vendor_hours/transform/part-00000-ed625927-0e7f-4a2e-9948-55d77d54e69f.c000.snappy.parquet')
df5 = df.head(5)
#print(df5.to_string())

def concatList(l):
    return reduce(lambda a,b: str(a)+'|'+str(b), l, '')[1:]

cols = concatList(df5.columns).replace('vendor_employee', 'Vendor Employee')
#print(cols)


dfDict = df5.to_dict('records')

# for r in dfDict:
#     l = list(r.values())
#     print(l)

#map1 = map(lambda m: list(m.values()), dfDict)
# for r in map1:
#     print(r)


vendor_hours = map(concatList, map(lambda m: list(m.values()), dfDict))

# for r in vendor_hours:
#     print(r)
# df5 = df.head(5)
# dfDict = df5.to_dict('records')
#
# for r in dfDict:
#     print(_concatList(list(r.values())))

wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
# vendor_hours = ['Hours|SPE00345.01.01|JOHNSON, EMMA - 002290260001|Senior Consultant II - S00029|BILLABLE|-9999|']
# cols = 'Account|Level|Vendor Employee|PLC|Non_Billable|SP1_FY16|SP2_FY16'
response = wdap.create_vendor_hours(vendor_hours, cols, sim=True)

if response['success']:
    print(response['success'])
else:
    print(response['error'])