package com.tryout.generalPuzzles.a10sort;

import java.util.ArrayList;
import java.util.List;

// Write a program which takes as input an array of disjoint closed intervals with integer
//endpoints, sorted by increasing order of left endpoint, and an interval to be added,
//and returns the union of the intervals in the array and the added interval
public class n14_5_mergeIntervals {

    private static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    public static List<Interval> addInterval(List<Interval> disjointlntervals, Interval newInterval) {
        int i = 0;
        List<Interval> result = new ArrayList<>();
        // Processes intervals in disjointlntervals which come before newlnterval.
        while (i < disjointlntervals.size() && newInterval.left > disjointlntervals.get(i).right) {
            result.add(disjointlntervals.get(i++));
        }
        // Processes intervals in disjointlntervals which overlap with newlnterval .
        while (i < disjointlntervals.size() && newInterval.right >= disjointlntervals.get(i).left) {
            // If [a, b ] and [c , d] overlap, their union is [min(a , c),max(b, d) ] .

            newInterval = new Interval(
                    Math.min(newInterval.left, disjointlntervals.get(i).left),
                    Math.max(newInterval.right, disjointlntervals.get(i).right));
            ++i;
        }
        result.add(newInterval);
        // Processes intervals in disjointlntervals which come after newlnterval .
        result.addAll(disjointlntervals.subList(i, disjointlntervals.size()));
        return result;
    }
}
