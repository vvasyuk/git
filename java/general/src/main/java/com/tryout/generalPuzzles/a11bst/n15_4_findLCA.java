package com.tryout.generalPuzzles.a11bst;

// Design an algorithm that takes as input a BST and two nodes, and returns the LCA (lowest common ancestor)
//of the two nodes
public class n15_4_findLCA {
    // Let s and b be the two nodes whose LCA we are to compute
    // If the root's key is the same as that stored at s or at b, we are done—the root is the LCA
    // If the key at s is smaller than the key at the root, and the key at b is greater than the key at the root, the root is the LCA.
    // If the keys at s and b are both smaller than that at the root, the LCA must he in the left subtree of the root.
    // If both keys are larger than that at the root, then the LCA must he in the right subtree of the root.
    public static BSTNode <Integer> findLCA (BSTNode <Integer> tree, BSTNode <Integer > s, BSTNode <Integer> b) {
        BSTNode<Integer> p = tree;
        while (p.data < s.data || p.data > b.data) {
            // Keep searching since p is outside of [s, b].
            while (p.data < s.data) {
                p = p.right; // LCA must be in p’s right child.
            }
            while (p.data > b.data) {
                p = p.left; // LCA must be in p’s left child.
            }
        }
        // Now, s.data >= p.data && p.data <- b.data.
        return p;
    }
}
