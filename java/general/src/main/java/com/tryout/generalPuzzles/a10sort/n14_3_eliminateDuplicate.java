package com.tryout.generalPuzzles.a10sort;

import java.util.Collections;
import java.util.List;

// Design an efficient algorithm for removing all first-name duplicatesfrom an array. For
//example, if the input is ((Ian, Botham), (David,Gower), (Ian, Bell), (Ian, Chappell)),
//one result could be ((Ian, Bell), (David,Gower)); ((David,Gower), (Ian, Botham))
//would also be acceptable.
public class n14_3_eliminateDuplicate {
    //We can avoid the additional space complexity if we can reuse the input array for
    //storing the final result. First we sort the array, which brings equal elements together.
    //Sorting can be done in0(n log n) time. The subsequent elimination of duplicates takes
    //0(n) time. Note that sorting an array requires that its elements are comparable.

    public static class Name implements Comparable <Name> {
        String firstName;
        String lastName;

        public int compareTo(Name name) {
            int cmpFirst = firstName.compareTo(name.firstName);
            if (cmpFirst != 0) {
                return cmpFirst;
            }
            return lastName.compareTo(name.lastName);
        }
    }
    public static void eliminateDuplicate(List<Name> A) {
        Collections.sort(A); // Makes identical elements become neighbors.
        int result = 0;
        for (int first = 1; first < A.size(); first++) {
            if (!A.get(first).firstName.equals(A.get(result).firstName)) {
                A.set(++result, A.get(first));
            }
        }
        // Shrinks array size.
        A.subList(++result, A.size()).clear();
    }
}
