package com.tryout.DailyCodingProblems.p1

// cons(a, b) constructs a pair, and car(pair) and cdr(pair) returns the first and last element of that pair. For example, car(cons(3, 4)) returns 3, and cdr(cons(3, 4)) returns 4.
//
//Given this implementation of cons:
//
//def cons(a, b):
//    def pair(f):
//        return f(a, b)
//    return pair
//Implement car and cdr.
object p5_pair_first_last {



  def main(args: Array[String]): Unit = {
    val t = (1,2)
    println(car(t))
    println(cdr(t))
    println(carCdr(t,(x:(Int,Int))=>x._1))
  }

  def car(p:(Int,Int)) = p._1
  def cdr(p:(Int,Int)) = p._2
  def carCdr(t: (Int, Int), f: ((Int, Int)) => Int): Int = f(t)
}
