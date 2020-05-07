def apply_ops(a, b):
    return [a + b, a - b, a * b, a / b]

x = [5, 2, 7, 8]
for i in range(len(x) - 2):
    for x in apply_ops(*x[i : i + 2]):
        print(x)