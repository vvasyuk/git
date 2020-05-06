package com.tryout.generalPuzzles.a3strings;

// For example, "Alice likes Bob" transforms to "Bob likes Alice"
public class n7_6_reverse_all_words {
    // For example, "ram is costly" reversed yields "yltsoc si mar".
    // We obtain the final result by reversing each word to obtain "costly is ram".
    public static void reverseWords(char[] input) {
        // Reverses the whole string first.
        reverse(input, 0, input.length) ;
        int start = 0, end;
        while ((end = find(input, ' ' , start)) != -1) {
            // Reverses each word in the string.
            reverse(input, start, end);
            start = end + 1;
        }
        // Reverses the last word.
        reverse(input , start, input.length);
    }
    public static void reverse(char[] array, int start, int stoplndex) {
        if (start >= stoplndex) {
            return ;
        }
        int last = stoplndex - 1;
        for (int i = start; i <= start + (last - start) / 2; i++) {
            char tmp = array[i];
            array[i] = array[last - i + start];
            array[last - i + start] = tmp;
        }
    }
    public static int find(char[] array, char c, int start) {
        for (int i = start; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
