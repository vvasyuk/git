package com.tryout.DailyCodingProblems.p35

// Create a data structure that performs all the following operations in O(1) time:
//
//plus: Add a key with value 1. If the key already exists, increment its value by one.
//minus: Decrement the value of a key. If the key's value is currently 1, remove it.
//get_max: Return a key with the highest value.
//get_min: Return a key with the lowest value.
object p358_plus_minus_max_min {
// from collections import defaultdict
  //
  //class FastClass:
  //    def __init__(self):
  //        self.dll = DoublyLinkedList()
  //        self.key_counts = defaultdict(int)
  //        self.count_node_map = {0: self.dll.head}
  //
  //    def remove_key(self, count, key):
  //        node = self.count_node_map[count]
  //        node.remove(key)
  //
  //        if not node.keys:
  //            self.dll.remove(node)
  //            self.count_node_map.pop(count)
  //
  //    def plus(self, key):
  //        self.key_counts[key] += 1
  //        count = self.key_counts[key]
  //        prev_count = count - 1
  //
  //        if count not in self.count_node_map:
  //            last_node = self.count_node_map[prev_count]
  //            self.count_node_map[count] = self.dll.insert_after(last_node, key)
  //
  //        else:
  //            self.count_node_map[count].add(key)
  //
  //        if prev_count > 0:
  //            self.remove_key(prev_count, key)
  //
  //    def minus(self, key):
  //        if key not in self.key_counts:
  //            return
  //
  //        self.key_counts[key] -= 1
  //        count = self.key_counts[key]
  //        prev_count = count + 1
  //
  //        if count == 0:
  //            self.key_counts.pop(key)
  //
  //        else:
  //            if count not in self.count_node_map:
  //                last_node = self.count_node_map[prev_count].prev
  //                self.count_node_map[count] = self.dll.insert_after(last_node, key)
  //            else:
  //                self.count_node_map[count].add(key)
  //
  //        self.remove_key(prev_count, key)
  //
  //    def get_max(self):
  //        last_node = self.dll.tail.prev
  //
  //        if last_node.keys:
  //            return last_node.select_key()
  //        else:
  //            return None
  //
  //    def get_min(self):
  //        first_node = self.dll.head.next
  //
  //        if first_node.keys:
  //            return first_node.select_key()
  //        else:
  //            return None
}
