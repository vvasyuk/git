package com.tryout.DailyCodingProblems.p34

// You are given a huge list of airline ticket prices between different cities around the world on a given day.
// These are all direct flights. Each element in the list has the format (source_city, destination, price).
//
//Consider a user who is willing to take up to k connections from their origin city A to their destination B.
// Find the cheapest fare possible for this journey and print the itinerary for that journey.
//
//For example, our traveler wants to go from JFK to LAX with up to 3 connections, and our input flights are as follows:
//
//[
//    ('JFK', 'ATL', 150),
//    ('ATL', 'SFO', 400),
//    ('ORD', 'LAX', 200),
//    ('LAX', 'DFW', 80),
//    ('JFK', 'HKG', 800),
//    ('ATL', 'ORD', 90),
//    ('JFK', 'LAX', 500),
//]
//Due to some improbably low flight prices, the cheapest itinerary would be JFK -> ATL -> ORD -> LAX, costing $440.
object p346_cheapest_flight {
  // Let's first think about how we would approach this problem without the constraint of limiting our traveler to k connections.
  // This reduces to finding the shortest path between two points in a graph, which can be efficiently solved using Dijkstra's algorithm.
  //We will maintain a heap that is keyed on the total cost of the journey so far, and which additionally holds the current node and
  // the accumulated path. Initially, this heap will store a single item representing the fact that it costs nothing to begin at
  // our source airport.
  //At each step of the process, we pop the lowest cost item off the heap. Then, we take all unvisited connecting airports and place
  // them on the heap, with their accumulated flight cost and path. Once we reach our destination, we return these values.
  //To handle the extra constraint, we can add another variable to each heap item representing how many remaining connections are allowed.
  // Initially this will be k + 1, and for each flight taken we will decrement by one. If we reach 0, we know that we cannot continue the current path, so we will skip to the next item.

  // import heapq
  //
  //from collections import defaultdict
  //
  //def get_itinerary(flights, source, destination, k):
  //    prices = defaultdict(dict)
  //
  //    for u, v, cost in flights:
  //        prices[u][v] = cost
  //
  //    path = [source]
  //    visited = set()
  //    heap = [(0, source, k + 1, path)]
  //
  //    while heap:
  //        visited.add(u)
  //        cost, u, k, path = heapq.heappop(heap)
  //
  //        # Stop once we reach our destination.
  //        if u == destination:
  //            return cost, path
  //
  //        # Decrement k with each flight taken so we do not exceed k connections.
  //        if k > 0:
  //            for v in prices[u]:
  //                if v not in visited:
  //                    heapq.heappush(heap, (prices[u][v] + cost, v, k - 1, path + [v]))
  //
  //    return -1
}
