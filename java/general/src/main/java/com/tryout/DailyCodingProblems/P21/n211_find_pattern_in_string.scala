package com.tryout.DailyCodingProblems.P21

import scala.collection.mutable.ArrayBuffer

//Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string.
//For example, given the string "abracadabra" and the pattern "abr", you should return [0, 7].

object n211_find_pattern_in_string {


  def execute(s: String, pattern: String): ArrayBuffer[Int] = {
    val res = ArrayBuffer[Int]()
    var sIdx = 0
    var pIdx = 0
    var tmp = -1

    while(sIdx != s.size){
      if(s(sIdx) == pattern(pIdx)){
        if(pIdx==0){tmp = sIdx}
        pIdx+=1
        if(pIdx==pattern.size){
          pIdx=0
          res+=tmp
        }
      } else{
        pIdx=0
      }
      sIdx+=1
    }
    res
  }

  def main(args: Array[String]):Unit={
    execute("abracadabra", "abr")
  }
}