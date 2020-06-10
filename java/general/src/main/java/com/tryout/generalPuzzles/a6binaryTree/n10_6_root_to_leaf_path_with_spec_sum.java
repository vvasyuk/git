package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// You are given a binary tree where each node is labeled with an integer. The path
//weight of a node in such a tree is the sum of the integers on the unique path from the
//root to that node.
// Write a program which takes as input an integer and a binary tree with integer node
//weights, and checks if there exists a leaf whose path weight equals the given integer.
public class n10_6_root_to_leaf_path_with_spec_sum {
    // A better approach is to traverse the tree, keeping track of the root-to-node path
//sum. The first time we encounter a leaf whose weight equals the target weight, we
//have succeeded at locating a desired leaf. Short circuit evaluation of the check ensures
//that we do not process additional leaves
    public static boolean hasPathSum(BinaryTreeNode<Integer> tree, int targetSum) {
        return hasPathSumHelper(tree, 0, targetSum);
    }

    private static boolean hasPathSumHelper(BinaryTreeNode<Integer> node, int partialPathSum, int targetSum) {
        if (node == null) {
            return false;
        }
        partialPathSum += node.data;
        if (node.left == null && node.right == null) { // Leaf.
            return partialPathSum == targetSum;
        }
        // Non-leaf .
        return hasPathSumHelper(node.left, partialPathSum, targetSum) || hasPathSumHelper(node.right, partialPathSum, targetSum);
    }
}
