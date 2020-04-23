package com.tryout.DailyCodingProblems.p30

// There are M people sitting in a row of N seats, where M < N. Your task is to redistribute people such that there are no gaps between any of them,
// while keeping overall movement to a minimum.
//
//For example, suppose you are faced with an input of [0, 1, 1, 0, 1, 0, 0, 0, 1], where 0 represents an empty seat and 1 represents a person.
// In this case, one solution would be to place the person on the right in the fourth seat. We can consider the cost of a solution to be the sum of
// the absolute distance each person must move, so that the cost here would be five.

// TODO: rewrite inmore functional way
object p309_row_seats_redistribute {
  // figure out where the middle is ( location of the median person)
  // start at the median and extend outward to the left, and then to the right
  // when come across an empty seat, we swap it (if possible) with a person sitting further out.
  def main(args: Array[String]): Unit = {
    val seats = Array(1, 0, 0, 0, 1, 0, 0, 0, 1)
    val people = seats.zipWithIndex.filter(_._1==1).map(_._2)
    val n = people.size
    val median = people(n/2)
    var cost = 0

    // move left seats closer to median
    var i = median-1
    var j = n/2-1
    while (i>=0 && j>=0){
      if (seats(i) == 0){
        cost+=Math.abs(people(j)-i)
        val tmp = seats(i)
        seats(i) = seats(people(j))
        seats(people(j)) = tmp
        j-=1
      }
      i-=1
    }

    // right
    i = median+1
    j = n/2+1
    while(i<=seats.size && j<=n-1){
      if (seats(i) == 0){
        cost+=Math.abs(people(j)-i)
        val tmp = seats(i)
        seats(i) = seats(people(j))
        seats(people(j)) = tmp
        j+=1
      }
      i+=1
    }
    println("cost: " + cost)
  }
}
