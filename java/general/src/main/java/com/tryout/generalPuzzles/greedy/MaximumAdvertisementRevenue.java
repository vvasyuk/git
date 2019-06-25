package com.tryout.generalPuzzles.greedy;

import java.util.Arrays;

public class MaximumAdvertisementRevenue {
    //Find the maximum dot product of two sequences
    //of numbers.
    //Output the maximum value of (price1 c1+  +pricen
    //cn),


    public static int execute(int[] a, int[] b) {
        int res=0;

        Arrays.sort(a);
        Arrays.sort(b);

        for(int i=a.length-1; i>=0; i--){
            res+=a[i]*b[i];
        }

        return res;
    }
}
