from collections import OrderedDict

def solve(letters, unassigned, nums, words):
    if not unassigned:
        if is_valid(letters, words):
            return letters
        else:
            return None

    char = unassigned[0]
    for num in nums:
        letters[char] = num
        nums.remove(num)

        if is_valid(letters, words):
            solution = solve(letters, unassigned[1:], nums, words)
            if solution:
                return solution

        nums.add(num)
        letters[char] = None

    return False

def order_letters(words):
    n = len(words[2])

    letters = OrderedDict()
    for i in range(n - 1, -1, -1):
        for word in words:
            if word[i] not in letters:
                letters[word[i]] = None

    return letters

def normalize(word, n):
    diff = n - len(word)
    return ['#'] * diff + word

def cryptanalyze(problem):
    words = list(map(list, problem))

    n = len(words[2])
    words[0] = normalize(words[0], n)
    words[1] = normalize(words[1], n)

    letters = order_letters(words)
    unassigned = [letter for letter in letters if letter != '#']
    nums = set(range(0, 10))

    return solve(letters, unassigned, nums, words)

def is_valid(letters, words):
    a, b, c = words
    n = len(c)

    carry = 0
    for i in range(n - 1, -1, -1):
        if any(letters[word[i]] is None for word in words):
            return True
        elif letters[a[i]] + letters[b[i]] + carry == letters[c[i]]:
            carry = 0
        elif letters[a[i]] + letters[b[i]] + carry == 10 + letters[c[i]]:
            carry = 1
        else:
            return False

    return True

# from collections import defaultdict
#
# graph = defaultdict(list)
# graph["a"].append(1)
# graph["a"].append(2)
# graph["b"].append(3)
#
# print(graph)
# l = list(graph)
#
# print(l[0])

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