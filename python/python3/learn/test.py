def and_product(m, n):
    res = m

    for i in range(m + 1, n + 1):
        res &= i

    return res
m=1
n=5
print(and_product(m,n))