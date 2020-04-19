package com.tryout.DailyCodingProblems.p30

// mplement a data structure which carries out the following operations without resizing the underlying array:
//
// add(value): Add a value to the set of values.
// check(value): Check whether a value is in the set.
// The check method may return occasional false positives (in other words, incorrectly identifying an element as part of the set), but should always correctly identify a true element.
object p301_set_array_withot_resize {
  // Bloom filter - hashing each item in multiple ways, so that several values in the array will be set to True for any given input
  def main(args: Array[String]): Unit = {
    val arr = Array.fill(100)(false)
    val fHashes = List(
      x=>h1(x),
      x=>h2(x)
    )

    val x = 15
    add(x, arr, fHashes)
    println(check(x, arr, fHashes))
    println("")
  }

  def check(x: Int, arr: Array[Boolean], fHashes: List[Int => Int]) = {
    (for (f<-fHashes)yield{
      arr(f(x))
    }).forall(_==true)
  }

  def add(x:Int, arr:  Array[Boolean], fHashes:  List[Int => Int])={
    for (f<-fHashes){
      arr(f(x)) = true
    }
  }

  def h1(v:Int):Int={
    (Math.pow(v+7, 11) %100).toInt
  }
  def h2(v:Int):Int={
    (Math.pow(v+11, 7) %100).toInt
  }
}
