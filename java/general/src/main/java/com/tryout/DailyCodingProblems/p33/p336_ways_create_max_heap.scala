package com.tryout.DailyCodingProblems.p33

// Write a program to determine how many distinct ways there are to create a max heap from a list of N given integers.
//
//For example, if N = 3, and our integers are [1, 2, 3], there are two ways, shown below.
//
//  3      3
// / \    / \
//1   2  2   1
object p336_ways_create_max_heap {
// While this problem is about heaps, we do not actually need to use a heap to solve it. Rather, we can use good old-fashioned math.
  //
  //First note that for a given max heap of size N, there is no choice but to place the largest element as the root. Once we have done this, we are left with N - 1 elements that we must split between the left and right subtrees.
  //
  //If we let L be the number of integers in the left subtree, and R be the number of integers in the right, then we can describe the total ways of constructing the heap as:
  //
  //f(N) = choose(N, L) * f(L) * f(N - 1 - L)
  //
  //Here, choose(n, k) denotes the number of ways of choosing k distinct elements from a group of N. In other words, we can take any group of L elements to form the left subtree. Then, the elements that are not in the left subtree, except for the root, will form the right subtree. Lastly, we recursively solve for the arrangement of each subtree.
  //
  //The only task remaining is to solve for L. Let us look at a sample tree with ten elements as we figure this out.
  //
  //           1
  //        /     \
  //      2         3
  //    /   \     /   \
  //  4      5   6     7
  // / \    /
  //8   9  10
  //We can first calculate the height of the tree up to the last complete row, which will be the largest power of two not exceeding N. For the above tree the height will be three.
  //
  //Next, the number of leftover nodes on the bottom row can be obtained by subtracting the number of nodes in a complete tree with the above height from N. Here, this value will be three as well. It will either be the case that all the leftover nodes will be contained in the left subtree, or we will hit a limit of 2height - 1.
  //
  //Combining the left side nodes from the complete tree without the last level, and the bottom nodes that belong to the left tree, we come to the formula L = 2height - 1- 1 + min(2height - 1, bottom).
}

// from math import log
//
//def get_factorials(n):
//    factorials = {0: 1}
//
//    for i in range(1, n + 1):
//        factorials[i] = i * factorials[i - 1]
//
//    return factorials
//
//def choose(factorials, n, k):
//    return factorials[n] // factorials[n - k] // factorials[k]
//
//def ways(n, f):
//    if n <= 2:
//        return 1
//
//    height = int(log(n)) + 1
//    bottom = n - (2 ** height - 1)
//
//    left = 2 ** (height - 1) - 1 + min(2 ** (height - 1), bottom)
//    right = n - left - 1
//
//    return choose(f, n - 1, left) * ways(left, f) * ways(right, f)
//
//def num_heaps(n):
//    factorials = get_factorials(n)
//    return ways(n, factorials)