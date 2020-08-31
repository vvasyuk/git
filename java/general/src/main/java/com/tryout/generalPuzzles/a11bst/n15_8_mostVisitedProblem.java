package com.tryout.generalPuzzles.a11bst;

// Write a function to read the next line from a log file, and a function to find the k
//most visited pages, where k is an input to the function. Optimize performance for the
//situation where calls to the two functions are interleaved. You can assume the set of
//distinct pages is small enough to fit in RAM.

//suppose the log file ids appear in the following order:
//g,a,t,t,a,a,a,g,t,c,t,a,t, i.e., there are four pages with ids a,c,g,t. After the first 10
//lines have been read, the most common page is a with a count of 4, and the next most
//common page is t with a count of 3.
public class n15_8_mostVisitedProblem {

    // Height-balanced BSTs are a good choice when performing many incremental up¬
    //dates while preserving sortedness. Adding and removing an entry in a heightbalanced BST on N nodes takes time <9(logN). Therefore it makes sense to store the
    //page-to-visit-counts in a balanced BST. The BST nodes store (page,visit-count) pairs.
    //These pairs are ordered by visit-count, with ties broken on page.

    //Height-balanced BSTs are a good choice when performing many incremental up¬
    //dates while preserving sortedness. Adding and removing an entry in a heightbalanced BST on N nodes takes time <9(logN). Therefore it makes sense to store the
    //page-to-visit-counts in a balanced BST. The BST nodes store (page,visit-count) pairs.
    //These pairs are ordered by visit-count, with ties broken on page.

    //For the given example, after the first four entries have been read, the BST contains
    //thefollowing (visit-count, page) pairs (1,a),(1, g),(2, f) in thisorder,and the hash table
    //mapsa,g,t to (1,a),(1, g),(2, f), respectively. After we read the fifth entry,a,we use the
    //hash table to find thecorresponding entry (1,a) and update it to (2,a),yielding the tree
    //(1, g),(2,a),(2, t). After the first ten entries, the tree consists of (1,c),(2, g),(3, f), (4,a).
    //The most visited page at this point is a, with a visit count of 4.
}
