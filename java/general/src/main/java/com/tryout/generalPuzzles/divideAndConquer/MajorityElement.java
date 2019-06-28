package com.tryout.generalPuzzles.divideAndConquer;

import java.util.Arrays;

public class MajorityElement {
    //Check whether a given sequence of numbers contains
    //an element that appears more than half of
    //the times.

    //Input: A sequence of n integers.
    //Output: 1, if there is an element
    //that is repeated more than n=2
    //times, and 0 otherwise.

    //Note if we know that there is a majority and we need to tell which element is the majority:
    //we can sort and then get n(n.length/2 or (n.length/2)+1 for odd)


    public static int execute(int[] a) {
        return recHelper(a, 0, a.length-1);
    }

    private static int recHelper(int[] a, int lo, int hi) {
        if(lo==hi){ return a[lo]; }

        int mid=lo+(hi-lo)/2;
        int left=recHelper(a,lo,mid);
        int right=recHelper(a,mid+1,hi);

        if(left==right){ return left; }

        int leftCount=0; int rightCount=0;
        for(int i=lo; i<=hi;i++){
            if(a[i]==left){
                leftCount++;
            }else if (a[i]==right){
                rightCount++;
            }
        }
        return leftCount>rightCount? left : right;

    }
}
