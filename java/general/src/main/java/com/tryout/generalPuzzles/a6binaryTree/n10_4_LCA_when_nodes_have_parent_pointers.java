package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

// Given two nodes in a binary tree, design an algorithm that computes their LCA.
//Assume that each node has a parent pointer.
public class n10_4_LCA_when_nodes_have_parent_pointers {

    public static BinaryTreeNode<Integer> LCA (BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1) {
        int depth0 = getDepth(node0), depth1 = getDepth(node1);
        // Makes node(9 as the deeper node in order to simplify the code.
        if (depth1 > depth0) {
            BinaryTreeNode<Integer> temp = node0;
            node0 = node1;
            node1 = temp;
        }
        // Ascends from the deeper node.
        int depthDiff = Math.abs(depth0 - depth1);
        while (depthDiff-- > 0) {
            node0 = node0.parent;
        }
        while (node0 !=node1){
            node0 =node0.parent;
            node1 = node1.parent;
        }
        return node0;
    }
    private static int getDepth(BinaryTreeNode<Integer> node) {
        int depth = 0;
        while (node.parent != null) {
            ++depth;
            node = node.parent;
        }
        return depth;
    }
}
