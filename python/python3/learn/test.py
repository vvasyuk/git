from collections import deque

t1 = (13, 0)
t2 = (12, 0)

queue = deque([t1])
queue.append(t2)

print(queue.pop(12))