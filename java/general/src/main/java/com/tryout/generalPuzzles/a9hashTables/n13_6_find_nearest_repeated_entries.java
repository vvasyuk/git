package com.tryout.generalPuzzles.a9hashTables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Write a program which takes as input an array and finds the distance between a
//closest pair of equal entries. For example, if s = ("All", "work", "and", "no", "play",
//"makes", "for", "no", "work", "no", "fun", "and", "no", "results"), then the second
//and third occurrences of "no" is the closest pair.
public class n13_6_find_nearest_repeated_entries {
    //when processing an entry, all we care about is the closest previous equal entry. Specifically,
    //as we scan through the array, we record for each value seen so far, we store in a hash
    //table the latest index at which it appears. When processing the element, we use the
    //hash table to see the latest index less than the current index holding the same value.
    //For the given example, when processing the element at index 9, which is "no", the
    //hash table tells us the most recent previous occurrence of "no" is at index 7, so we
    //update the distance of the closest pair of equal entries seen so far to 2.

    public static int findNearestRepetition(List<String> paragraph){
        Map<String , Integer> wordToLatestIndex = new HashMap<>();
        int nearestRepeatedDistance = Integer.MAX_VALUE ;
        for (int i = 0; i < paragraph.size(); ++i) {
            if (wordToLatestIndex.containsKey(paragraph.get(i))){
                nearestRepeatedDistance =
                        Math.min(nearestRepeatedDistance , i - wordToLatestIndex .get(paragraph.get(i)));
            }
            wordToLatestIndex.put(paragraph.get(i), i);
        }
        return nearestRepeatedDistance ;
    }
}
