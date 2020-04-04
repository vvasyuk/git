import heapq
heap = [(0,0)]

print(heapq.heappop(heap))


heapq.heappush(heap, (2,0))
heapq.heappush(heap, (1,0))
heapq.heappush(heap, (5,2))
heapq.heappush(heap, (5,6))
while heap:
    print(heapq.heappop(heap))

skyline = [(0, 3), (4, 5)]

print(skyline[-1][1])

# if not skyline:
#     print("not skyline")