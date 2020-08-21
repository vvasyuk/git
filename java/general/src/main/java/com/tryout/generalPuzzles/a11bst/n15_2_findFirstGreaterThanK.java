package com.tryout.generalPuzzles.a11bst;

// Write a program that takes as input a BST and a value, and returns the first key
//that would appear in an inorder traversal which is greater than the input value
public class n15_2_findFirstGreaterThanK {
    // store the best candidate for the result and update that candidate as we iteratively descend the tree
    // if the current subtree's root holds a value less than or equal to the input value, we search the right subtree
    // If the current subtree's root stores a key that is greater than the input value, we search in the left subtree, updating the candidate to the current root

    public static BSTNode<Integer> findFirstGreaterThanK(BSTNode<Integer> tree, Integer k) {
        BSTNode<Integer> subtree = tree, firstSoFar = null;
        while (subtree != null) {
            if (subtree.data > k) {
                firstSoFar = subtree;
                subtree = subtree.left ;
            } else { // Root and all keys in left-subtree are <= k, so skip them.
                subtree = subtree.right ;
            }
        }
        return firstSoFar;
    }
}
