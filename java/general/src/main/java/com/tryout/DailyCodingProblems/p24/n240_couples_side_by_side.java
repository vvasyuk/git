package com.tryout.DailyCodingProblems.p24;

// There are N couples sitting in a row of length 2 * N. They are currently ordered randomly, but would like to rearrange themselves so that each couple's partners can sit side by side.
// What is the minimum number of swaps necessary for this to happen?

import java.util.Arrays;

public class n240_couples_side_by_side {
    public static void main(String[] args) {
        // Suppose the couples are labeled (0, 1), (2, 3), (4, 5), ..., and they are sitting on consecutive couches in the following arrangement:
        // [(0, 4), (1, 3), (2, 5), ...]

        // Then for the first pair, there are two ways to make a swap such that a couple will end up together on the first couch.
        // That is, we can either swap person 4 with person 1, or swap person 0 with person 5.
        Couple[] couples = {new Couple(0,4), new Couple(1,3), new Couple(2,5)};
        
        swap(couples);

        Arrays.stream(couples).forEach(x->{
            System.out.println(x.a + "-" + x.b);
        });
    }

    private static void swap(Couple[] couples) {
        int[] cache = new int[couples.length*2];
        for (int i = 0; i < couples.length; i++) {
            cache[couples[i].a] = i;
            cache[couples[i].b] = i;
        }

        for (int i = 0; i < couples.length; i++) {
            if(couples[i].a != couples[i].b-1 || couples[i].a != couples[i].b+1){
                if(couples[i].a%2==0){
                    if(couples[cache[couples[i].a+1]].a==couples[i].a+1){
                        couples[cache[couples[i].a+1]].a=couples[i].b;
                    }

                }
                couples[i].b=couples[i].a+1;
            }
        }
    }

    static class Couple{
        int a,b;

        public Couple(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}

