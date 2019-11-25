package com.tryout.DailyCodingProblems

import scala.collection.mutable.ArrayBuffer

object palindrome_182 {

  def execute(s:String): Unit ={
    val sz = s.size
    // init 2d array
    val a = ArrayBuffer.fill(sz,sz)(false)
    // single char palindromes
    for(n <- Range(0,sz)){a(n)(n) = true}
    // 2 char palindromes
    for(n <- Range(0,sz-1)){a(n)(n+1) = s(n)==s(n+1)}

    //a.foreach(x=>println(x))
  }

  def main(args: Array[String]): Unit = {
    println("palindrome_182 started")
    execute("racecaar")
  }
}


//i, k = 0, 3
//while k <= len(s):
//while i < (len(s) - k + 1):
//j = i + k - 1
//A[i][j] = A[i + 1][j - 1] and s[i] == s[j]
//i += 1
//k += 1
//i = 0
//P = [None for _ in range(len(s) + 1)]
//
//for _ in range(len(s)):
//print(A[_])
//
//P[0] = []
//for i in range(len(P)):
//for j in range(i):
//matrix_i = i - 1
//print("j:"+ str(j)+ " matrix_i:" + str(matrix_i) + " i:"+ str(i) )
//if A[j][matrix_i]:
//if P[i] is None or (len(P[j]) + 1 < len(P[i])):
//P[i] = P[j] + [s[j:i]]
//print(P)
//print(P[-1])