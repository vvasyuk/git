package com.tryout.DailyCodingProblems;

public class StaircasePaths {
    public static int execute(){
        int steps = 4;
        int[] stairs = new int[steps+1];
        stairs[stairs.length-1]=1;

        for(int i = stairs.length-2; i>=0; i--){
            if(stairs.length-i>=3){
                stairs[i]=stairs[i+1] + stairs[i+2];
            }else{
                stairs[i]=stairs[i+1];
            }
        }
        return stairs[0];
    }
}
