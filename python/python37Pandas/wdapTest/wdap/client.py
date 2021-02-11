import xml.etree.ElementTree as Et

import requests

API_URL = "https://api.adaptiveinsights.com/api/v25"
# API_URL = "http://localhost:5000/"
headers = {'Content-Type': 'application/xml'}


def print_query(request):
    print(f'{utf8len(Et.tostring(request, encoding="utf8").decode("utf8"))} B')
    print(f'{utf8len(Et.tostring(request, encoding="utf8").decode("utf8")) / 1024 / 1024:.0f} MB')
    print(Et.tostring(request, encoding='utf8').decode('utf8'))


def utf8len(string):
    return len(string.encode('utf-8'))


class WDAPClient(object):
    """
    This is the entry point to using the API. Create an instance of this class, passing it the value of the
    "login" and "password".
    """

    def __init__(
            self,
            login,
            password,
            locale='',
            instance=''
    ) -> None:
        self.credentials = Et.Element("credentials", login=login, password=password)

    def __make_request(self, body):
        resp = requests.post(API_URL, headers={'Content-Type': 'application/xml'}, data=Et.tostring(body))
        # if resp.status_code != requests.codes.ok:
        #     return None
        return resp

    def __new_request(self, method):
        request = Et.Element("call", callerName="WDAP client", method=method)
        request.append(self.credentials)
        return request

    def get_users(self, groups=None, return_response=True):
        result = {}
        request = self.__new_request("exportUsers")
        if groups:
            Et.SubElement(request, "include", groups=str(groups))
        # print_query(request)
        response = self.__make_request(request)
        if not response.ok:
            result['success'] = False
            result['error'] = {
                'key': f'HTTP error {response.status_code}',
                'message': response.reason
            }
            return result
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            users = []
            for user in root.find('output').find('users'):
                users.append(user.attrib)
            result['data'] = users

        return result

    def create_users(self, users, return_response=True, sim=False):
        result = {}
        request = self.__new_request("createUser")
        users_tag = Et.SubElement(request, "users")
        for user in users:
            # Et.SubElement(users_tag, "user", attrib=user)
            user_tag = Et.SubElement(users_tag, "user",
                                     name=user['name'],
                                     password=user['password'],
                                     email=user['email'])
            if 'roleId' in user:
                user_tag.set('roleId', user['roleId'])
            if 'ownedLevels' in user:
                user_tag.set('ownedLevels', user['ownedLevels'])
            if 'timeZone' in user:
                user_tag.set('timeZone', user['timeZone'])

        if sim:
            print_query(request)
            return {'success': True, 'data': 'Simulated'}
        response = self.__make_request(request)
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            users = []
            for user in root.find('output').find('result').find('created_users'):
                users.append(user.attrib)
            result['data'] = users

        return result

    def update_users(self, users, return_response=True, sim=False):
        """
        API call to update users

        :param sim: if True do not perform any real changes
        :param users: dictionary of users
        :param return_response: if True, return raw XML response also
        :return:
        """
        result = {}
        request = self.__new_request("updateUser")
        users_tag = Et.SubElement(request, "users")
        for user in users:
            user_tag = Et.SubElement(users_tag, "user", guid=user['guid'])
            if 'name' in user:
                user_tag.set('name', user['name'])
            if 'password' in user:
                user_tag.set('password', user['password'])
            if 'roleId' in user:
                user_tag.set('roleId', user['roleId'])
            if 'email' in user:
                user_tag.set('email', user['email'])
            if 'ownedLevels' in user:
                user_tag.set('ownedLevels', user['ownedLevels'])
            if 'timeZone' in user:
                user_tag.set('timeZone', user['timeZone'])

        if sim:
            print_query(request)
            return {'success': True, 'data': 'Simulated'}
        response = self.__make_request(request)
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            users = []
            for user in root.find('output').find('result').find('updated_users'):
                users.append(user.attrib)
            result['data'] = users

        return result

    def delete_users(self, users, return_response=True, sim=False):
        """
        API call to delete users

        :param sim: if True do not perform any real changes
        :param users: dictionary of users' GUIDs
        :param return_response: if True, return raw XML response also
        :return:
        """
        result = {}
        request = self.__new_request("updateUser")
        users_tag = Et.SubElement(request, "users")
        for user in users:
            Et.SubElement(users_tag, "user", guid=user['guid'], roleId='121')

        if sim:
            print_query(request)
            return {'success': True, 'data': 'Simulated'}
        response = self.__make_request(request)
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            users = []
            for user in root.find('output').find('result').find('updated_users'):
                users.append(user.attrib)
            result['data'] = users

        return result

    def get_levels(self, sheet_id=None, return_response=True):
        def read_level(item, parent=None):
            res = []
            lvl = item.attrib
            lvl['attributes'] = {}
            lvl['parentId'] = parent
            if item.find('attributes'):
                for attribute in item.find('attributes'):
                    lvl['attributes'][attribute.attrib['name']] = attribute.attrib['value']
            res.append(lvl)
            if 'hasChildren' in item.attrib:
                for child in item.findall('level'):
                    res += read_level(child, item.attrib['id'])
                return res
            else:
                return res

        result = {}
        request = self.__new_request("exportLevels")
        if sheet_id:
            Et.SubElement(request, "sheet", id=sheet_id)
        # print_query(request)
        response = requests.post(API_URL, headers=headers, data=Et.tostring(request))
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            levels = []
            for level in root.find('output').find('levels'):
                levels = read_level(level)
            result['data'] = levels

        return result

    def create_levels(self, levels, return_response=True, sim=False):
        def _create_level(parent_tag, levels):
            if levels:
                for level in levels:
                    current_level = Et.SubElement(parent_tag, 'level', id='' if level['id'] is None else level['id'],name=level['name'], currency=level['currency'],shortName=level['shortName'])
                    _create_level(current_level, level['children'])
                return current_level

        result = {}
        request = self.__new_request("updateLevels")
        levels_tag = Et.SubElement(request, "levels")
        _create_level(levels_tag, levels)

        if sim:
            print_query(request)
            return {'success': True, 'data': 'Simulated'}
        response = requests.post(API_URL, headers=headers, data=Et.tostring(request))
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = []
            for msg in root.findall(".//messages/"):
                result['error'].append(msg.text)
        else:
            result['success'] = True

        return result

    def get_times(self, return_response=True):
        def read_period(item, parent=None):
            res = []
            prd = item.attrib
            prd['parentId'] = parent
            prd['stratumName'] = stratum[prd['stratumId']]
            res.append(prd)
            if item.findall('period'):
                for child in item.findall('period'):
                    res += read_period(child, item.attrib['id'])
                return res
            else:
                return res

        def read_stratum(item, parent=None):
            res = []
            strt = item.attrib
            strt['parentId'] = parent
            res.append(strt)
            if item.findall('stratum'):
                for child in item.findall('stratum'):
                    res += read_stratum(child, item.attrib['id'])
                return res
            else:
                return res

        result = {}
        request = self.__new_request("exportTime")
        # print_query(request)
        response = requests.post(API_URL, headers=headers, data=Et.tostring(request))
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text

            stratum = {}
            for elem in read_stratum(root.find('output').find('time').find('stratum')):
                stratum[elem['id']] = elem['label']

            periods = []
            for period in root.find('output').find('time').findall('period'):
                periods += read_period(period)
            result['data'] = periods

        return result

    def create_vendor_hours(self, vendor_hours, cols, return_response=True, sim=False):
        result = {}
        request = self.__new_request("importCubeData")
        request.append(Et.Element("importDataOptions", moveBPtr="false", allowParallel="false", planOrActuals="Actuals"))
        request.append(Et.Element("sheet", isUserAssigned="false", name="Vendor Hours"))
        vendor_hours_tag = Et.SubElement(request, "rowData")

        header = Et.Element("header")
        header.text = cols
        vendor_hours_tag.append(header)

        rows = Et.Element("rows")

        for r in vendor_hours:
            row = Et.Element("row")
            row.text = r
            rows.append(row)

        vendor_hours_tag.append(rows)

        if sim:
            print_query(request)
            return {'success': True, 'data': 'Simulated'}

        response = self.__make_request(request)
        root = Et.fromstring(response.content)
        if root.attrib['success'] != 'true':
            result['success'] = False
            result['error'] = {
                'key': root.find('messages').find('message').attrib['key'],
                'message': root.find('messages').find('message').text
            }
        else:
            result['success'] = True
            if return_response:
                result['response'] = response.text
        return result