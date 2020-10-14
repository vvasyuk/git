package com.tryout.generalPuzzles.a12recursion;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

// Write a program which returns all distinct binary trees with a specified number of
//nodes.
public class n16_8_generateAUBinaryTrees {
    //The key to efficiency is to direct the search. If the left child has k nodes, we should
    //only use right children with n — 1 — k nodes, to get binary trees with n nodes that
    //have that left child. Specifically, we get all binary trees on n nodes by getting all left
    //subtrees on i nodes, and right subtrees on n-1- i nodes, for i between 0 and n — 1.
    //Looking carefully at Figure 16.5, you will see the first two trees correspond to the
    //trees on three nodes which have a left subtree of size 0 and a right subtree of size 2.
    //The third tree is the only tree on three nodes which has a left subtree of size1 and a
    //right subtree of size1. The last two trees correspond to the trees on three nodes which
    //have a left subtree of size 2 and a right subtree of size 0. The set of two trees on two
    //nodes is itself computed recursively: there is a single binary tree on one node, and it
    //may be on either side of the root.
    public static List<BinaryTreeNode<Integer>> generateAUBinaryTrees (int numNodes) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        if (numNodes == 0){ // Empty tree, add as an null.
            result.add(null);
        }
        for (int numLeftTreeNodes = 0; numLeftTreeNodes <numNodes; ++numLeftTreeNodes){
            int numRightTreeNodes = numNodes - 1 - numLeftTreeNodes;
            List<BinaryTreeNode<Integer>> leftSubtrees
                    = generateAUBinaryTrees(numLeftTreeNodes);
            List<BinaryTreeNode<Integer>> rightSubtrees
                    = generateAUBinaryTrees(numNodes - 1 - numLeftTreeNodes);
            // Generates all combinations of leftSubtrees and rightSubtrees.
            for (BinaryTreeNode<Integer> left:
            leftSubtrees){
                for (BinaryTreeNode<Integer> right : rightSubtrees) {
                    result.add(new BinaryTreeNode<>(0 , left, right));
                }
            }
        }
        return result;
    }
}
