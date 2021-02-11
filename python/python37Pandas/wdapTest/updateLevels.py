from wdapTest.wdap.client import WDAPClient
import os

levels = [{
    'id': '49',
    'name': 'DOJ JUSTICE',
    'currency': 'USD',
    'shortName': '',
    'children': [{
        'id': '',
        'name': 'MARSHALS SERVICE, UNITED',
        'currency': 'USD',
        'shortName': '',
        'children': [{
            'id': None,
            'name': 'MAR00204',
            'currency': 'USD',
            'shortName': '',
            'children': [{
                'id': None,
                'name': 'MAR00204.00',
                'currency': 'USD',
                'shortName': '',
                'children': []
            }]
        }]
    }]
}, {
    'id': '49',
    'name': 'DOJ JUSTICE',
    'currency': 'USD',
    'shortName': '',
    'children': [{
        'id': '',
        'name': 'JMD - PROCUREMENT SERVICE',
        'currency': 'USD',
        'shortName': '',
        'children': [{
            'id': None,
            'name': 'PSS10119',
            'currency': 'USD',
            'shortName': '',
            'children': []
        }, {
            'id': None,
            'name': 'PSS07524',
            'currency': 'USD',
            'shortName': '',
            'children': [{
                'id': None,
                'name': 'PSS07524.03',
                'currency': 'USD',
                'shortName': '',
                'children': []
            }]
        }, {
            'id': '3042',
            'name': 'PSS07509',
            'currency': 'USD',
            'shortName': '',
            'children': [{
                'id': None,
                'name': 'PSS07509.04',
                'currency': 'USD',
                'shortName': '',
                'children': []
            }]
        }]
    }]
}
]

wdap = WDAPClient(login=str(os.environ['login']), password=str(os.environ['password']))
result = wdap.create_levels(levels)
if result['success']:
    print("work is done")
else:
    for err in result['error']:
        print(err)