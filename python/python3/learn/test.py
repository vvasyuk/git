# s = "hello world"
# print("string: " + s)
# print("reverse: " + s[::-1])
# print("length: " + str(len(s)))

l1 = [0,2,4,6]
l2 = [0,1,3,5]

line_segments = list(zip(l1, l2))

for i, x1 in enumerate(line_segments):
    print("idx: " + i.__str__() + " t1: " + str(x1[0]))
    for x2 in line_segments[i + 1:]:
        print("inner idx: " + i.__str__() + " t1: " + str(x2[1]))