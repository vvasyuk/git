import xml.etree.ElementTree as Et


# levels = [{'id': '369', 'name': 'HOU12037', 'currency': 'USD', 'shortName': '','children': [{'id': None, 'name': 'HOU12037.00', 'currency': 'USD', 'shortName': '','children': [{'id': None, 'name': 'HOU12037.00.01', 'currency': 'USD', 'shortName': '', 'children': []}]}]}]
#
# def create_level(parent_tag, levels):
#     if levels:
#         for level in levels:
#             current_level = Et.SubElement(parent_tag,'level',id='' if level['id'] is None else level['id'],name=level['name'],currency=level['currency'],shortName=level['shortName'])
#             create_level(current_level, level['children'])
#         return current_level
#
# request = Et.Element("call", callerName="client", method='method')
# levels_tag = Et.SubElement(request, "levels")
# create_level(levels_tag, levels)
#
def utf8len(string):
    return len(string.encode('utf-8'))

def print_query(request):
    print(f'{utf8len(Et.tostring(request, encoding="utf8").decode("utf8"))} B')
    print(f'{utf8len(Et.tostring(request, encoding="utf8").decode("utf8")) / 1024 / 1024:.0f} MB')
    print(Et.tostring(request, encoding='utf8').decode('utf8'))
#
# print_query(request)


def __new_request(method):
    request = Et.Element("call", callerName="client", method=method)
    credentials = Et.Element("credentials", login='login', password='password')
    request.append(credentials)
    return request

request = __new_request("exportUsers")
request.append(Et.Element("include", inaccessibleValues="true", groups="true"))
print_query(request)