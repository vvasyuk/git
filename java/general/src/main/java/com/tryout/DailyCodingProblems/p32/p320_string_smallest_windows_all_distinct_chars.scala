package com.tryout.DailyCodingProblems.p32

import scala.collection.mutable

// Given a string, find the length of the smallest window that contains every distinct character.
// Characters may appear more than once in the window.
//
//For example, given "jiujitsu", you should return 5, corresponding to the final five letters.
object p320_string_smallest_windows_all_distinct_chars {
  def main(args: Array[String]): Unit = {
    val in = "jiujitsu"
    println(execute(in))
  }

  def execute(in: String): Int = {

    var start = 0
    val char_total = in.toSet.size
    var char_seen = 0
    val m = mutable.Map[Char, Int]().withDefaultValue(0)
    var res = Int.MaxValue

    for(cIdx<-in.zipWithIndex){
      val (c, i) = cIdx
      m(c) += 1
      if(m(c)==1) char_seen += 1

      if (char_seen == char_total){
        while(m(in(start))>1){
          m(in(start)) -= 1
          start +=1
        }
        val window = i-start+1
        if(window<res) res = window
      }
    }
    res
  }
}
