import unittest
import pandas as pd
import TreeNodeOld


class MyTestCase(unittest.TestCase):
    def test_node_output(self):
        data = [
            ['HOU12037', 'HOU12037', '286251', '285972', None, 'CIVIL GOVT', 'HUD', 'HOUSING AND URBAN DEVELOPMENT',None, None],
            ['HOU12037.00', 'HOU12037.00', '286251', '285972', 'HOU12037', 'CIVIL GOVT', 'HUD','HOUSING AND URBAN DEVELOPMENT', None, None],
            ['HOU12037.00.01', 'StratMan New Core TO 05', '286251', '285972', 'HOU12037.00', 'CIVIL GOVT', 'HUD','XHOUSING AND URBAN DEVELOPMENT', None, None],
            ['IOU12037.00.01.01.0110', 'AEM Phase 2I - L&C', '286251', '285972', 'IOU12037.00.01.01', 'SLHE','ICALIFORNIA', 'ICALIFORNIA DEPT OF PUBLIC', None, None],
            ['GOU12037.00.01.01.0110', 'AEM Phase 2G - L&C', '286251', '285972', 'GOU12037.00.01.01', 'SLHE','CALIFORNIA', 'CALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
            ['KOU12037.00.01.01.0110', 'AEM Phase 2K - L&C', '286251', '285972', 'KOU12037.00.01.01', 'SLHE','CALIFORNIA', 'CALIFORNIA DEPT OF PUBLIC HEALTH', None, None],
            ['CALIFORNIA', None, None, None, None, None, None, None, '68', '42'],
            ['CALIFORNIA DEPT OF CONSUMER AFFAIRS', None, None, None, None, None, None, None, '210', '68'],
            ['CALIFORNIA DEPT OF PUBLIC HEALTH', None, None, None, None, None, None, None, '212', '68'],
            ['CIVIL GOVT', None, None, None, None, None, None, None, '44', '1'],
            ['HOUSING AND URBAN DEVELOPMENT', None, None, None, None, None, None, None, '369', '103'],
            ['HUD', None, None, None, None, None, None, None, '103', '44'],
            ['SLHE', None, None, None, None, None, None, None, '42', '1'],
            ['UNIV CALIFORNIA', None, None, None, None, None, None, None, '130', '42'],
            ['UNIVERSITY OF CALIFORNIA, REGE', None, None, None, None, None, None, None, '608', '130'],
            ['WATER RESOURCES, CALIFORNIA DE', None, None, None, None, None, None, None, '663', '68'],
        ]
        df0 = pd.DataFrame(data,columns=['proj_id', 'proj_name', 'ppmd_id', 'pm_id', 'prnt_proj_id', 'sector', 'accounts','cust_name', 'wap_id', 'wap_prnt_id'], dtype='string')
        #df0 = df0.where(pd.notnull(df0), None)
        #print(df0.dtypes)
        print(df0.to_string())

        root = TreeNodeOld.get_node(df0)
        root.traverse(1)
        actual = []
        root.traverse_until_wap_helper(actual)
        print(actual)
        expected = [{'id': '369', 'name': 'HOUSING AND URBAN DEVELOPMENT', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'HOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}, {'id': '42', 'name': 'SLHE', 'currency': 'USD', 'shortName': '', 'children': [{'id': '', 'name': 'ICALIFORNIA', 'currency': 'USD', 'shortName': '', 'children': [{'id': '', 'name': 'ICALIFORNIA DEPT OF PUBLIC', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'IOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}]}]}, {'id': '212', 'name': 'CALIFORNIA DEPT OF PUBLIC HEALTH', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'GOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}, {'id': '212', 'name': 'CALIFORNIA DEPT OF PUBLIC HEALTH', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01.01', 'currency': 'USD', 'shortName': '', 'children': [{'id': None, 'name': 'KOU12037.00.01.01.0110', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]}]}]}]
        self.assertEqual(actual, expected)


if __name__ == '__main__':
    unittest.main()
