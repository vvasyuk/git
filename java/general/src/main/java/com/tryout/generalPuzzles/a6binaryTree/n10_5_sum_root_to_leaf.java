package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// Consider a binary tree in which each node contains a binary digit. A rootto-
//leaf path can be associated with a binary number—the MSB is at the
//root. As an example, the binary tree in Figure 10.4 represents the numbers
//(1000)2, (1001)2, (10110)2, (110011)2, (11000)2, and (1100)2
public class n10_5_sum_root_to_leaf {
    // Therefore, we can compute the sum of all root to leaf node as follows. Each time
    //we visit a node, we compute the integer it encodes using the number for its parent.
    //If the node is a leaf we return its integer. If it is not a leaf, we return the sum of the
    //results from its left and right children.

    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return sumRootToLeafHelper(tree, 0);
    }

    private static int sumRootToLeafHelper(BinaryTreeNode<Integer> tree, int partialPathSum) {
        if (tree == null) {
            return 0;
        }
        partialPathSum = partialPathSum * 2 + tree.data;
        if (tree.left == null && tree.right == null) { // Leaf.
            return partialPathSum;
        }
        // Non-leaf .
        return sumRootToLeafHelper(tree.left, partialPathSum) + sumRootToLeafHelper(tree.right, partialPathSum);
    }
}
