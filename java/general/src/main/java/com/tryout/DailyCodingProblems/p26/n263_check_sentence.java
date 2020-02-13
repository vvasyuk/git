package com.tryout.DailyCodingProblems.p26;

// Create a basic sentence checker that takes in a stream of characters and determines whether they form valid sentences. If a sentence is valid, the program should print it out.
//
//We can consider a sentence valid if it conforms to the following rules:
//
//The sentence must start with a capital letter, followed by a lowercase letter or a space.
//All other characters must be lowercase letters, separators (,,;,:) or terminal marks (.,?,!,‽).
//There must be a single space between each word.
//The sentence must end with a terminal mark immediately following a word.

import java.util.stream.Stream;

public class n263_check_sentence {
    // As it turns out, one system that suits this problem well is a finite state machine. After processing each element in our stream, we can update the state to be one of the following:
    //0: Begin
    //1: Uppercase
    //2: Lowercase
    //3: Space
    //4: Separator

    public static void main(String[] args) {
        String s = "Create, parse a basic sentence checker that takes in a stream.";
        int state = 0;
        StringBuilder sentence = new StringBuilder();

        for (Character c : s.toCharArray()){
            sentence.append(c);

            if (Character.isUpperCase(c)) {
                state = 1;
            }    //uppercase
            else if (!Character.isUpperCase(c) && c!=',' && c!='.' && c!=' ' && (state == 1 | state == 2 | state == 3)) {
                state = 2;
            }    //lowercase
            else if (c == ' ' && (state == 1 | state == 2 | state == 4)) {
                state = 3;
            }    //space
            else if (c == ',' && state == 2) {
                state = 4;
            }    //separator
            else if (c == '.' && state == 2) {
                System.out.println(sentence.toString());
            } else {
                sentence.delete(0, sentence.length());
            }
        }
    }
}