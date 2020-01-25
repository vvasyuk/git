package com.tryout.DailyCodingProblems.p24;

// You are given an array of length 24, where each element represents the number of new subscribers during the corresponding hour. Implement a data structure that efficiently supports the following:
//update(hour: int, value: int): Increment the element at index hour by value.
//query(start: int, end: int): Retrieve the number of subscribers that have signed up between start and end (inclusive).

import java.util.Arrays;

public class n242_array_of_24_hours {

    public static void main(String[] args) {
        Hours h = new Hours(new int[] {4, 8, 1, 9, 3, 5, 5, 3});

        Arrays.stream(h.hours).forEach(x->{ System.out.println(x); });
        System.out.println("res: " + h.query(4));
    }

    static class Hours{
        int[] hours;
        Bit bit;

        public Hours(int[] hours) {
            this.hours = hours;
            bit = new Bit(hours);
        }

        public void update(int h, int value){
            bit.update(h, value - hours[h]);
            hours[h] = hours[h] + value;
        }

        public int query(int end){
            return bit.query(end);
        }
    }

    // c[13] will be c[1101] = tree[1101] + tree[1100] + tree[1000]
    // using binary indexed tree
    //remove last bit n&(n-1)
    //get least bit value n&-n

    //-----   16
    //|   -   15
    //|  --   14
    //|  |-   13
    //| ---   12
    //| | -   11
    //| |--   10
    //| ||-   9
    //|----   8
    //||  -   7
    //|| --   6
    //|| |-   5
    //||---   4
    //||| -   3
    //|||--   2
    //||||-   1
    static class Bit{
        int[] tree;

        public Bit(int[] nums) {
            this.tree = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                update(i+1, nums[i]);
            }
        }

        public void update(int idx, int value){
            while (idx<tree.length){
                tree[idx]=tree[idx]+value;
                idx +=idx&-idx; // add least bit value
            }
        }

        public int query(int end){
            int res = 0;
            while (end>1){
                res+=tree[end];
                end -=end&-end; // remove least bit value
            }
            return res;
        }
    }


}
