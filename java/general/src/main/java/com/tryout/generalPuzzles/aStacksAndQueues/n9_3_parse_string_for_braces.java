package com.tryout.generalPuzzles.aStacksAndQueues;

import java.util.Deque;
import java.util.LinkedList;

// A string over the characters "{,},(,),[,]" is said to be well-formed if the different types
// of brackets match in the correct order.
// For example, "([]){()}" is well-formed, as is "[()[]|()()|]". However, "{)" and
// "[()[]{()()" are n°t well-formed,
// Write a program that tests if a string made up of the characters '(', ')', '[', and"}'
// is well-formed.

public class n9_3_parse_string_for_braces {
    // Therefore, starting from the left, every
    //time we see a left parenthesis, we store it. Each time we see a right parenthesis, we
    //match it with a stored left parenthesis. Since there are not brackets or braces, we can
    //simply keep a count of the number of unmatched left parentheses.
    // If we encounter a right character and the stack is empty or the top of the stack is a
    //different type of left character, the right character is not matched, implying the string
    //is not matched. If all characters have been processed and the stack is nonempty, there
    //are unmatched left characters so the string is not matched
    public static boolean isWellFormed(String s){
        Deque<Character> leftChars = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                leftChars.addFirst(s.charAt(i));
            } else {
                if (leftChars.isEmpty()){
                    return false; // Unmatched right char.
                }
                if ((s.charAt(i) == ')' && leftChars.peekFirst() != '(')
                        || (s.charAt(i) == '}' && leftChars.peekFirst() != '{')
                        || (s.charAt(i) == ']' && leftChars.peekFirst() != '[')) {
                        return false; // Mismatched chars.
                    }
                    leftChars.removeFirst();
                }
            }
                return leftChars.isEmpty();
            }
}
