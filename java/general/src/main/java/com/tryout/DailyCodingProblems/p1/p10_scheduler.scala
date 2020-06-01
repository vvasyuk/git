package com.tryout.DailyCodingProblems.p1

// Implement a job scheduler which takes in a function f and an integer n, and calls f after n milliseconds.
object p10_scheduler {

  def execute(f: () => Unit, num: Int): Unit = {
    Thread.sleep(num)
    f()
  }

  def main(args: Array[String]): Unit = {

    execute(()=>println("wow"), 1000)
  }
}
