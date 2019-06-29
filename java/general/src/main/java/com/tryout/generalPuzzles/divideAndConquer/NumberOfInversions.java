package com.tryout.generalPuzzles.divideAndConquer;

public class NumberOfInversions {
    //Compute the number of inversions in a sequence
    //of integers.
    //Input: A sequence of integers
    //a1; : : : ;an.
    //Output: The number of inversions
    //in the sequence, i.e., the number of
    //indices i < j such that ai > aj .

    public static int execute(int[] a) {
        int[] temp = new int[a.length];
        return recHelper(a,temp, 0, a.length-1);
    }

    private static int recHelper(int[] a, int[] temp, int lo, int hi) {
        int mid, invCount = 0;
        if(hi>lo){
            mid=(lo+hi)/2;
            invCount=recHelper(a,temp,lo,mid);
            invCount+=recHelper(a,temp,mid+1,hi);
            invCount+=merge(a,temp,lo,mid+1,hi);

        }
        return invCount;
    }

    private static int merge(int[] a, int[] temp, int lo, int mid, int hi) {
        int l = lo;int r = mid;int t = lo;int invCount = 0;

        while ((l <= mid - 1) && (r <= hi)) {
            if (a[l] <= a[r]) {
                temp[t++] = a[l++];
            }
            else {
                temp[t++] = a[r++];

                //if a[l] is greater than a[r], then there are (mid–l) inversions.
                //because left and right subarrays are sorted, so all the remaining elements
                // in left-subarray (a[i+1], a[i+2] … a[mid]) will be greater than a[j]
                invCount = invCount + (mid - l);
            }
        }

        //write remaining left part
        while (l <= mid-1)
            temp[t++] = a[l++];

        //write remaining right part
        while (r <= hi)
            temp[t++] = a[r++];

        //write sorted to original array
        for (l = lo; l <= hi; l++)
            a[l] = temp[l];

        return invCount;
    }
}
