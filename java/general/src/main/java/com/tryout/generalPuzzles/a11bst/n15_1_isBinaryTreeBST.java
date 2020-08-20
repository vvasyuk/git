package com.tryout.generalPuzzles.a11bst;

// Write a program that takes as input a binary tree and checks if the tree satisfies the BST property.
import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

public class n15_1_isBinaryTreeBST {
    // check that the key at the root is greater than or equal to the maximum from the left subtree and less than or equal to the minimum from the right subtree
    
    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return areKeysInRange(tree , Integer.MIN_VALUE , Integer.MAX_VALUE);
    }

    private static boolean areKeysInRange(BinaryTreeNode <Integer> tree, Integer lower, Integer upper) {
        if (tree == null) {
            return true ;
        } else if (Integer.compare(tree.data, lower) < 0 || Integer.compare(tree.data , upper) > 0) {
            return false;
        }
        return areKeysInRange(tree.left , lower, tree.data) && areKeysInRange(tree.right , tree.data, upper);
    }
}
