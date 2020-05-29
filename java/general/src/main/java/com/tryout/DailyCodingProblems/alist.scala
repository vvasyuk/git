package com.tryout.DailyCodingProblems

object alist {
  def main(args: Array[String]): Unit = {
    //rec(List(1,2,3))
    pMatch(List(1,2,3))
  }

  def rec(l: List[Int]): Unit = l match {
      case a::b::rest => {
        println(s"$a $b")
        rec(b::rest)
      }
      case _ =>
  }

  def pMatch(l: List[Int]):Unit = l match{
    case 1::2::rest => println(s"one"); pMatch(2::rest)
    case 1::rest => println(s"one"); pMatch(rest)
    case e::rest => println(e); pMatch(rest)
    case _ =>
  }
}
