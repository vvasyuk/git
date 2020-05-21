package com.tryout.DailyCodingProblems.p36

//Write a function, add_subtract, which alternately adds and subtracts curried arguments. Here are some sample operations:
//
//add_subtract(7) -> 7
//add_subtract(1)(2)(3) -> 1 + 2 - 3 -> 0
//add_subtract(-5)(10)(3)(9) -> -5 + 10 - 3 + 9 -> 11
object p363_add_subtract {
  def main(args: Array[String]): Unit = {
    val in = List(1,2,3,4)
    println(rec(in, true).get)
  }

  def rec(l: List[Int], plus: Boolean): Option[Int] = l match {
    case a::b::rest => {
      println(s"$a $b")
      if(plus) rec((a+b)::rest, !plus) else rec((a-b)::rest, !plus)

    }
    case a::Nil => Some(a)
    case _ => None
  }
}
