package com.tryout.DailyCodingProblems

object test {
  def main(args: Array[String]): Unit = {
    val p = "/1/2/3/4/5/6"
    val i  = p.substring(p.substring(0, p.lastIndexOf("/")).lastIndexOf("/"));
    println(i)
  }
}
