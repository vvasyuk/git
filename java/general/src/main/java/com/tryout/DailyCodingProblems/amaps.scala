package com.tryout.DailyCodingProblems

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object amaps {
  def main(args: Array[String]): Unit = {

    var prev_to_next = mutable.Map[Int, ListBuffer[Int]]()
    for { songList <- List(List(1,2,3),List(4,1,6),List(7,8,9))
          (before, after) <- songList zip songList.tail
          }{
      prev_to_next.getOrElseUpdate(before, ListBuffer[Int]()).append(after)
    }
    println(prev_to_next)


    val prev_count = mutable.Map[Int, Int]().withDefaultValue(0)
    for { nextSongs <-  prev_to_next.values
          song <- nextSongs
          } {
      prev_count(song) += 1
    }


    val counts = mutable.Map[Char, Int]()
    for { c <- "abcdef"}{
      counts.updateWith(c)({
        case Some(count) => Some(count + 1)
        case None        => Some(1)
      })
    }
  }
}
