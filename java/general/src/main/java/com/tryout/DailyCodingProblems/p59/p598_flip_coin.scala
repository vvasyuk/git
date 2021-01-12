package com.tryout.DailyCodingProblems.p59

import scala.util.Random

object p598_flip_coin {
//  You have n fair coins and you flip them all at the same time. Any that come up tails you set aside.
//  The ones that come up heads you flip again. How many rounds do you expect to play before only one coin remains?
//
//  Write a function that, given n, returns the number of rounds you'd expect to play until one coin remains.

  def main(args: Array[String]): Unit = {


    //Range(0,100).foreach(x=>println(r.nextInt(2)))
    //println(Range(0,10).filter(x=> 0 == r.nextInt(2)).size)

    val res = flipCoin(100, 0, Random)
    println(res)
  }

  def flipCoin(n: Int, flipCnt: Int, r: Random):Int={
    if (n==0)
      flipCnt
    else {
      println(s"step: $flipCnt coins remaining: $n")
      flipCoin(Range(0,n).filter(x=> 0 == r.nextInt(2)).size, flipCnt+1, r)
    }
  }
}
