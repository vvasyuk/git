from collections import defaultdict

graph = defaultdict(list)
graph["a"].append(1)
graph["a"].append(2)
graph["b"].append(3)

print(graph)
l = list(graph)

print(l[0])

# from collections import defaultdict
# import heapq
#
# heap = []
# heapq.heappush(heap, (1, "a"))
# heapq.heappush(heap, (1, "a"))
# heapq.heappush(heap, (2, "a"))
#
# print(heap)
# string="aaabbc"
# frequencies = defaultdict(int)
# for letter in string:
#     frequencies[letter] += 1
#
# heap = []
# for char, count in frequencies.items():
#     heapq.heappush(heap, (-count, char))
#
# count, char = heapq.heappop(heap)
# result = [char]
#
# print(frequencies)
# print(heap)
# print(char)


# closest_string = []
#
# closest_string.append("a")
# closest_string += "b"
# closest_string.append("c")
#
# print(closest_string)

# s = "hello world"
# print("string: " + s)
# print("reverse: " + s[::-1])
# print("length: " + str(len(s)))

# l1 = [0,2,4,6]
# l2 = [0,1,3,5]
#
# line_segments = list(zip(l1, l2))
#
# for i, x1 in enumerate(line_segments):
#     print("idx: " + i.__str__() + " t1: " + str(x1[0]))
#     for x2 in line_segments[i + 1:]:
#         print("inner idx: " + i.__str__() + " t1: " + str(x2[1]))

# nums = [3, 5, 10, 20, 21]
#
# print(nums)
#
# num_divisors = [1 for _ in range(len(nums))]
# print(num_divisors)
# prev_divisor_index = [-1 for _ in range(len(nums))]
# print(prev_divisor_index)
# max_index = 0
#
# for i in range(len(nums)):
#   for j in range(i):
#     print(str(nums[i]) + " - " + str(nums[j]))
#     if (nums[i] % nums[j] == 0) and (num_divisors[i] < num_divisors[j] + 1):
#         num_divisors[i] = num_divisors[j] + 1
#         prev_divisor_index[i] = j
#
#     if num_divisors[max_index] < num_divisors[i]:
#         max_index = i
#
# for i in range(len(num_divisors)):
#     print("num_divisors: " + str(num_divisors[i]) + " prev_divisor_index: " + str(prev_divisor_index[i]))
#
# result = []
# i = max_index
# while i >= 0:
#     result.append(nums[i])
#     i = prev_divisor_index[i]
#
# print(str(result))