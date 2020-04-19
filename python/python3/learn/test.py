from itertools import product
l=list(product((0, 3), range(5 + 1)))
l2 = list(product(range(1, 3), (0, 5)))
print(l2)