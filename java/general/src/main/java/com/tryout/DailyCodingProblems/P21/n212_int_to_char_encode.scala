package com.tryout.DailyCodingProblems.P21

//Spreadsheets often use this alphabetical encoding for its columns: "A", "B", "C", ..., "AA", "AB", ..., "ZZ", "AAA", "AAB", ....
//Given a column number, return its alphabetical column id. For example, given 1, return "A". Given 27, return "AA".

object n212_int_to_char_encode {

  def execute(n: Int): Unit = {
    var i = n
    var s = ""

    while (i>0){
      val remain = (i-1)%26
      i = (i-2)/26
      s+=(65+remain).toChar
    }
    println(s.reverse)
  }

  def main(args: Array[String]):Unit={
    execute(53)	//BA
  }
}
