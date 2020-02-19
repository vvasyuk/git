package com.tryout.DailyCodingProblems.p26;

public class n268_if_int_is_power_of_4 {
    public static void main(String[] args) {
        int input = 4096;
        int x = 4;  //100
        //14        //10000
        //64        //1000000
        //System.out.println(Integer.toBinaryString(4));
        //System.out.println(Math.pow(4, 4) + " : " + Integer.toBinaryString((int) Math.pow(4, 4)));

        //System.out.println(isPowerOf4(input));
        System.out.println(isPowerOf4Better(input));
    }

    private static boolean isPowerOf4(int input) {
        boolean res = false;
        while (4<=input){
            if (input==4) res = true;
            System.out.println(input);
            input>>=2;
        }
        return res;
    }

    private static boolean isPowerOf4Better(int input) {
        //System.out.println((24 & (24 - 1))==16);
        return (input & (input - 1))==0 && input%3==1;  // drops lowest bit
    }
}
