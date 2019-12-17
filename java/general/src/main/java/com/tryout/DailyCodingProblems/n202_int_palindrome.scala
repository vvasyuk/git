package com.tryout.DailyCodingProblems

object n202_int_palindrome {

  def execute(i: Int):Unit={
    var tmp =i
    var reversed =0

    while (tmp>0){
      val n = tmp%10
      reversed=reversed*10+n
      tmp = tmp/10
    }

    println("is palindrome: " + (i==reversed))
  }

  def main(args: Array[String]):Unit={
    execute(121)
  }
}
