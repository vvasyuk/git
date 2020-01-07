package com.tryout.DailyCodingProblems.P22;

// There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting with the kth person,
// and removing every successive kth person going clockwise until there is no one left.

//Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.

//For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.

public class n225_last_prisoner {
    public static void main(String[] args) {
        System.out.println(josephus(5));
    }

    //If n is odd : For example n = 7. In first round, first 2 is killed, then 4, then 6. In second round, we have 3, 5, 7 in positions 1st, 2nd and 3rd respectively.
    //If n is odd and a person is in position x in current round, then the person was in position 2x + 1 in previous round.

    //Let f(n) be position of survivor for input n, the value of f(n) can be recursively written as below.

    //If n is even: f(n) = 2f(n/2) - 1
    //Else  f(n) = 2f((n-1)/2) + 1

    static int josephus(int n) {
        int p = 1;
        while (p <= n){
            p = p * 2;
        }

        return (2 * n) - p + 1;
    }
}
