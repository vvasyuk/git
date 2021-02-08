from wdapTest.wdap.client import WDAPClient
import os

wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
vendor_hours = ['Hours|SPE00345.01.01|JOHNSON, EMMA - 002290260001|Senior Consultant II - S00029|BILLABLE|-9999|']
cols = 'Account|Level|Vendor Employee|PLC|Non_Billable|SP1_FY16|SP2_FY16'
response = wdap.create_vendor_hours(vendor_hours, cols, sim=False)

if response['success']:
    print(response['success'])
else:
    print(response['error'])