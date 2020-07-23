package com.tryout.generalPuzzles.a9hashTables;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.HashSet;
import java.util.Set;

// Design an algorithm for computing the LCA of two nodes in a binary tree. The
//algorithm's time complexity should depend only on the distance from the nodes to
//the LCA.
public class n13_4_compute_LCA {
    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {
        Set<BinaryTreeNode<Integer>> hash = new HashSet<>();
        while (node0 != null || node1 != null) {
            // Ascend tree in tandem from these two nodes.
            if (node0 != null) {
                if (!hash.add(node0)) {
                    return node0;
                }
                node0 = node0.parent ;
            }
            if (node1 != null) {
                if (! hash.add(node1)) {
                    return node1;
                }
                node1 = node1.parent ;
            }
        }
        throw new IllegalArgumentException (
                "node0 and node1 are not in the same tree");
    }
}
