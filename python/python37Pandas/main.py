# from common.print_lol import *
# from common.parquet import read

# def f1(str):
#     print_lol(['hi','there'])
#     return str.upper()

# def f2():
#     df = read()
#     print(df)

class Node(object):
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

def main():
    root = Node(0)
    c1 = Node(1)
    c2 = Node(2)
    c3 = Node(3)
    root.childs.append(c1)
    root.childs.append(c2)
    root.childs.append(c3)

    print([x.id for x in root.childs].__contains__(3))


    # m = {1: "one", 2: "two"}
    # print(m)
    #
    # m[1] = var = m.get(1, "AAA")
    # print(m)
    # print(var)

    # x = "a.b.c.d"
    # splitted = x.split(".")
    # print(splitted[:len(splitted)-1])

    # a = b = 1
    # print(a)
    # print(b)
    # print(f1("test"))
    # f2()


if __name__ == '__main__':
    main()
