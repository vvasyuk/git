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

    val M = p.size
    val N = s.size
    val dfa = Array.ofDim[Int](256, M)        // 256 for extended ASCII

    dfa(p.charAt(0))(0) = 1                             // if we meet A - we move to 1
    var state = 0
    for(j <- 1 until M){                                // for each letter in pattern
      for(r <- 0 until 256){ dfa(r)(j) = dfa(r)(state)} // calculate move for each from 256 letters for a pattern letter taking value from prev state
      dfa(p.charAt(j))(j) = j + 1                       // if you meet pattern char - move to next step
      state = dfa(p.charAt(j))(state)                   //
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