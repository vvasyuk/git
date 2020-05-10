package com.tryout.DailyCodingProblems.p33

// Given a linked list, uniformly shuffle the nodes. What if we want to prioritize space over time?
object p337_shuffle_linkedlist {
  // Since we can uniformly shuffle an array in O(n) time via the Fisher-Yates shuffle, all that remains is converting the array of nodes back to a proper linked list. Luckily, this part is straightforward and also runs in O(n) time. Thus, our overall time complexity is O(n), and our space complexity also O(n).
  // import random
  //
  //class Node:
  //    def __init__(self, val):
  //        self.val = val
  //        self.next = None
  //
  //def shuffle(head):
  //    node_list = []
  //    while head is not None:
  //        node_list.append(head)
  //        head = head.next
  //
  //    for i in range(len(node_list)):
  //        j = random.randrange(i, len(node_list))
  //        node_list[i], node_list[j] = node_list[j], node_list[i]
  //
  //    dummy = Node(0)
  //    prev = dummy
  //    for node in node_list:
  //        prev.next = node
  //        prev = node
  //    prev.next = None
  //
  //    return dummy.next

  // We can also derive an O(nlogn) approach, taking inspiration from merge sort. If we divide the list in half each time and shuffle the nodes within each sublist, we can devise a way to merge the lists while maintaining that each outcome is uniformly random. The base case would be a list of size 1, where we return the list. If we have two lists of size 1, we choose either of the heads with probability 0.5 to be the head of the new list. However, for two lists with different sizes, we need to choose a ratio based on the size of the lists rather than 0.5. Here is an example of the possible merged lists and their respective probabilities:
}
