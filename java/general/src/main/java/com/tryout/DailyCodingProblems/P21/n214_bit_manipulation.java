package com.tryout.DailyCodingProblems.P21;

public class n214_bit_manipulation {

    public static void main(String[] args) {
                          //1   0  0  1  1 1 0 0
        //execute(156);   //128 64 32 16 8 4 2 1
        executeWithBitManipulation(40);
    }

    private static void executeWithBitManipulation(int i) {
        //if we perform the operation x & x << 1, the longest consecutive run of 1s must decrease by one
        //This is because all but one of the set bits in the original number will correspond to set bits in the shifted number
        //Using the example in the problem, we can see that the maximum length changes from 3 to 2
        //  10011100
        //& 00111000
        //-----------
        //  00011000
        //With this in mind, we can continue to AND our input with a shifted version of itself until we reach 0.
        //The number of times we perform this operation will be our answer.

        int max = 0;
        while (i>0){
            max++;
            i= i &(i << 1);
        }
        System.out.println("max: " + max);
    }

    private static void execute(int i) {
        System.out.println(Integer.toBinaryString(i));
        int tmpCnt = 0;
        int max = 0;
        for(char c: Integer.toBinaryString(i).toCharArray()){
            System.out.println(c);
            if(c=='1'){
                tmpCnt++;
                max = Math.max(max,tmpCnt);
            }else{
                tmpCnt=0;
            }
        }
        System.out.println("max: " + max);

    }
}
