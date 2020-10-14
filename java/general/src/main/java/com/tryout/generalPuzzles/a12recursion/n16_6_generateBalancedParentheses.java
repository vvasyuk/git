package com.tryout.generalPuzzles.a12recursion;

import java.util.ArrayList;
import java.util.List;

//Strings in which parens are matched are defined by the following three rules:
//• The empty string, "", is a string in which parens are matched.
//• The addition of a leading left parens and a trailing right parens to a string in
//which parens are matched results in a string in which parens are matched. For
//example, since "(())()" is a string with matched parens, so is "((())())".
//• The concatenation of two strings in which parens are matched is itself a string
//in which parens are matched. For example, since "(())()" and "()" are strings
//with matched parens, so is "(())()()".
//For example, the set of strings containing two pairs of matched parens
//is ((()),()()), and the set of strings with three pairs of matched parens is
//«(())), (00), (0)0, 0(0), ()()()}•
//Write a program that takes as input a number and returns all the strings with that
//number of matched pairs of parens

public class n16_6_generateBalancedParentheses {
    //improve upon the time complexity by enumerating in a more
    //directed fashion. For example, some strings can never be completed to a string with
    //k pairs of matched parens, e.g., if a string begins with ). Therefore, one way to be
    //more directed is to build strings incrementally. We will ensure that as each additional
    //character is added, the resulting string has the potential to be completed to a string
    //with k pairs of matched parens.
    // Suppose we have a string whose length is less than 2k, and we know that string
    //can be completed to a string with k pairs of matched parens. How can we extend that
    //string with an additional character so that the resulting string can still be completed
    //to a string with k pairs of matched parens?
    //There are two possibilities: we add a left parens, or we add a right parens.
    //• If we add a left parens, and still want to complete the string to a string with k
    //pairs of matched parens, it must be that the number of left parens we need is
    //greater than 0.
    //• If we add a right parens, and still want to complete the string to a string with
    //k pairs of matched parens, it must be that the number of left parens we need is
    //less than the number of right parens (i.e., there are unmatched left parens in the
    //string).
    //As a concrete example, if k = 2, we would go through the following sequence
    //of strings: "(", "((", "(()", "(())", "0", "()(", "00" Of these, "(())" and "00" are
    //complete, and we would add them to the result.
    public static List<String> generateBalancedParentheses(int numPairs) {
        List<String> result = new ArrayList<>();
        directedGenerateBalancedParentheses(numPairs, numPairs, "", result);
        return result;
    }

    private static void directedGenerateBalancedParentheses(int numLeftParensNeeded, int numRightParensNeeded, String validPrefix, List<String> result) {
        if (numLeftParensNeeded == 0 && numRightParensNeeded == 0) {
            result.add(validPrefix);
            return;
        }
        if (numLeftParensNeeded > 0) { // Able to insert
            directedGenerateBalancedParentheses(numLeftParensNeeded - 1,
                    numRightParensNeeded,
                    validPrefix + "(", result);
        }
        if (numLeftParensNeeded < numRightParensNeeded) { // Able to insert
            directedGenerateBalancedParentheses(numLeftParensNeeded,
                    numRightParensNeeded - 1,
                    validPrefix + ")", result);
        }
    }
}
