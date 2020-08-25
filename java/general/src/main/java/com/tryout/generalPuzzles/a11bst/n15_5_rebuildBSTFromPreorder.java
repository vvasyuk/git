package com.tryout.generalPuzzles.a11bst;

import java.util.List;

// reconstruct the BST from the sequence
public class n15_5_rebuildBSTFromPreorder {
    // 43,23,37, 29,31,41,47,53
    // root must hold 43, since it's the first visited node
    // left subtree contains keys less than 43, i.e., 23,37,29,31,41, and the right subtree contains keys greater than 43, i.e., 47,53
    // Furthermore, <23,37,29,31,41) is exactly the preorder sequence for the left subtree and 47,53) is exactly the preorder sequence for the right subtree

    //Generalizing, in any preorder traversal sequence, the first key corresponds to the
    //root. The subsequence which begins at the second element and ends at the last key
    //less than the root, corresponds to the preorder traversal of the root's left subtree.
    //The final subsequence, consisting of keys greater than the root corresponds to the
    //preorder traversal of the root's right subtree. We recursively reconstruct the BST by
    //recursively reconstructing the left and right subtrees from the two subsequences then
    //adding them to the root.

    public static BSTNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence){
        return rebuildBSTFromPreorderHelper(preorderSequence , 0, preorderSequence.size());
    }
    // Builds a BST from preorderSequence.subList(start, end).
    private static BSTNode <Integer> rebuildBSTFromPreorderHelper(List<Integer> preorderSequence, int start, int end) {
        if (start >= end) { return null; }
        int transitionPoint = start + 1;
        while (transitionPoint < end && Integer.compare(preorderSequence.get(transitionPoint), preorderSequence.get(start)) < 0){
            ++transitionPoint;
        }
        return new BSTNode<>(
                preorderSequence.get(start),
                rebuildBSTFromPreorderHelper(preorderSequence, start + 1, transitionPoint),
                rebuildBSTFromPreorderHelper(preorderSequence, transitionPoint, end));
    }
}
