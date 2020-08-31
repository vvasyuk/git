package com.tryout.generalPuzzles.a11bst;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

// Design an algorithm for efficiently computing the k smallest numbers of the form
//a + b sqrt(2) for nonnegative integers a and b.
public class n_15_7_generateFirstKABSqrt2 {
    // fact about sqrt(2) is that it is irrational
    // We know the smallest number is 0+ 0 sqrt(2).
    // next smallest number are 1 + 0 sqrt(2) and 0 + 1 sqrt(2)
    // We perform k extractions of thesmallest element, call it a+ b sqrt(2), followed by insertion of (a+l)+b sqrt(2) and a + (b + 1) sqrt(2) to the collection

    //  We extract the minimum from the BST, which is
    //0 + 0 sqrt(2), and insert 1 + 0 sqrt(2) and 0 + 1 sqrt(2) to the BST. We extract the minimum from
    //the BST, which is 1 + 0 sqrt(2), and insert 2 + 0 sqrt(2) and 1 + 1 sqrt(2) to the BST, which now
    //consists of 0 + 1 sqrt(2) = 1.414, 2 + 0 sqrt(2) = 2, 1 + 1 sqrt(2) = 2.414. We extract the minimum
    //from the BST, which is 0 + 1 sqrt(2), and insert 1 + 1 sqrt(2) and 0 + 2 sqrt(2). The first value is
    //already present, so the BST updates to 2 + 0 sqrt(2) = 2, l + l sqrt(2) = 2.414, 0 + 2 sqrt(2) = 2.828.
    public static class ABSqrt2 implements Comparable <ABSqrt2> {
        public int a, b;
        public double val ;
        public ABSqrt2(int a, int b) {
            this.a = a;
            this.b = b;
            val = a + b * Math.sqrt(2);
        }
        @Override
        public int compareTo(ABSqrt2 o) { return Double.compare(val , o.val); }
    }
    public static List<ABSqrt2> generateFirstKABSqrt2(int k) {
        SortedSet<ABSqrt2> candidates = new TreeSet<>();
        // Initial for <S + Q * sqrt(2).
        candidates.add(new ABSqrt2(0, 0));
        List<ABSqrt2> result = new ArrayList<>();
        while (result.size() < k) {
            ABSqrt2 nextSmallest = candidates.first();
            result.add(nextSmallest);
            // Add the next two numbers derived from nextSmallest.
            candidates.add(new ABSqrt2(nextSmallest.a + 1, nextSmallest.b));
            candidates.add(new ABSqrt2(nextSmallest.a, nextSmallest.b + 1));
            candidates.remove(nextSmallest);
        }
        return result;
    }
}
