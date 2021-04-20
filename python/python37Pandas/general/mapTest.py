

def getPayload(wdap_secret_name,s3_bucket,rowData):
    payload = {
        'funcName1': 'func_users_get',
        'wdap_secret_name': wdap_secret_name,
        's3_bucket': s3_bucket,
        'rowData': rowData,
        'detail1': {
            'jobName': 'vv_tmp3'
        }
    }
    return payload

data = [
          {"guid": "BE815D1B06263008E0532A061E0A2F3D", "ownedLevels": "13832,15846"},
          {"guid": "BE815D1B06563008E0532A061E0A2F3D", "ownedLevels": "44"},
          {"guid": "BE8711E56A0E99D8E0532A061E0A1962", "ownedLevels": "13123"}
          ]
# print(getPayload('a','b',data))

pl = getPayload('a','b',data)
print(pl)

try:
    if 'jobName' in pl['detail']:
        print(pl['detail']['jobName'])
except KeyError:
    pass

# if 'detail' in pl:
#     if 'jobName' in pl['detail']:
#         print(pl['detail']['jobName'])

if 'funcName' in pl:
    print('funcName')

if 'funcName1' in pl:
    print('funcName1')

# a_dict = {'login': 'pswd'}
# print(type(a_dict))
#
# loginPswd = [(k, v) for k, v in a_dict.items()]
# print(loginPswd[0][0])

# b_dict = {'funcName': 'func_parse_dates', 'list_dates': [20210201, 20210202, 20210203]}
# present = b_dict['funcName']
#
# if present is not None:
#     print(present)
#
# non = b_dict.get('date', None)
#
# if non is not None:
#     print("should not be here")

# t1 = {'funcName': 'func_save_processed_date', 'data': {'date': '20210304','list_dates': [20210220, 20210221, 20210222], 'iterate': True}}
# date = t1['data'].get('date', None)
# print(date)

# a_dict = {'A': 1, 'B': 2}
# firstFewpairs = {k: a_dict[k] for k in sorted(a_dict.keys())[:5]}
# print(firstFewpairs)

# firstFewpairs2 = {k: a_dict[k] for k in list(a_dict.keys())[:5]}
# print(firstFewpairs2)

# a_dict = {'A': 1, 'B': 2}
# items_view = a_dict.items()
# items_iterator = iter(items_view)
# first_item = next(items_iterator)
# print(first_item)

# p = 2
# c = 22
# d = 222
#
# items = {}
# items[p] = {}
# items[c] = {}
# items[d] = {}
# items[p][c] = {}
# items[p][c][d] = {}
# #items[p][c] = []
# #items[p][c][d] = d
# print(items)

# testMap = {1: 'one', 2: 'two'}
# print(testMap)

# all_items = {'id': 0, 'childs':{}}
# p1 = {'id': 1, 'childs':{}}
# c1 = {'id': 11, 'childs':{}}
# p1["childs"] = c1
# print(all_items)
# print(p1)
# print(c1)