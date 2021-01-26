class Node(object):
    def __init__(self, v):
        self.value = v
        self.childs = []

    def traverse(self):
        for c in self.childs:
            yield c.value()
        # def _traverse():
        #     for c in self.childs:
        #         yield c.value()
        # return (_traverse())
        # print(self.value)
        # for child in self.childs:
        #     yield from child.traverse()



root = Node(0)

n13 = Node(13)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)

root.childs.append(n13)
n13.childs.append(n2)
n13.childs.append(n3)
n2.childs.append(n2)


x = root.traverse()
print(1)
