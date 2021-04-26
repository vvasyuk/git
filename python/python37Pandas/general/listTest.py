# start_date = 20210201
l = [1,2,3,4,5,6,7,8,9,10]
# print(l[0])
res = [l[i: i+3] for i in range(0, len(l), 3)]
chunks_cnt = len(res)
for i in range(chunks_cnt):
    print(f"\ncalling lambda: {i+1} of {chunks_cnt} times")
    print(res[i])


# l2 = sorted(i for i in l if i >= start_date)
#
# print(l2.pop(0))
# print(l2.pop(0))
# print(l2.pop(0))
#
# lEmpty = []
# if lEmpty:
#     print("non empty")
# else:
#     print("empty")

#l_str = map(str, l)
# l_str = [str(x) for x in l]
# print(l)
# print(l_str)

# from functools import reduce
# l = ['Account','Level','Split%Label','SP13_FY20']
# reduced = reduce(lambda a, b: str(a) + '|' + str(b).replace("%", " "), l, '')[1:]
# print(reduced)