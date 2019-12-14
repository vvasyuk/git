package com.tryout.DailyCodingProblems

import scala.collection.mutable

object n199_correct_string_of_braces {


  def execute(value: "(()"): Unit = {
    var s = mutable.Stack[(String, Int)]()

    value.zipWithIndex.foreach(x=>{
      println(x._1 + " : " + x._2)
      x._1 match{
        case '(' => s.push(("(", x._2))
        case ')' => s.pop()
      }
    })

    s.foreach(println(_))

  }

  def main(args: Array[String]):Unit={
    execute("(()")
  }
}
