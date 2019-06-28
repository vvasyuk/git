package com.tryout.generalPuzzles.divideAndConquer;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
