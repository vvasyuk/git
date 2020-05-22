import bisect


sorted_nums = [1,2,3]

i = bisect.bisect_left(sorted_nums, 4)
print(i)