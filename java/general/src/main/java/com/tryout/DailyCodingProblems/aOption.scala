package com.tryout.DailyCodingProblems

object aOption {
  def main(args: Array[String]): Unit = {
    val s = Some("string")
    //val s = None

    s.map(println(_))
  }
}
