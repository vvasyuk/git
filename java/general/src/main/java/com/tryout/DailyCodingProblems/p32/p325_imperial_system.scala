package com.tryout.DailyCodingProblems.p32

// The United States uses the imperial system of weights and measures, which means that there are many different, seemingly arbitrary units to measure distance. There are 12 inches in a foot, 3 feet in a yard, 22 yards in a chain, and so on.
//
//Create a data structure that can efficiently convert a certain quantity of one unit to the correct amount of any other unit. You should also allow for additional units to be added to the system.
object p325_imperial_system {

}

// from collections import defaultdict, deque
//
//class Converter:
//    def __init__(self, standard='foot'):
//        self.graph = defaultdict(list)
//        self.graph[standard] = [(standard, 1.0)]
//
//    def add(self, unit, relative, multiplier):
//        if relative in self.graph:
//            self.graph[unit].append((relative, multiplier))
//            self.graph[relative].append((unit, 1.0 / multiplier))
//
//    def convert(self, unit):
//        visited = set()
//        results = {unit: 1.0}
//        queue = deque([(unit, 1.0)])
//
//        while queue:
//            curr, value = queue.popleft()
//            neighbors = self.graph[curr]
//
//            for neighbor, multiplier in neighbors:
//                if neighbor not in visited:
//                    results[neighbor] = value * multiplier
//                    queue.append((neighbor, value * multiplier))
//
//            visited.add(curr)
//
//        return results