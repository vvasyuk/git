package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashMap;
import java.util.Map;

// Write a program which takes text for an anonymous letter and text for a magazine
//and determines if it is possible to write the anonymous letter using the magazine.
//The anonymous letter can be written using the magazine if for each character in the
//anonymous letter, the number of times it appears in the anonymous letter is no more
//than the number of times it appears in the magazine.
public class n13_2_is_anon_letter_constructible {
    // A better approach is to make a single pass over the letter, storing the character
    //counts for the letter in a single hash table—keys are characters, and values are the
    //number of times that character appears. Next, we make a pass over the magazine.
    //When processing a character c, if c appears in the hash table, we reduce its count by 1;
    //we remove it from the hash when its count goes to zero. If the hash becomes empty,
    //we return true. If we reach the end of the letter and the hash is nonempty, we return
    //false—each of the characters remaining in the hash occurs more times in the letter
    //than the magazine

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        Map<Character, Integer> charFrequencyForLetter = new HashMap<>();
        // Compute the frequencies for all chars in letterText.
        for (int i = 0; i < letterText.length(); i++) {
            char c = letterText.charAt(i);
            if (!charFrequencyForLetter.containsKey(c)) {
                charFrequencyForLetter.put(c, 1);
            } else {
                charFrequencyForLetter.put(c, charFrequencyForLetter.get(c) + 1);
            }
        }
        // Check if the characters in magazineText can cover characters in letterText.
        for (char c : magazineText.toCharArray()) {
            if (charFrequencyForLetter.containsKey(c)) {
                charFrequencyForLetter.put(c, charFrequencyForLetter.get(c) - 1);
                if (charFrequencyForLetter.get(c) == 0) {
                    charFrequencyForLetter.remove(c);
                    // All characters for letterText are matched.
                    if (charFrequencyForLetter.isEmpty()) {
                        break;
                    }
                }
            }
        }
        // Empty charFrequencyForLetter means every char in letterText can be
        // covered by a character in magazineText.
        return charFrequencyForLetter.isEmpty();
    }
}
