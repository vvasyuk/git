package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// A binary tree is said to be height-balanced if for each node in the tree, the difference
//in the height of its left and right subtrees is at most one. A perfect binary tree is
//height-balanced, as is a complete binary tree. A height-balanced binary tree does not
//have to be perfect or complete—see Figure 10.2 on the facing page for an example.
//Write a program that takes as input the root of a binary tree and checks whether the
//tree is height-balanced.
public class n10_1_if_btree_height_balanced {
    private static class BalanceStatusWithHeight {
        public boolean balanced;
        public int height;

        public BalanceStatusWithHeight(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return checkBalanced(tree).balanced;
    }

    private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) {
            return new BalanceStatusWithHeight(true, -1); // Base case.
        }
        BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
        if (!leftResult.balanced) {
            return leftResult; // Left subtree is not balanced.
        }
        BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
        if (!rightResult.balanced) {
            return rightResult; // Right subtree is not balanced.
        }
        boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new BalanceStatusWithHeight(isBalanced, height);
    }
}
