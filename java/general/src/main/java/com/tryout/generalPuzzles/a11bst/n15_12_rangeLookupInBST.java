package com.tryout.generalPuzzles.a11bst;

// Consider the problem of developing a web-service that takes a geographical loca¬
//tion, and returns the nearest restaurant. The service starts with a set of restaurant
//locations—each location includes X and Y-coordinates. A query consists of a location,
//and should return the nearest restaurant

import java.util.ArrayList;
import java.util.List;

// Write a program that takes as input a BST and an interval and returns the BST keys
//that lie in the interval.
public class n15_12_rangeLookupInBST {
    // 1. If the root of the tree holdsa key that is less than the left endpoint of the interval,
    //the left subtree cannot contain any node whose key lies in the interval.
    // 2.If the root of the tree holds a key that is greater than the right endpoint of
    //the interval, the right subtree cannot contain any node whose key lies in the
    //interval.
    // 3. Otherwise, the root of the tree holds a key that lies within the interval, and it is
    //possible for both the left and right subtrees to contain nodes whose keys he in
    //the interval.

    private static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> rangeLookupInBST(BSTNode<Integer> tree, Interval interval) {
        List<Integer> result = new ArrayList<>();
        rangeLookupInBSTHelper(tree, interval, result);
        return result;
    }

    public static void rangeLookupInBSTHelper(BSTNode<Integer> tree, Interval interval, List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            // tree.data lies in the interval.
            rangeLookupInBSTHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBSTHelper(tree.right, interval, result);
        } else if (interval.left > tree.data) {
            rangeLookupInBSTHelper(tree.right, interval, result);
        } else { // interval.right >= tree.data
            rangeLookupInBSTHelper(tree.left, interval, result);
        }
    }
}
