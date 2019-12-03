package com.tryout.DailyCodingProblems

object test {
  var res: Array[String] = Array()
  //val b = a.++("e")

  def f(inArray: Array[String], accum: Array[String]):Unit={
    inArray match{
      case Array(a,_*) =>{
        println(a)
        //accum.foreach(println(_))
        f(inArray.tail, accum:+a)
      }
      case _ =>
    }
  }
  def main(args: Array[String]): Unit = {
    println("test started")
    val a = Array("a", "b", "c", "d")
    //val b: Array[String] = a :+"e"
    //b.foreach(println(_))
    f(a, Array())
  }
}
