print("abc")
seats = [1, 0, 0, 0, 1, 0, 0, 0, 1]
people = [i for i, x in enumerate(seats) if x == 1]
print(people)