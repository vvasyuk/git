from pandas._libs.missing import *

class TreeNode(object):
    def __init__(self, id, wap_id='', name='', currency='', shortName='', sector='', account='', cust_name='', has_parent=False):
        self.id = id
        self.wap_id = wap_id
        self.name = name
        self.currency = currency
        self.shortName = shortName
        self.sector = sector
        self.account = account
        self.cust_name = cust_name
        self.has_parent = has_parent
        self.childs = []

    def toMap(self):
        return {'id': self.wap_id, 'name': self.id, 'currency': 'USD', 'shortName': self.shortName, 'children': []}

    def add_child_if_not_exists(self, node):
        if not [n.id for n in self.childs].__contains__(node.id):
            self.childs.append(node)

    def traverse(self, lvl):
        print(" "*lvl + f"{self.id}: {self.wap_id}: {self.name}")
        for c in self.childs:
            c.traverse(lvl+1)

    def traverse_into_dict(self, result):
        tmp_result = self.toMap()
        result.append(tmp_result)
        for c in self.childs:
            c.traverse_into_dict(tmp_result['children'])

    def traverse_until_wap_helper(self, result):
        for c in self.childs:
            c.traverse_until_wap_into_dict(result, False, None)
        return result

    def traverse_until_wap_into_dict(self, result, isStarted, prev_node):
        def _iterate(res):
            tmp_result = self.toMap()
            res.append(tmp_result)
            for c in self.childs:
                c.traverse_until_wap_into_dict(tmp_result['children'], isStarted, self.wap_id)
        if isStarted:
            _iterate(result)
        elif prev_node is None and (self.wap_id is None or self.wap_id == ''):
            isStarted = True
            _iterate(result)
        elif not isStarted:
            if (self.wap_id is None or self.wap_id == '') and prev_node.id is not None:
                prev_result = prev_node.toMap()
                result.append(prev_result)
                isStarted = True
                _iterate(prev_result['children'])
            else:
                for c in self.childs:
                    c.traverse_until_wap_into_dict(result, isStarted, self)


def get_node(df):
    root = TreeNode("root")
    all_items = {}

    def _get_node(id):
        if id not in all_items:
            all_items[id] = TreeNode(id=id)
        return all_items[id]

    def _link_top_levels(id, sector, account, cust_name):
        (cust_name_obj, account_obj, sector_obj) = (_get_node(cust_name), _get_node(account), _get_node(sector))
        sector_obj.add_child_if_not_exists(account_obj)
        account_obj.add_child_if_not_exists(cust_name_obj)
        cust_name_obj.add_child_if_not_exists(all_items[id])
        root.add_child_if_not_exists(all_items[sector])

    def _substract_last_dot(input):
        splitted = input.split(".")
        new_parent_id = splitted[:len(splitted) - 1]
        new_parent_id_str = '.'.join([str(elem) for elem in new_parent_id])
        return new_parent_id_str

    def _generate_parent(node):
        if not node.has_parent and str(node.id).__contains__('.'):
            new_parent_id_str = _substract_last_dot(str(node.id))
            if new_parent_id_str in all_items:
                all_items[new_parent_id_str].add_child_if_not_exists(node)
            else:
                parent_obj = TreeNode(id=new_parent_id_str, wap_id=node.wap_id, name=node.id, currency='',
                                      shortName='', sector=node.sector, account=node.account,
                                      cust_name=node.cust_name, has_parent=False)
                parent_obj.add_child_if_not_exists(node)
                node.has_parent = True
                all_items[new_parent_id_str] = parent_obj
                _generate_parent(parent_obj)
        elif not str(node.id).__contains__('.') and node.sector and node.account and node.cust_name:
            _link_top_levels(node.id, node.sector, node.account, node.cust_name)

    def _replace_na_to_none(df):
        res = []
        for x in df:
            if isinstance(x, NAType):
                res.append(None)
            else:
                res.append(x)
        return tuple(res)

    for index, row in df.iterrows():
        id, name, ppmd, pm, parent, sector, account, cust_name, wap_id, wap_prnt_id = _replace_na_to_none(row)
        if id in all_items:
            node_obj = all_items[id]
            if name is not None:
                node_obj.name = name
            node_obj.wap_id = wap_id
            node_obj.currency = ''
        if id not in all_items:
            all_items[id] = TreeNode(id=id, wap_id=wap_id, name=name, currency='', shortName='', sector=sector, account=account, cust_name=cust_name, has_parent=True)
        if parent not in all_items:
            if (parent is None or parent == '') and sector is not None and account is not None and cust_name is not None:
                _link_top_levels(id, sector, account, cust_name)
            else:
                all_items[parent] = TreeNode(id=parent, wap_id=wap_id, name=name, currency='', shortName='', sector=sector, account=account, cust_name=cust_name, has_parent=False)
                all_items[parent].add_child_if_not_exists(all_items[id])
        if parent in all_items:
            all_items[parent].add_child_if_not_exists(all_items[id])

    for (k, node) in list(all_items.items()):
        _generate_parent(node)

    return root