package com.tryout.DailyCodingProblems.p26;

import java.util.ArrayList;
import java.util.Stack;

// The sequence [0, 1, ..., N] has been jumbled, and the only clue you have for its order is an array representing whether each number is
// larger or smaller than the last. Given this information, reconstruct an array that is consistent with it.
// For example, given [None, +, +, -, +], you could return [1, 2, 3, 0, 4].
public class n260_jumbled_sequence {
    public static void main(String[] args) {
        // Notice that if there are no negative signs in our input, we can return the original sequence [0, 1, ..., N].
        // Furthermore, if we have just one run of consecutive negatives, we can reverse the corresponding entries in the original
        // sequence to produce a decreasing run of numbers. For example, given [None, +, -, -, -] we reverse the last
        // three entries of [0, 1, 2, 3, 4] to get [0, 1, 4, 3, 2].
        String[] jumbled = new String[]{null, "+", "-", "-", "-"};
        int[] original = new int[]{0, 1, 2, 3, 4};

        reconstruct(jumbled, original);
    }

    private static void reconstruct(String[] jumbled, int[] original) {
        ArrayList<Integer> res = new ArrayList();
        int n = jumbled.length-1;
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < n ; i++) {
            if (jumbled[i+1].equals("-")){
                stack.push(original[i]);
            }else{
                res.add(original[i]);
                while(!stack.isEmpty()){
                    res.add(stack.pop());
                }
            }
        }
        stack.push(original[jumbled.length-1]);

        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
        res.forEach(System.out::println);
    }
}
