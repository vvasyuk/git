package com.tryout.generalPuzzles.greedy;

import java.util.Comparator;
import java.util.List;

public class CollectingSignatures {
    //Find the minimum number of points needed to
    //cover all given segments on a line.
    //Input: A sequence of n segments
    //[a1;b1]; : : : ; [an;bn] on a line.
    //Output: A set of points of minimum
    //size such that each segment
    //[ai ;bi ] contains a point, i.e., there
    //exists a point x such that ai  x 
    //bi .

    public static int execute(List<int[]> segments) {
        int res=0;

        while(segments.size()>0){
            int minRight=segments.stream().map(x->x[1]).min(Comparator.comparing(Integer::valueOf)).get();
            //int minRight=getMinRightPoint(segments);
            res++;
            segments.removeIf(i -> i[0]<=minRight && i[1]>=minRight);
        }

        return res;
    }

    private static int getMinRightPoint(List<int[]> segments){
        int min=Integer.MAX_VALUE;
        for(int[] s:segments){
            if (s[1]<min)
                min=s[1];
        }
        return min;
    }
}
