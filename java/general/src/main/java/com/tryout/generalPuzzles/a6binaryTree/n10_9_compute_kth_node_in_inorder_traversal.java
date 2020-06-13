package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// Write a program that efficiently computes the kth node appearing in an inorder
//traversal. Assume that each node stores the number of nodes in the subtree rooted at
//that node
public class n10_9_compute_kth_node_in_inorder_traversal {
    // Looking carefully at the brute-force algorithm, observe that it does not take ad¬
    //vantage of the information present in the node. For example, if k is greater than the
    //number of nodes in the left subtree, the kth node cannot lie in the left subtree. More
    //precisely, if the left subtree has L nodes, then the kth node in the original tree is the
    //(k- L)th node when we skip the left subtree. Conversely, if k < L, the desired node
    //lies in the left subtree. For example, the left subtree in Figure 10.1 on Page 150 has
    //seven nodes, so the tenth node cannot in the left subtree. Instead it is the third node
    //if we skip the left subtree. This observation leads to the following program.

//    public static BinaryTreeNode<Integer> findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
//        BinaryTreeNode<Integer> iter = tree;
//        while (iter != null) {
//            int leftSize = iter.left != null ? iter.left.size : 0;
//            if (leftSize + 1 < k) { // k-th node must be in right subtree of iter.
//                k -= (leftSize + 1);
//                iter = iter.right;
//            } else if (leftSize == k - 1) { // k-th is iter itself.
//                return iter;
//            } else { // k-th node must be in left subtree of iter.
//                iter = iter.left;
//            }
//        }
//        // If k is between 1 and the tree size, this line is unreachable .
//        return null;
//    }
}
