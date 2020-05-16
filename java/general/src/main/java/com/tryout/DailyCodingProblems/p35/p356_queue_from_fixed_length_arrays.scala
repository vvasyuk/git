package com.tryout.DailyCodingProblems.p35

// Implement a queue using a set of fixed-length arrays.
//
//The queue should support enqueue, dequeue, and get_size operations.
object p356_queue_from_fixed_length_arrays {
// To understand how this arrays work, we can take a look at the following diagram, similar to the one given previously.
  //
  //last operation  |  head_array     |    tail_array
  //----------------------------------------------------------------
  //enqueue(2)      |  [1, 2, None]   |  [[1, 2, None]]
  //enqueue(3)      |  [1, 2, 3]      |  [[1, 2, 3]]
  //enqueue(4)      |  [1, 2, 3]      |  [[1, 2, 3], [4, None, None]]
  //dequeue()       |  [1, 2, 3]      |  [[1, 2, 3], [4, None, None]]
  //dequeue()       |  [1, 2, 3]      |  [[1, 2, 3], [4, None, None]]
  //dequeue()       |  [4, None, None]|  [[4, None, None]]]
  //enqueue(5)      |  [4, 5, None]   |  [[4, 5, None]]]
  //Here, we first enqueue two items to the queue, populating both the head and tail arrays. When we enqueue our next item, there is no room in our existing array for it, so we declare a new list and place this as its first element.
  //
  //We then dequeue from the head array until there is nothing left to take. At this point, the first array is useless, so we remove it and reassign the head array to the first list of the tail.
  //
  //As before, each time we add or remove an element, we update a size parameter, so that getting the length of our queue is a painless process.
  //
  //class Queue:
  //    def __init__(self, n):
  //        self.array_size = n
  //        self.head_array = [None] * n
  //        self.tail_array = [self.head_array]
  //        self.curr_array = 0
  //
  //        self.head = 0
  //        self.tail = 0
  //        self.size = 0
  //
  //    def enqueue(self, x):
  //        self.tail_array[self.curr_array][self.tail] = x
  //
  //        if self.tail == self.array_size - 1:
  //            self.tail_array.append([None] * self.array_size)
  //            self.curr_array += 1
  //
  //        self.tail = (self.tail + 1) % self.array_size
  //        self.size += 1
  //
  //    def dequeue(self):
  //        if self.size == 0:
  //            print('Cannot dequeue from empty queue.')
  //            return
  //
  //        result = self.head_array[self.head]
  //
  //        if self.head == self.array_size - 1:
  //            self.head_array = self.tail_array[1]
  //            self.tail_array = self.tail_array[1:]
  //            self.curr_array -= 1
  //
  //        self.head = (self.head + 1) % self.array_size
  //        self.size -= 1
  //
  //        return result
  //
  //    def get_size(self):
  //        return self.size
}
