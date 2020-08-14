package com.tryout.generalPuzzles.a10sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// Design an algorithm that takes as input a set of intervals, and outputs their union
//expressed as a set of disjoint intervals.
public class n14_6_unionOfIntervals {

    //sorting the intervals on their left endpoints
    // 1. The interval most recently added to the result does not intersect the current
    //interval, nor does its right endpoint equal the left endpoint of the current
    //interval. In this case, we simply add the current interval to the end of the result
    //array as a new interval.
    // 2. The interval most recently added to the result intersects the current interval. In
    //this case, we update the most recently added interval to the union of it with the
    //current interval.
    // 3. The interval most recently added to the result has its right endpoint equal to
    //the left endpoint of the current interval, and one (or both) of these endpoints
    //are closed. In this case too, we update the most recently added interval to the
    //union of it with the current interval.
    public static class Interval implements Comparable<Interval> {
        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();

        private static class Endpoint {
            public boolean isClosed;
            public int val;
        }
        public int compareTo(Interval i){
            if(Integer.compare(left.val , i.left.val) != 0) {
                return left.val - i.left.val;
            }
            // Left endpoints are equal , so now see if one is closed and the
            // other open - closed intervals should appear first.
            if (left.isClosed && !i.left.isClosed) {
                return -1;
            }
            if (!left.isClosed && i.left.isClosed) {
                return 1 ;
            }
            return 0;
        }

        @Override
        public boolean equals(Object obj){
            if (obj == null || !(obj instanceof Interval)) {
                return false;
            }
            if (this == obj) {
                return true ;
            }
            Interval that =(Interval)obj ;
            return left.val == that.left.val && left.isClosed == that.left.isClosed ;
        }

        @Override
        public int hashCode() { return Objects.hash(left.val , left.isClosed); }
    }

    public static List<Interval> unionOfIntervals(List<Interval> intervals) {
        // Empty input.
        if (intervals.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        // Sort intervals according to left endpoints of intervals.
        Collections.sort(intervals);
        Interval curr = intervals.get(0);
        List<Interval> result = new ArrayList<>();
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).left.val < curr.right.val
                    || (intervals.get(i).left.val == curr.right.val
                    && (intervals.get(i).left.isClosed || curr.right.isClosed))){
                if (intervals.get(i).right.val > curr.right.val
                        || (intervals.get(i).right.val == curr.right.val
                        && intervals.get(i).right.isClosed)) {
                    curr.right = intervals.get(i).right;
                }
            } else{
                result.add(curr);
                curr = intervals.get(i);
            }
        }
        result.add(curr);
        return result;
    }


}
