from wdapTest.wdap.client import WDAPClient
import os

wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
levels = wdap.get_levels()
if levels['success']:
    print(levels['data'])
else:
    print(levels['error'])