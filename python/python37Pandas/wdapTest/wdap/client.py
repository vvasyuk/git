import xml.etree.ElementTree as Et
import zipfile
from io import BytesIO
import requests

API_URL = "https://api.adaptiveinsights.com/api/v25"
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
            password
    ) -> None:
        self.credentials = Et.Element("credentials", login=login, password=password)

    def __new_request(self, method):
        request = Et.Element("call", callerName="WDAP client", method=method)
        return request

    def __make_request(self, body, debug=False):
        result = {
                'success': True,
                'request': Et.tostring(body),
                'response': 'RESPONSE',
                'messages': 'MESSAGES',
                'data': 'DEBUG'
            }
        if debug:
            print_query(body)
            return result, 'Sims'
        body.append(self.credentials)
        response = requests.post(
            API_URL,
            headers={'Content-Type': 'application/xml'},
            data=Et.tostring(body)
        )
        result['response'] = response.text
        if not response.ok:
            result['success'] = False
            result['messages'] = {
                'key': f'HTTP error {response.status_code}',
                'message': response.reason
            }
            return result
        root = Et.fromstring(response.content)
        result['success'] = True if root.attrib['success'] == 'true' else False
        if root.find('messages'):
            messages = []
            for msg in root.find('messages').findall('message'):
                messages.append(msg.text)
            result['messages'] = messages
        content = Et.fromstring(response.content)
        return result, content

    def __make_big_request(self, request, content):
        request.append(self.credentials)
        payload = {
            'request': Et.tostring(request)}
        mem_zip = BytesIO()
        with zipfile.ZipFile(mem_zip, mode="w") as zf:
            zf.writestr('content.xml', Et.tostring(content))
        files = {
            'content': (
                "content.xml.zip",
                mem_zip.getvalue(),
                'application/zip'
            )
        }
        response = requests.request("POST", API_URL, data=payload, files=files)
        return response

    def get_users(self, groups=True, debug=False):
        request = self.__new_request("exportUsers")
        if groups:
            Et.SubElement(request, "include", groups=str(groups))
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            users = []
            for user in content.find('output').find('users'):
                users.append(user.attrib)
            result['data'] = users
        return result

    def create_users(self, users, debug=False):
        request = self.__new_request("createUser")
        users_tag = Et.SubElement(request, "users")
        for user in users:
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
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            users = []
            for user in content.find('output').find('result').find('created_users'):
                users.append(user.attrib)
            result['data'] = users
        return result

    def update_users(self, users, debug=False):
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
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            users = []
            for user in content.find('output').find('result').find('updated_users'):
                users.append(user.attrib)
            result['data'] = users
        return result

    def delete_users(self, users, debug=False):
        request = self.__new_request("updateUser")
        users_tag = Et.SubElement(request, "users")
        for user in users:
            Et.SubElement(users_tag, "user", guid=user['guid'], roleId='121')
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            users = []
            for user in content.find('output').find('result').find('updated_users'):
                users.append(user.attrib)
            result['data'] = users
        return result

    def get_levels(self, sheet_id=None, debug=False):
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

        request = self.__new_request("exportLevels")
        if sheet_id:
            Et.SubElement(request, "sheet", id=sheet_id)
        Et.SubElement(request, "include", inaccessibleValues="true")
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            levels = []
            for level in content.find('output').find('levels'):
                levels = read_level(level)
            result['data'] = levels
        return result

    def create_levels(self, levels, debug=False):
        def _create_level(parent_tag, child_levels):
            if child_levels:
                for level in child_levels:
                    current_level = Et.SubElement(parent_tag, 'level', id='' if level['id'] is None else level['id'],
                                                  name=level['name'], currency=level['currency'],
                                                  shortName=level['shortName'], proceedWithWarnings='1')
                    _create_level(current_level, level['children'])
                return current_level

        request = self.__new_request("updateLevels")
        levels_tag = Et.SubElement(request, "levels")
        _create_level(levels_tag, levels)
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            result['data'] = 'DONE'
        return result

    def insert_data(self, data_type, sheet_name, columns, data, version=None, debug=False):
        request = self.__new_request(f"import{data_type}Data")
        request.append(
            Et.Element("importDataOptions",
                       planOrActuals="Actuals",
                       moveBPtr="false",
                       allowParallel="false",
                       useMappings="false"))
        if version:
            request.append(Et.Element("version", name=version))
        request.append(Et.Element("sheet", name=sheet_name, isUserAssigned="false"))
        row_data_tag = Et.SubElement(request, "rowData")
        header = Et.Element("header")
        header.text = columns
        row_data_tag.append(header)
        rows = Et.Element("rows")
        for r in data:
            row = Et.Element("row")
            row.text = r
            rows.append(row)
        row_data_tag.append(rows)
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            result['data'] = 'DONE'
        return result

    def insert_cube_data(self, sheet_name, columns, data, version=None, debug=False):
        return self.insert_data('Cube', sheet_name, columns, data, version=version, debug=debug)

    def insert_standard_data(self, sheet_name, columns, data, version=None, debug=False):
        return self.insert_data('Standard', sheet_name, columns, data, version=version, debug=debug)

    def get_times(self, debug=False):
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

        request = self.__new_request("exportTime")
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            stratum = {}
            for elem in read_stratum(content.find('output').find('time').find('stratum')):
                stratum[elem['id']] = elem['label']
            periods = []
            for period in content.find('output').find('time').findall('period'):
                periods += read_period(period)
            result['data'] = periods
        return result

    def get_dimensions(self, debug=False):
        request = self.__new_request("exportDimensions")
        Et.SubElement(request, "include", dimensionValues="false")
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            dimensions = []
            for dimension in content.find('output').find('dimensions'):
                dimensions.append(dimension.attrib)
            result['data'] = dimensions
        return result

    def get_dimension_values(self, dimension_id, debug=False):
        request = self.__new_request("exportDimensions")
        Et.SubElement(request, "include", dimensionIDs=dimension_id, dimensionValues="true")
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            dim_values = []
            for dimension in content.find('output').find('dimensions'):
                d_id = dimension.attrib['id']
                d_name = dimension.attrib['name']
                for d_value in dimension.findall('dimensionValue'):
                    v_id = d_value.attrib['id']
                    v_name = d_value.attrib['name']
                    if d_value.find('attributes'):
                        for attr in d_value.find('attributes').findall('attribute'):
                            dim_values.append({
                                'd_id': d_id,
                                'd_name': d_name,
                                'v_id': v_id,
                                'v_name': v_name,
                                'att_id': attr.attrib['attributeId'],
                                'att_name': attr.attrib['name'],
                                'att_vid': attr.attrib['valueId'],
                                'att_vname': attr.attrib['value']
                            })
                    else:
                        dim_values.append({
                            'd_id': d_id,
                            'd_name': d_name,
                            'v_id': v_id,
                            'v_name': v_name
                        })
            result['data'] = dim_values
        return result

    def update_dimension_values(self, dim_values, debug=False):
        request = self.__new_request("updateDimensions")
        dimensions_tag = Et.SubElement(request, "dimensions")
        dimensions = {}
        for value in dim_values:
            if value['d_id'] not in dimensions:
                dimensions[value['d_id']] = Et.SubElement(
                    dimensions_tag,
                    "dimension",
                    id=value['d_id']
                )
            dim_tag = dimensions[value['d_id']]
            value_tag = Et.SubElement(
                dim_tag,
                "dimensionValue",
                id=value['v_id'],
                name=value['v_name']
            )
            Et.SubElement(
                value_tag,
                "attribute",
                name=value['att_name'],
                value=value['att_vname']
            )
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            result['data'] = 'DONE'
        return result

    def get_accounts(self, sheet_id=None, debug=False):
        def read_account(item, parent=None):
            res = []
            accnt = item.attrib
            accnt['attributes'] = {}
            accnt['parentId'] = parent
            if item.find('attributes'):
                for attribute in item.find('attributes'):
                    accnt['attributes'][attribute.attrib['name']] = attribute.attrib['value']
            res.append(accnt)
            if item.find('account') is not None:
                for child in item.findall('account'):
                    res += read_account(child, item.attrib['id'])
                return res
            else:
                return res

        request = self.__new_request("exportAccounts")
        if sheet_id:
            Et.SubElement(request, "sheet", id=sheet_id)
        result, content = self.__make_request(request, debug=debug)
        if debug:
            return result
        if result['success']:
            accounts = []
            for account in content.find('output').find('accounts'):
                accounts += read_account(account)
            result['data'] = accounts
        return result
