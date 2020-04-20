package com.tryout.DailyCodingProblems.p30

import util.control.Breaks._
import scala.collection.mutable

// Given a linked list, remove all consecutive nodes that sum to zero. Print out the remaining nodes.
//
//For example, suppose you are given the input 3 -> 4 -> -7 -> 5 -> -6 -> 6.
// In this case, you should first remove 3 -> 4 -> -7, then -6 -> 6, leaving only 5.
object p305_remove_som9_cons_nodes {

  def printNodes(l: ll):Unit = {
    var start = l
    var end = l

    while (start != null) {
      end = start
      var total = 0
      var skip = false

      while (end != null) {
        breakable {
          total += end.value
          if (total == 0) {
            start = end
            skip = true
            break
          }
          end = end.next
        }
      }
      if (!skip) println(start.value)
      start = start.next
    }
  }

  def main(args: Array[String]): Unit = {
    var in = ll(3,ll(4,ll(-7,ll(5,ll(-6,ll(6))))))

    printNodes(in)
  }
}
case class ll(value:Int, next:ll=null)

//TODO rewrite in functional way