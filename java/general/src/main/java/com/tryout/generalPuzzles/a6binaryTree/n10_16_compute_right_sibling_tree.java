package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// For this problem, assume that each binary tree node has a extra field, call it level-next,
//that holds a binary tree node (this field is distinct from the fields for the left and right
//children). The level-next field will be used to compute a map from nodes to their
//right siblings. The input is assumed to be perfect binary tree. See Figure 10.6 for an
//example.
//Write a program that takes a perfect binary tree, and sets each node's level-next field
//to the node on its right, if one exists.

public class n10_16_compute_right_sibling_tree {
    //A brute-force approach is to compute the depth of each node, which is
    //stored in a hash table. Next we order nodes at the same depth using inorder visit
    //times. Then we set each node's level-next field according to this order. The time and
    //space complexity are 0(n),where n is the number of nodes.

    //The key insight into solving this problem with better space complexity is to use
    //the structure of the tree. Since it is a perfect binary tree, for a node which is a left
    //child, its right sibling is just its parent's right child. For a node which is a right child,
    //its right sibling is its parent's right sibling's left child

//    public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
//        BinaryTreeNode<Integer> leftStart = tree;
//        while (leftStart != null && leftStart.left != null){
//            populateLowerLevelNextField(leftStart);
//            leftStart = leftStart.left;
//        }
//    }
//
//    private static void populateLowerLevelNextField(BinaryTreeNode<Integer> startNode) {
//        BinaryTreeNode<Integer> iter = startNode;
//        while (iter != null) {
//            // Populate left child’s next field.
//            iter.left.next = iter.right;
//            // Populate right child’s next field if iter is not the last node of this level .
//            if (iter.next != null) {
//                iter.right.next = iter.next.left;
//            }
//            iter = iter.next;
//        }
//    }
}
