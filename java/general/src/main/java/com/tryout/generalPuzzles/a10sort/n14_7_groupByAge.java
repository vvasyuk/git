package com.tryout.generalPuzzles.a10sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Suppose you need to reorder the elements of a very large array so that equal elements
//appear together. For example, if the array is {b,a, c, b,d,a,b,d ) then (a,a, b,b,b,c,d,d)
//is an acceptable reordering, as is (d, d,c,a,a,b,b,b).
public class n14_7_groupByAge {
    // consider the array ((Greg,14), (John,12), (Andy,11), (Jim,13), (Phil,12), (Bob,13), (Chip,13), (Tim,14))
    // iterate array and record the number of students of each age in a hash
    // keys are ages, and values are the corresponding counts (14, 2), (12, 2), (11,1), (13,3)

    //If we had a new array to write to, we can write the two students of age 14 starting
    //at index 0, the two students of age 12 starting at index 0 + 2 = 2, the one student of
    //age 11 at index 2+2 = 4, and the three students of age 13 starting at index 4 + 1 = 5.
    //We would iterate through the original array, and write each entry into the new array
    //according to these offsets. For example, after the first four iterations, the new array
    //would be ((Greg,14),_(John,12),_(Andy,11),(Jim,13),_,_).

    private static class Person {
        public Integer age;
        public String name;

        public Person(Integer k, String n) {
            age = k;
            name = n;
        }
    }

    public static void groupByAge(List<Person> people) {
        Map<Integer , Integer> ageToCount = new HashMap<>();
        for (Person p : people) {
            if (ageToCount.containsKey(p.age)) {
                ageToCount.put(p.age , ageToCount.get(p.age) + 1);
            } else {
                ageToCount.put(p.age , 1);
            }
        }
        Map<Integer, Integer> ageToOffset = new HashMap<>();
        int offset = 0;
        for (Map.Entry<Integer , Integer> kc : ageToCount.entrySet()){
            ageToOffset.put(kc.getKey () , offset);
            offset += kc.getValue();
        }
        while (!ageToOffset.isEmpty()){
            Map.Entry<Integer, Integer> from
                    = ageToOffset.entrySet().iterator().next();
            Integer toAge = people.get(from.getValue()).age ;
            Integer toValue = ageToOffset.get(toAge);
            Collections.swap(people , from.getValue(), toValue);
            // Use ageToCount to see when we are finished with a particular age.
            Integer count = ageToCount.get(toAge)- 1;
            ageToCount.put(toAge , count);
            if (count > 0) {
                ageToOffset.put(toAge , toValue + 1);
            } else {
                ageToOffset.remove(toAge);
            }
        }
    }
}
