package com.tryout.DailyCodingProblems

import scala.collection.mutable

object test {
  def main(args: Array[String]): Unit = {
    val counts = mutable.Map[Char, Int]()
    for { c <- "abcdef"}{
      counts.updateWith(c)({
        case Some(count) => Some(count + 1)
        case None        => Some(1)
      })
    }
  }
}
