package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// A binary tree is symmetric if you can draw a vertical line through the root and then
//the left subtree is the mirror image of the right subtree. The concept of a symmetric
//binary tree is illustrated in Figure 10.3 on the facing page.
//Write a program that checks whether a binary tree is symmetric.
public class n10_2_if_btree_is_symetric {
    // We can test if a tree is symmetric by computing its mirror image and seeing
    //if the mirror image is equal to the original tree. Computing the mirror image of a tree
    //is as simple as swapping the left and right subtrees, and recursively continuing. The
    //time and space complexity are both 0(n), where n is the number of nodes in the tree.

    public static boolean isSymmetric (BinaryTreeNode<Integer> tree) {
        return tree == null || checkSymmetric (tree . left , tree. right);
    }

    private static boolean checkSymmetric (BinaryTreeNode<Integer> subtree0, BinaryTreeNode <Integer> subtree1) {
        if (subtree0 == null && subtree1 == null) {
            return true;
        } else if (subtree0 != null && subtree1 != null) {
            return subtree0.data == subtree1.data &&
                    checkSymmetric(subtree0.left, subtree1.right) &&
                    checkSymmetric(subtree0.right, subtree1.left);
        }
        // One subtree is empty, and the other is not.
        return false;
    }
}
