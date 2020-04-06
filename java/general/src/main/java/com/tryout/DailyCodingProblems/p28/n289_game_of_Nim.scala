package com.tryout.DailyCodingProblems.p28

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// The game of Nim is played as follows. Starting with three heaps, each containing a variable number of items, two players
// take turns removing one or more items from a single pile. The player who eventually is forced to take the last stone loses.
// For example, if the initial heap sizes are 3, 4, and 5, a game could be played as shown below:
//
//  A  |  B  |  C
//-----------------
//  3  |  4  |  5
//  3  |  1  |  3
//  3  |  1  |  3
//  0  |  1  |  3
//  0  |  1  |  0
//  0  |  0  |  0
//In other words, to start, the first player takes three items from pile B. The second player responds by removing two stones from pile C.
// The game continues in this way until player one takes last stone and loses.
//
//Given a list of non-zero starting values [a, b, c], and assuming optimal play, determine whether the first player has a forced win.
object n289_game_of_Nim {
  def main(args: Array[String]): Unit = {
    val in = (3,4,5)
    //getMoves(in)
    val results = mutable.Map[(Int, Int, Int),Int]()
    print(nim(in, results))
  }

  def update(heap: (Int, Int, Int), idx: Int, pr: Int): (Int, Int, Int) = {
    val l = heap.productIterator.toList.map(_.toString.toInt)
    val l1 = l.updated(idx, l(idx)-pr)
    (l1(0), l1(1), l1(2))
  }

  def getMoves(heap: Tuple3[Int,Int,Int]):ArrayBuffer[(Int,Int,Int)]={
    var moves = ArrayBuffer[(Int,Int,Int)]()

    for(
      (pile, idx) <- heap.productIterator.zipWithIndex;
      pr <- 1 to pile.toString.toInt
    ){
      //println(pile + ":" + pr + " idx: " + idx)
      moves+=update(heap,idx,pr)
    }

    //moves.foreach(println(_))
    moves
  }

  def nim(heap: (Int, Int, Int), results: mutable.Map[(Int, Int, Int),Int]):Int=heap match{
    case (0,0,0) => 1
    case x if results.contains(x) => {results(x)}
    case t => {
      val moves = getMoves(t)
      val res = for (
        m <- moves
        ) yield 1-nim(m, results)
      results(t)=res.max
      results(t)
    }
  }
}
