start_date = 20210201
l = [20210130,20210131,20210202,20210201,20210203]
l2 = sorted(i for i in l if i >= start_date)

print(l2.pop(0))
print(l2.pop(0))
print(l2.pop(0))

lEmpty = []
if lEmpty:
    print("non empty")
else:
    print("empty")

