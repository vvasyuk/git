package com.tryout.generalPuzzles.a11bst;

import java.util.ArrayList;
import java.util.List;

// Write a program that takes as input a BST and an integer k, and returns the k largest
//elements in the BST in decreasing order.
public class n15_3_findKLargestlnBST {

    public static List<Integer> findKLargestlnBST(BSTNode<Integer> tree, int k){
        List<Integer> kLargestElements = new ArrayList<>();
        findKLargestlnBSTHelper(tree, k, kLargestElements);
        return kLargestElements;
    }
    private static void findKLargestlnBSTHelper(BSTNode<Integer> tree, int k, List<Integer> kLargestElements) {
        // Perform reverse inorder traversal.
        if (tree != null && kLargestElements.size() < k) {
            findKLargestlnBSTHelper(tree.right , k, kLargestElements);
            if (kLargestElements.size() < k) {
                kLargestElements.add(tree.data);
                findKLargestlnBSTHelper(tree.left , k, kLargestElements);
            }
        }
    }
}
