package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// This problem is concerned with traversing nodes in a binary tree in an inorder fashion.
// Write a program which takes as input a binary tree and performs an inorder traversal
//of the tree. Do not use recursion. Nodes do not contain parent references.
public class n10_7_inorder_traversal_without_recursion {
    // The recursive solution is trivial—first traverse the left subtree, then visit
    //the root, and finally traverse the right subtree. This algorithm can be converted into a
    //iterative algorithm by using an explicit stack. Several implementations are possible;
    //the one below is noteworthy in that it pushes the current node, and not its right child.
    //Furthermore, it does not use a visited field.

    public static List<Integer> BSTInSortedOrder (BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreeNode<Integer>> s = new LinkedList<>();
        BinaryTreeNode<Integer> curr = tree;
        List<Integer> result = new ArrayList<>();
        while (!s.isEmpty() || curr != null) {
            if (curr != null) {
                s.addFirst(curr);
                // Going left.
                curr = curr.left;
            } else {
                // Going up .
                curr = s.removeFirst();
                result.add(curr.data);
                // Going right.
                curr = curr.right;
            }
        }
        return result;
    }
}
