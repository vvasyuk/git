package com.tryout.DailyCodingProblems;


public class MissingNumberInArray {
//		int ar[] = { 1, 2, 3, 5 };

//    Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
//    For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

    public static int execute(int[] arr){
        //method1 if elements are sequential
//        int sum = (arr.length+1)*(arr.length+2)/2;
//        for(int i : arr){ sum -= i;}
//        return sum;
        //method2 if elements are sequential
//        int x1 = arr[0];
//        int x2 = 1;
//
//        /* For xor of all the elements in array */
//        for (int i = 1; i < arr.length; i++){
//            System.out.println("arr[i]: " + arr[i] + " xor x1 ^ arr[i]:" + (x1 ^ arr[i]));
//            x1 = x1 ^ arr[i];
//        }
//
//        /* For xor of all the elements from 1 to n+1 */
//        for (int i = 2; i <= arr.length+1; i++){
//            System.out.println("i: " + i + " xor x2 ^ i:" + (x2 ^ i));
//            x2 = x2 ^ i;
//        }
//        return (x1 ^ x2);

        //method 3
        // First separate positive and negative numbers
        int size = arr.length;
        int shift = segregate (arr, size);
        int arr2[] = new int[size-shift];
        int j=0;
        for(int i=shift;i<size;i++)
        {
            arr2[j] = arr[i];
            j++;
        }
        // Shift the array and call findMissingPositive for positive part
        return findMissingPositive(arr2, j);
    }

    /* Utility function that puts all non-positive (0 and negative) numbers on left side of arr[] and return count of such numbers */
    static int segregate (int arr[], int size){
        int j = 0;
        for(int i = 0; i < size; i++){
            if (arr[i] <= 0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive integers
                j++;
            }
        }
        return j;
    }

    /* Find the smallest positive missing number in an array that contains  all positive integers */
    static int findMissingPositive(int arr[], int size){
        int i;
        // Mark arr[i] as visited by making arr[arr[i] - 1] negative. Note that 1 is subtracted because index start from 0 and positive numbers start from 1
        for(i = 0; i < size; i++) {
            int x = Math.abs(arr[i]);
            if(x - 1 < size && arr[x - 1] > 0)
                arr[x - 1] = -arr[x - 1];
        }

        // Return the first index value at which is positive
        for(i = 0; i < size; i++)
            if (arr[i] > 0)
                return i+1;  // 1 is added because indexes
        // start from 0
        return size+1;
    }
}
