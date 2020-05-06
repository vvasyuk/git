x = [0,0,0,1,1,1]
y = [2,2,2,3,3,3]

test = (sum(pair) for pair in zip(x, y))
print(test)

for char in test:
    print(char)