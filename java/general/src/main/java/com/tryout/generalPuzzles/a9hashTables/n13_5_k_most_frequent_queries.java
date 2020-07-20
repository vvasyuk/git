package com.tryout.generalPuzzles.a9hashTables;

//You are given a log file containing search queries. Each query is a string, and queries
//are separated by newlines. Diverse applications, such as autocompletion and trend
//analysis, require computing the most frequent queries. In the abstract, you are to
//solve the following problem.
public class n13_5_k_most_frequent_queries {
    // We do this by
    //maintaining a min-heap of the k most frequent strings. We add the first k strings
    //to the hash table. We compare the frequency of each subsequent string with the
    //frequency of the string at the root of the min-heap. If the new string's frequency is
    //greater than the root's frequency, we delete the root and add the new string to the
    //min-heap. The k stringsin the min-heap at the end of the iteration are the result. In the
    //worst-case, each iterative step entails a heap delete and insert, so the time complexity
    //is0(n + m log k). The space complexity is dominated by the hash table, i.e., 0(m).
    //We can improve the time complexity to almost certain 0(n + m) = 0(n) by using
    //the algorithm in Solution 12.8 on Page 200 to compute the k largest elements in the
    //array of unique strings, again comparing strings on their frequencies. The space
    //complexity is0(m).
}
