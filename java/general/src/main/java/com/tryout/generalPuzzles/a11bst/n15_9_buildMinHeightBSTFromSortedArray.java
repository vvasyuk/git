package com.tryout.generalPuzzles.a11bst;

import java.util.List;

// Given a sorted array, the number of BSTs that can be built on the entries in the array
//grows enormously with its size. Some of these trees are skewed, and are closer to
//lists; others are more balanced. See Figure 15.3 on Page 263 for an example.
//How would you build a BST of minimum possible height from a sorted array?
public class n15_9_buildMinHeightBSTFromSortedArray {
    // balance can be achieved by keeping the number of nodes in both subtrees as close as possible

    // Let n be the length of the array. To achieve optimum balance we can make the
    //element in the middle of the array, i.e., the Lf Jth entry, the root, and recursively
    //compute minimum height BSTs for the subarrays on either side of this entry

    // example array (2,3,5, 7,11,13,17,19, 23),
    // root 11.
    // left subtree is to be built from (2,3,5, 7), and the right subtree (13,17,19,23).
    // To make both of these minimum height, we call the procedure recursively.

    public static BSTNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer > A) {
        return buildMinHeightBSTFromSortedArrayHelper(A , 0, A.size());
    }
    // Build a min-height BST over the entries in A.subList(start , end - 1).
    private static BSTNode <Integer> buildMinHeightBSTFromSortedArrayHelper(List<Integer> A, int start, int end) {
        if (start >= end) {
            return null;
        }
        int mid = start + ((end - start) / 2);
        return new BSTNode<>(
                A.get(mid),
                buildMinHeightBSTFromSortedArrayHelper(A, start, mid),
                buildMinHeightBSTFromSortedArrayHelper(A, mid + 1, end));
    }
}
