package com.tryout.DailyCodingProblems.P22;

//Let's define a "sevenish" number to be one which is either a power of 7, or the sum of unique powers of 7.
//The first few sevenish numbers are 1, 7, 8, 49, and so on. Create an algorithm to find the nth sevenish number.

public class n221_sevenish_numbers {

    //To find unique sums of powers of 7, then, we can imagine that each bit represents
    //a power of 7 instead of 2!
    //Let's look at the first few sevenish numbers to see how this works:

    //001 (1 * 7^0= 1)
    //010 (1 * 7^1= 7)
    //011 (1 * 7^1+ 1 * 7^0= 8)
    //100 (1 * 7^2= 49)
    //101 (1 * 7^2+ 1 * 7^0= 50)

    //So the nth sevenish number will be the nth binary number, translated into powers of seven
    //instead of two. This points the way to our solution: we will go through each bit of n,
    //from least to most significant, and check if it is set. If so, we add 7bit_place to our total.
    //Once we bitshift through the entire number, we can return the total.

    public static void main(String[] args) {
        int n =6;
        int res = 0;
        int bitPlace = 0;
        //System.out.println(2>>1&1);

        while(n>0){
            if((n&1)==1){
                res+=Math.pow(7,bitPlace);
            }
            n=n>>1;
            bitPlace++;
        }
        System.out.println("res: " + res);

    }
}
