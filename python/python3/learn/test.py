def pascal(k):
    rows = [0 for _ in range(k + 1)]

    rows[1] = 1
    for i in range(1, k + 1):
        for j in range(i, 0, -1):
            print("i/j : " + str(i) + "/" + str(j))
            rows[j] += rows[j - 1]

    return rows[1:]

res = pascal(5)
print(res)