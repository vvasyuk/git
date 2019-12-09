package com.tryout.DailyCodingProblems

import scala.collection.mutable.ArrayBuffer

object n181_palindrome {

  def execute(s:String): Unit ={
    val sz = s.size
    // init 2d array
    val a = ArrayBuffer.fill(sz,sz)(false)
    // single char palindromes
    for(n <- Range(0,sz)){a(n)(n) = true}
    // 2 char palindromes
    for(n <- Range(0,sz-1)){a(n)(n+1) = s(n)==s(n+1)}

    var i=0
    var k=3
    while(k<=sz){
      while (i<(sz-k+1)){
        val j = i + k - 1
        a(i)(j) = a(i + 1)(j - 1) && s(i) == s(j)
        i += 1
      }
      k += 1
      i = 0
    }


    val res = ArrayBuffer.fill(sz+1,sz+1)("")
    res.foreach(println(_))
    println(res(1)(1).isEmpty)
    for(
      i <- Range(0,res.size);
      j <- Range(0,i)
    ) {
      println("i:" + i + " j:" + j)
      val matrix_i = i - 1
      if (a(j)(matrix_i)){
        if (res(i)(0).isEmpty || res(j).size+1 < res(j).size){
//          res(i) = res(j) + [s[j:i]]
        }
      }
    }
//    a.foreach(println(_))
  }

  def main(args: Array[String]): Unit = {
    println("palindrome_182 started")
    execute("racecar")
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