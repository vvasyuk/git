import pandas as pd

df = pd.read_parquet('C:/Users/Jopa/Downloads/part-00000-8708c1d8-b4be-4637-8bff-d0ac1fcd2908-c000.snappy.parquet')
print(df.to_string())

# currentDate = "20210102"
# dfDate = "20210102_124743"
# if dfDate[0:8] == currentDate:
#     print("yes")
# else:
#     print("no")


#
# print(df.head(1).to_string)
#
# print(df.empl_id.to_string)
#
# print(df.columns)

# ins_list = []
#
# for row_label, row in df.iterrows():
#     if row.operation == 'insert':
#         s = str(row.empl_id) + ' ' + row.last_name + ' ' + row.first_name + ' ' + row.email_id + ' ' + str(row.guid) + ' ' + row.from_date + ' ' + row.operation
#         ins_list.append(s)
#         #print(s)
#
# for elem in ins_list:
#     print(elem)