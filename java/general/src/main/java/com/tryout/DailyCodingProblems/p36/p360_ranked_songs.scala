package com.tryout.DailyCodingProblems.p36

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


// You have access to ranked lists of songs for various users. Each song is represented as an integer,
// and more preferred songs appear earlier in each list. For example, the list [4, 1, 7] indicates that a user likes song 4 the best, followed by songs 1 and 7.
//Given a set of these ranked lists, interleave them to create a playlist that satisfies everyone's priorities.
//For example, suppose your input is {[1, 7, 3], [2, 1, 6, 7, 9], [3, 9, 5]}. In this case a satisfactory playlist could
// be [2, 1, 6, 7, 3, 9, 5].
object p360_ranked_songs {

  def execute(in: List[List[Int]]) = {
    // Create a map from each song to songs that come directly after in the input.
    val prev_to_next = mutable.Map[Int, ListBuffer[Int]]()

    for { songList <- in
          (before, after) <- songList zip songList.tail
    }{
      prev_to_next.getOrElseUpdate(before, ListBuffer[Int]()).append(after)
    }

    // Keep track of how many predecessors each song has.
    val prev_count = mutable.Map[Int, Int]().withDefaultValue(0)
    for { nextSongs <-  prev_to_next.values
          song <- nextSongs
          } {
      prev_count(song) += 1
    }

    // Initialize a queue to represent the playlist, starting with songs without predecessors.
    val q = mutable.Queue[Int]()
    val res = ArrayBuffer[Int]()

    for { song <- in.flatten if !prev_count.contains(song)} {
      q.append(song)
      res.append(song)
    }

    // Pop elements one at a time and get their successors.
    // If a successor has no other predecessors, add it to the result.
    while(q.nonEmpty){
      val song = q.dequeue()
      val successors = prev_to_next.getOrElse(song, ArrayBuffer())
      for { next_song <- successors } {
        prev_count(next_song) -= 1
        if (prev_count(next_song) == 0){
          q.append(next_song)
          res.append(next_song)
        }
      }
    }

    res
  }

  def main(args: Array[String]): Unit = {
    val in = List(
      List(1, 7, 3),
      List(2, 1, 6, 7, 9),
      List(3, 9, 5)
    )

    execute(in)
  }
}
