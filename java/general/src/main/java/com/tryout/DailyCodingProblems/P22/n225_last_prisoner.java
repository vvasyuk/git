package com.tryout.DailyCodingProblems.P22;

// There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting with the kth person,
// and removing every successive kth person going clockwise until there is no one left.

//Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.

//For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.

public class n225_last_prisoner {
    public static void main(String[] args) {
        System.out.println("jose[hus 5: " + josephus(5));
        System.out.println("jose[hus 7: " + josephus(7));
    }
    // coolest solution
    // take input number, convert ot binary and move last bit to first position

    // josephus of 2,4,8,16... is 1
    // table:
    // 1    2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
    // 1    1   3   1   3   5   7   1   3   5   7   9   11  13  15  1

    private static Integer josephus(int n) {
//        input: 1,2,3,4,5
//        1) 3,5  (position from prev round 2t+1: 2*1+1=3 and 2*2+1=5)
//        2) 3    (position from prev round 2t-1: 2*1-1=1 because prev round had even n)
//
//        input: 1,2,3,4,5,6,7
//        1) 3,5,7    (position from prev round 2t+1: 2*1+1=3 and 2*2+1=5 and 2*3+1=7)
//        2) 7        (position from prev round 2t+1: 2*1+1=3)
        if(n==1){
            return 1;
        }else{
            if (n%2==0){
                //even case
                return 2*(josephus(n/2))-1;
            }else{
                //odd case
                return 2*(josephus(n/2))+1;
            }
        }

    }
    //If n is odd : For example n = 7. In first round, first 2 is killed, then 4, then 6. In second round, we have 3, 5, 7 in positions 1st, 2nd and 3rd respectively.
    //If n is odd and a person is in position x in current round, then the person was in position 2x + 1 in previous round.
    //Let f(n) be position of survivor for input n, the value of f(n) can be recursively written as below.
    //If n is even: f(n) = 2f(n/2) - 1
    //Else  f(n) = 2f((n-1)/2) + 1
//    static int josephus(int n) {
//        int p = 1;
//        while (p <= n){
//            p = p * 2;
//        }
//
//        return (2 * n) - p + 1;
//    }

}
