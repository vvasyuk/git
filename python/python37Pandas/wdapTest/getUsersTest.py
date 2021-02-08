from wdapTest.wdap.client import WDAPClient
import os

wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
users = wdap.get_users(groups=True, return_response=False)
if users['success']:
    print(users['data'])
else:
    print(users['error'])