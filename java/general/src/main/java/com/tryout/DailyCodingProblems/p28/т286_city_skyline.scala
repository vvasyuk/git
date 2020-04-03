package com.tryout.DailyCodingProblems.p28

//The skyline of a city is composed of several buildings of various widths and heights, possibly
// overlapping one another when viewed from a distance. We can represent the buildings using an
// array of (left, right, height) tuples, which tell us where on an imaginary x-axis a building
// begins and ends, and how tall it is. The skyline itself can be described by a list of (x, height) tuples, giving the locations at which the height visible to a distant observer changes, and each new height.
//
//Given an array of buildings as described above, create a function that returns the skyline.
//
//For example, suppose the input consists of the buildings [(0, 15, 3), (4, 11, 5), (19, 23, 4)].
// In aggregate, these buildings would create a skyline that looks like the one below.
//
//     ______
//    |      |        ___
// ___|      |___    |   |
//|   |   B  |   |   | C |
//| A |      | A |   |   |
//|   |      |   |   |   |
//------------------------
//As a result, your function should return [(0, 3), (4, 5), (11, 3), (15, 0), (19, 4), (23, 0)].
object т286_city_skyline {
  def main(args: Array[String]): Unit = {

  }

}

// import heapq
//buildings =[(0, 15, 3), (4, 11, 5), (19, 23, 4)]
//print(buildings)                                        # [(0, 15, 3), (4, 11, 5), (19, 23, 4)]
//buildings += [(r, r, 0) for (_, r, _) in buildings]
//print(buildings)                                        # [(0, 15, 3), (4, 11, 5), (19, 23, 4), (15, 15, 0), (11, 11, 0), (23, 23, 0)]
//buildings.sort(key=lambda x: (x[0], -x[2]))
//print(buildings)                                        # [(0, 15, 3), (4, 11, 5), (11, 11, 0), (15, 15, 0), (19, 23, 4), (23, 23, 0)]

//sorting explanation, ascending by first el, desc by last
// buildings =[(0, 15, 1), (0, 15, 2), (0, 15, 3)]
// buildings.sort(key=lambda x: (x[0], -x[2]))               # [(0, 15, 3), (0, 15, 2), (0, 15, 1)]

//skyline = []
//heap = [(0, float("inf"))]
//print(heap)                                             # [(0, inf)]
//print(type(heap))                                       # <class 'list'>
//
//for left, right, height in buildings:
//    while heap and left >= heap[0][1]:
//        heapq.heappop(heap)
//# [(0, inf)]
//# [(-3, 15), (0, inf)]
//# [(-3, 15), (0, inf)]
//# [(0, inf)]
//# [(0, inf)]
//# [(0, inf)]
//    heapq.heappush(heap, (-height, right))
//# [(-3, 15), (0, inf)]
//# [(-5, 11), (0, inf), (-3, 15)]
//# [(-3, 15), (0, inf), (0, 11)]
//# [(0, 15), (0, inf)]
//# [(-4, 23), (0, inf)]
//# [(0, 23), (0, inf)]
//
//    if not skyline or skyline[-1][1] != -heap[0][0]:
//        skyline.append((left, -heap[0][0]))
//# [(0, 3)]
//# [(0, 3), (4, 5)]
//# [(0, 3), (4, 5), (11, 3)]
//# [(0, 3), (4, 5), (11, 3), (15, 0)]
//# [(0, 3), (4, 5), (11, 3), (15, 0), (19, 4)]
//# [(0, 3), (4, 5), (11, 3), (15, 0), (19, 4), (23, 0)]