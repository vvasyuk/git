package com.tryout.generalPuzzles.a11bst;

// Write a program which takes two nodes in a BST and a third node, the "middle"
//node, and determines if one of the two nodes is a proper ancestor and the other a
//proper descendant of the middle.
public class n15_11_pairlncludesAncestorAndDescendantOfM {
    // performing the searches for the middle from both alternatives in an interleaved fashion. If we encounter the middle from one node, we
    //subsequently search for the second node from the middle. This way we avoid per¬
    //forming an unsuccessful search on a large subtree.

    public static boolean pairlncludesAncestorAndDescendantOfM(BSTNode<Integer> possibleAncOrDesc0 , BSTNode<Integer> possibleAncOrDesc1 , BSTNode<Integer> middle) {
        BSTNode <Integer> search0 = possibleAncOrDesc0, search1 = possibleAncOrDesc1;
        // Perform interleaved searching from possibleAncOrDesc0 and
        // possibleAncOrDesc1 for middle.
        while (search0 != possibleAncOrDesc1 && search0 != middle
                && search1 != possibleAncOrDesc0 && search1 != middle
                && (search0 != null || search1 != null)) {
            if (search0 != null) {
                search0 = search0.data > middle.data ? search0.left : search0.right ;
            }
            if (search1 != null) {
                search1 = search1.data > middle.data ? search1.left : search1.right ;
            }
        }
        // If both searches were unsuccessful , or we got from possibleAncOrDesc1
        // to possibleAncOrDesc1 without seeing middle, or from possibleAncOrDesc1
        // to possibleAncOrDesc0 without seeing middle, middle cannot lie between
        // possibleAncOrDesc0 and possibleAncOrDesc1 .
        if (search0 == possibleAncOrDesc1 || search1 == possibleAncOrDesc0  || (search0 != middle && search1 != middle)) {
            return false;
        }
        // If we get here, we already know one of possibleAncOrDesc0 or
        // possibleAncOrDesc1 has a path to middle. Check if middle has a path to
        // possibleAncOrDesc1 or to possibleAncOrDesc0.
        return search0 == middle ? searchTarget(middle , possibleAncOrDesc1)
                : searchTarget(middle , possibleAncOrDesc0);
    }
    private static boolean searchTarget(BSTNode<Integer> from, BSTNode<Integer> target) {
        while (from != null && from != target) {
            from = from.data > target.data ? from.left : from.right;
        }
        return from == target;
    }
}
