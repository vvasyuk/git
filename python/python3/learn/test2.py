x = (4+1)//2
print(x)
from itertools import combinations, permutations, product
l = [[2,3,4],[5,6,7],[1],[2]]
for i in product(*l):
    print(i)