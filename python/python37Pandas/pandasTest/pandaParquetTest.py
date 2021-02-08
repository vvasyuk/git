import pandas as pd

# df = pd.read_parquet("C:/Users/jopa/Downloads/data/master/wdap/parquet_wdap_users")
# print(df.to_string())

# df = pd.read_parquet("C:/Users/jopa/Downloads/data/transform/part-00000-498b61a4-20ec-425c-9823-162e4dcc3a3a.c000.snappy.parquet")
# print(df.to_string())
# colsList = ['email', 'name', 'password', 'roleId']
# dfDict = df[colsList].to_dict('records')
# print(dfDict)

df = pd.read_parquet("C:/Users/jopa/Downloads/data/master/datapoint/part-00000-fc7604bb-cbf8-4c3e-9ad7-d363ebadf235-c000.snappy.parquet")
print(df.to_string())