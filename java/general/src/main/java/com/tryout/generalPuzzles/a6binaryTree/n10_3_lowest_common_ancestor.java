package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// Any two nodes in a binary tree have a common ancestor, namely the root. The lowest
//common ancestor (LCA) of any two nodes in a binary tree is the node furthest from
//the root that is an ancestor of both nodes
// Computing the LCA has important applications. For example, it is an essential
//calculation when rendering web pages, specifically when computing the Cascading
//Style Sheet (CSS) that is applicable to a particular Document Object Model (DOM)
//element
public class n10_3_lowest_common_ancestor {
    // The insight to a better time complexity is that we do not need to perform multiple
    //passes. If the two nodes are in a subtree, we can compute the LCA directly, instead
    //of simply returning a Boolean indicating that both nodes are in that subtree. The
    //program below returns an object with two fields—the first is an integer indicating
    //how many of the two nodes were present in that subtree, and the second is their LCA,
    //if both nodes were present.
    private static class Status {
        public int numTargetNodes;
        public BinaryTreeNode<Integer> ancestor;

        public Status(int numTargetNodes, BinaryTreeNode<Integer> node) {
            this.numTargetNodes = numTargetNodes;
            this.ancestor = node;
        }
    }

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode <Integer> tree, BinaryTreeNode <Integer> node0, BinaryTreeNode <Integer> node1) {
        return LCAHelper(tree , node0, node1).ancestor ;
    }
    // Returns an object consisting of an int and a node. The int field is
    // 0, 1, or 2 depending on how many of {node0 , nodel} are present in
    // the tree. If both are present in the tree, when ancestor is
    // assigned to a non-null value, it is the LCA.
    private static Status LCAHelper(BinaryTreeNode <Integer> tree, BinaryTreeNode <Integer> node0, BinaryTreeNode <Integer> node1) {
        if (tree == null) {
            return new Status(0, null);
        }
        Status leftResult = LCAHelper(tree.left, node0, node1);
        if (leftResult.numTargetNodes == 2) {
        // Found both nodes in the left subtree.
            return leftResult;
        }
        Status rightResult = LCAHelper(tree.right, node0, node1);
        if (rightResult.numTargetNodes == 2) {
        // Found both nodes in the right subtree.
            return rightResult;
        }
        int numTargetNodes = leftResult.numTargetNodes + rightResult.numTargetNodes
                + (tree == node0 ? 1 : 0)+(tree == node1 ? 1 : 0);
        return new Status(numTargetNodes, numTargetNodes == 2 ? tree : null);
    }
}
