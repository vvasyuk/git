package com.tryout.generalPuzzles.a6binaryTree;

import com.tryout.generalPuzzles.a5stacksAndQueues.BinaryTreeNode;

import java.util.List;

//Design an algorithm for reconstructing a binary tree from a preorder traversal visit
//sequence that uses null to mark empty children.
public class n10_13_reconstruct_from_preorder_btree {
    //The intuition for a better algorithm is the recognition that the first node in the
    //sequence is the root, and the sequence for the root's left subtree appears before all the
    //nodes in the root's right subtree. It is not easy to see where the left subtree sequence
    //ends. However, if we solve the problem recursively, we can assume that the routine
    //correctly computes the left subtree, which will also tell us where the right subtree
    //begins
    private static Integer subtreeIdx;
    public static BinaryTreeNode<Integer> reconstructPreorder (List<Integer> preorder) {
        subtreeIdx = 0;
        return reconstructPreorderSubtree(preorder) ;
    }
    // Reconstructs the subtree that is rooted at subtreeldx .
    private static BinaryTreeNode <Integer> reconstructPreorderSubtree (List<Integer > preorder) {
        Integer subtreeKey = preorder.get(subtreeIdx);
        ++subtreeIdx;
        if (subtreeKey == null) {
            return null;
        }
        // Note that reconstructPreorderSubtree updates subtreeldx. So the order of
        // following two calls are critical.
        BinaryTreeNode<Integer> leftSubtree = reconstructPreorderSubtree(preorder);
        BinaryTreeNode<Integer> rightSubtree = reconstructPreorderSubtree(preorder);
        return new BinaryTreeNode(subtreeKey, leftSubtree, rightSubtree);
    }
}
