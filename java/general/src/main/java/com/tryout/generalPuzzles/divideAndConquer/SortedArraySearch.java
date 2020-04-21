package com.tryout.generalPuzzles.divideAndConquer;

public class SortedArraySearch {

    //binary search

    public static boolean execute(int[] a, int value) {
        Boolean res=false;

        int start=0;
        int end=a.length-1;

        if(value>a[end] || value<a[start]){
            return res;
        }

        while(start<end){
            int mid=(end+start)/2;

            if(mid==value){
                res=true;
                break;
            }else if (mid>value){
                end=mid;
            }else if(mid<value){
                start=mid;
            }
        }

        return res;
    }
}
