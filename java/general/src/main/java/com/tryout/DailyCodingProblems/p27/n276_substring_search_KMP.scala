package com.tryout.DailyCodingProblems.p27

object n276_substring_search_KMP {
  def main(args: Array[String]): Unit = {
    val s = "AABACAABABACAA"
    val p = "ABABAC"
//                                                                                                  #########################################################
    //build dfa - deterministic finite automata                                                     #                                   A                   #
//    Steps to build DFA:                                                                           #                /<---------------------\               #
//    1.copy mismatched cases (first column all 0).                                                 #  B C      A   /     A                  \              #
//    2.set matched character to go to next state (first column DFA[needle.charAt(0)][0] = 1).      #  ---     --- /<--------\              B \             #
//    3.start state 0, update state for j in [1, M-1].                                              #  | |     | |/           \           /<--\\            #
//    j    :  0 1 2 3 4 5 6                                                                         #  v-+ A   v-+ B   +-+ A   +-+ B   +-+ A   +-+ C   +-+  #
//    state:  0 0 1 2 3 0                                                                           #  |0|---->|1|---->|2|---->|3|---->|4|---->|5|---->|6|  #
//    r       A B A B A C                                                                           #  +-+  C  +-+     +-+     +-+     +-+     +-+     +-+  #
//    A       1 1 3 1 5 1                                                                           #   \\\<--/  B C  /       /       /                     #
//    B       0 2 0 4 0 4                                                                           #    \\<---------/    C  /       /                      #
//    C       0 0 0 0 0 6                                                                           #     \\<---------------/  B C  /                       #
//    D       0 0 0 0 0 0                                                                           #      \<----------------------/                        #
//                                                                                                  #########################################################
// how state variable works:
// if you mismatch - to understand on which step you should be - you can remove first letter of already matched text and pass it through dfa - then from that place check where mismatched letter is pointing
// example: you matched in text for "A B A B A" and then you get B. Remove first letter from matched - you get "B A B A" - this will take you to step 3 and from there B will take you to 4
// what its used for: if you mismatch - you calcualte where you be by checking where you be with that char starting from state
// how to update state:
// I think if pattern had unique chars - it would not update
// for each new letter of pattern you try to understand where if would be if it started on previous state and it will be a bew state
// example:
// state starts with 0,
// we start with letter B(for(j <- 1 until M)) - we update state step number where we would be if we started pattern match with B (its 0) because dfa(B)(0)=0
// next letter is A (A B A) - we update state to dfa(A)(0) - its 1. If we would have a mismatch after third letter - to check where we should point our mismatch - we check where we be from state 1
// example of mismatch after A B A when state is 1: if we mismatch with char A - we would go to step 1 because we go to step 1 if we try A in step 1

    val M = p.size
    val N = s.size
    val dfa = Array.ofDim[Int](256, M)        // 256 for extended ASCII

    dfa(p.charAt(0))(0) = 1                             // if we meet A - we move to 1
    var state = 0
    for(j <- 1 until M){                                // for each letter in pattern; j is a number of letters matched in pattern
      for(r <- 0 until 256){ dfa(r)(j) = dfa(r)(state)} // covers mismatches, calculate move for each from 256 letters for a pattern letter taking value from prev state
      dfa(p.charAt(j))(j) = j + 1                       // if you meet pattern char - move to next step
      state = dfa(p.charAt(j))(state)                   // where you be if you mismatch? need to keep track of state where we would be at on the pattern starting at position 1
    }
    dfa foreach { row => row foreach print; println }
//    113151
//    020404
//    000006

//    Note DFA[’D’][5] = 0 but DFA[‘B’][5] = 4.

//    search
    var i = 0
    var j = 0
    while (i<N && j<M){
      j=dfa(s.charAt(i))(j)
      i+=1
    }
    if (j==M) println(i-M) else println(-1)
  }
}