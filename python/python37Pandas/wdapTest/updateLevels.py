from wdapTest.wdap.client import WDAPClient
import pandas as pd
from wdapTest.wdap.node import TreeNode
from wdapTest.wdap.node import *
from wdapTest.wdap.client import WDAPClient
import time
import os

# levels = [{
#     'id': '49',
#     'name': 'DOJ JUSTICE',
#     'currency': 'USD',
#     'shortName': '',
#     'children': [{
#         'id': '',
#         'name': 'MARSHALS SERVICE, UNITED',
#         'currency': 'USD',
#         'shortName': '',
#         'children': [{
#             'id': None,
#             'name': 'MAR00204',
#             'currency': 'USD',
#             'shortName': '',
#             'children': [{
#                 'id': None,
#                 'name': 'MAR00204.00',
#                 'currency': 'USD',
#                 'shortName': '',
#                 'children': []
#             }]
#         }]
#     }]
# }, {
#     'id': '49',
#     'name': 'DOJ JUSTICE',
#     'currency': 'USD',
#     'shortName': '',
#     'children': [{
#         'id': '',
#         'name': 'JMD - PROCUREMENT SERVICE',
#         'currency': 'USD',
#         'shortName': '',
#         'children': [{
#             'id': None,
#             'name': 'PSS10119',
#             'currency': 'USD',
#             'shortName': '',
#             'children': []
#         }, {
#             'id': None,
#             'name': 'PSS07524',
#             'currency': 'USD',
#             'shortName': '',
#             'children': [{
#                 'id': None,
#                 'name': 'PSS07524.03',
#                 'currency': 'USD',
#                 'shortName': '',
#                 'children': []
#             }]
#         }, {
#             'id': '3042',
#             'name': 'PSS07509',
#             'currency': 'USD',
#             'shortName': '',
#             'children': [{
#                 'id': None,
#                 'name': 'PSS07509.04',
#                 'currency': 'USD',
#                 'shortName': '',
#                 'children': []
#             }]
#         }]
#     }]
# }
# ]
def result_process(result):
    if result['success']:
        print(result['data'])
        # print(result['messages'])
    else:
        raise Exception(f"exception: {result['messages']}")
df = pd.read_parquet("C:\\work\\project\\data\\master\\datapoint\\levels\\", use_nullable_dtypes=True)
wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))

start = time.perf_counter()
root = get_node(df)
end = time.perf_counter()
print(f"create tree: {start - end:0.4f}")

start = time.perf_counter()
trees = []
root.traverse_until_wap_helper(trees)
end = time.perf_counter()
print(f"format tree: {start - end:0.4f}")
start = time.perf_counter()
result = wdap.create_levels(trees, debug=True)
print(result)
result_process(result)
end = time.perf_counter()
print(f"send to wdap: {start - end:0.4f}")