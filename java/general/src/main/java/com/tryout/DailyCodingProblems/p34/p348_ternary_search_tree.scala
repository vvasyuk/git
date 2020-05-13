package com.tryout.DailyCodingProblems.p34

// A ternary search tree is a trie-like data structure where each node may have up to three children. Here is an example which represents the words code, cob, be, ax, war, and we.
//
//       c
//    /  |  \
//   b   o   w
// / |   |   |
//a  e   d   a
//|    / |   | \
//x   b  e   r  e
//The tree is structured according to the following rules:
//
//left child nodes link to words lexicographically earlier than the parent prefix
//right child nodes link to words lexicographically later than the parent prefix
//middle child nodes continue the current word
//For instance, since code is the first word inserted in the tree, and cob lexicographically precedes cod, cob is represented as a left child extending from cod.
//
//Implement insertion and search functions for a ternary search tree.
object p348_ternary_search_tree {
  //class Node:
  //    def __init__(self, data):
  //        self.data = data
  //        self.left = None
  //        self.mid = None
  //        self.right = None
  //        self.end = False

  //With this in place, we can think about how to define our insertion function. If there are currently no nodes in our tree, the process is straightforward: we keep adding mid child nodes with each consecutive letter until we spell out the whole word, and set the last child's end value to be True.
  //
  //To handle more complicated cases, it helps to think recursively. At each step, we will consider two objects: the current node, and the remainder of the word that must be inserted. If the current node is None, we find ourselves in the case above. Otherwise, we can consider three possibilities:
  //
  //If the first letter of the word remainder precedes the current node's letter, we branch left.
  //If the first letter of the word remainder succeeds the current node's letter, we branch right.
  //If the two are equal, we continue to the middle child node with the tail of our word.
  //Since this recursive process applies to an arbitrary node and suffix, we will call it as a helper from our main insert function, which updates the root of our tree.
  //

  //class TernaryTree:
  //    def __init__(self):
  //        self.root = None
  //
  //    def _insert(self, node, word):
  //        if not word:
  //            return node
  //
  //        head, tail = word[0], word[1:]
  //        if not node:
  //            node = Node(head)
  //
  //        if head < node.data:
  //            node.left = self._insert(node.left, word)
  //        elif head > node.data:
  //            node.right = self._insert(node.right, word)
  //        else:
  //            if not tail:
  //                node.end = True
  //            else:
  //                node.mid = self._insert(node.mid, tail)
  //
  //        return node
  //
  //    def insert(self, word):
  //        self.root = self._insert(self.root, word)
}
