import unittest
import pandas as pd
import TreeNode

class NodeTest(unittest.TestCase):
    def test_node_output(self):
        data = [
            ['xHOU12037', 'HOU12037', '286251', '285972', None, 'XCIVIL GOVT', 'XHUD', 'XHOUSING AND URBAN DEVELOPMENT',None, None],
            ['XHOU12037.00', 'HOU12037.00', '286251', '285972', 'HOU12037', 'XCIVIL GOVT', 'XHUD','XHOUSING AND URBAN DEVELOPMENT', None, None],
            ['XHOU12037.00.01', 'StratMan New Core TO 05', '286251', '285972', 'HOU12037.00', 'XCIVIL GOVT', 'XHUD','XHOUSING AND URBAN DEVELOPMENT', None, None],
            ['XIOU12037.00.01.01.0110', 'AEM Phase 2I - L&C', '286251', '285972', 'IOU12037.00.01.01', 'XSLHE','XICALIFORNIA', 'XICALIFORNIA DEPT OF PUBLIC', None, None],
            ['XGOU12037.00.01.01.0110', 'AEM Phase 2G - L&C', '286251', '285972', 'GOU12037.00.01.01', 'XSLHE','XCALIFORNIA', 'XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
            ['XKOU12037.00.01.01.0110', 'AEM Phase 2K - L&C', '286251', '285972', 'KOU12037.00.01.01', 'XSLHE','XCALIFORNIA', 'XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
            ['XCALIFORNIA', None, None, None, None, None, None, None, '68', '42'],
            ['XCALIFORNIA DEPT OF CONSUMER AFFAIRS', None, None, None, None, None, None, None, '210', '68'],
            ['XCALIFORNIA DEPT OF PUBLIC HEALTH', None, None, None, None, None, None, None, '212', '68'],
            ['XCIVIL GOVT', None, None, None, None, None, None, None, '44', '1'],
            ['XHOUSING AND URBAN DEVELOPMENT', None, None, None, None, None, None, None, '369', '103'],
            ['XHUD', None, None, None, None, None, None, None, '103', '44'],
            ['XSLHE', None, None, None, None, None, None, None, '42', '1'],
            ['XUNIV CALIFORNIA', None, None, None, None, None, None, None, '130', '42'],
            ['XUNIVERSITY OF CALIFORNIA, REGE', None, None, None, None, None, None, None, '608', '130'],
            ['XWATER RESOURCES, CALIFORNIA DE', None, None, None, None, None, None, None, '663', '68'],
        ]
        df0 = pd.DataFrame(data,columns=['proj_id', 'proj_name', 'ppmd_id', 'pm_id', 'prnt_proj_id', 'sector', 'accounts','cust_name', 'wap_id', 'wap_prnt_id'])
        df0 = df0.where(pd.notnull(df0), None)

        root = TreeNode.get_node(df0)
        actual = []
        root.traverse_until_wap_helper(actual)
        print(actual)
        expected = [{'id': '369', 'name': 'HOUSING AND URBAN DEVELOPMENT', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}, {'id': '42', 'name': 'SLHE', 'currency': 'USD', 'shortName': '', 'children': [{'id': '', 'name': 'ICALIFORNIA', 'currency': 'USD', 'shortName': '', 'children': [{'id': '', 'name': 'ICALIFORNIA DEPT OF PUBLIC', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}]}]}, {'id': '212', 'name': 'CALIFORNIA DEPT OF PUBLIC HEALTH', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}, {'id': '212', 'name': 'CALIFORNIA DEPT OF PUBLIC HEALTH', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}]
        self.assertEqual(actual, expected)


if __name__ == '__main__':
    unittest.main()
