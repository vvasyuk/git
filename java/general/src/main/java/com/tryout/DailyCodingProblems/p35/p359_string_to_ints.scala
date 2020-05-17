package com.tryout.DailyCodingProblems.p35

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// You are given a string formed by concatenating several words corresponding to the integers zero through nine and then anagramming.
//
//For example, the input could be 'niesevehrtfeev', which is an anagram of 'threefiveseven'. Note that there can be multiple instances of each integer.
//
//Given this string, return the original integers in sorted order. In the example above, this would be 357.
object p359_string_to_ints {
  // If we examine the integers a little more closely, we can see that quite a few of them have letters that are unique. Specifically,
  // the digits 0, 2, 4, 6, and 8 each contain the only representation of a specific character.

  // Therefore, we can first count the number of times each of these characters appear, and assign these integers their corresponding count.

  // Next, we can take a look at 3, 5, and 7. Each of these words contain a letter that is almost unique, except for another appearance in one of the previous five words.
  //
  //three: count('h') - result[8]
  //five: count('f') - result[4]
  //seven: count('s') - result[6]
  //Finally, the remaining two integers, 1 and 9, share several letters with other numbers, but by straightforward arithmetic we can cancel out the other factors to obtain the appropriate result.
  //
  //one: count('o') - result[0] - result[2] - result[4]
  //nine: count('i') - result[5] - result[6] - result[8]

  def getDigits(input: String): String = {
    val letterCounts = mutable.Map[Char, Int]()
    for { c <- input}{
      letterCounts.updateWith(c)({
        case Some(count) => Some(count + 1)
        case None        => Some(1)
      })
    }

    val intCounts = mutable.ArrayBuffer.fill[Int](10)(0)
    // unique letters
    intCounts(0) = letterCounts.getOrElse('z', 0)
    intCounts(2) = letterCounts.getOrElse('w', 0)
    intCounts(4) = letterCounts.getOrElse('u', 0)
    intCounts(6) = letterCounts.getOrElse('x', 0)
    intCounts(8) = letterCounts.getOrElse('g', 0)

    letterCounts.getOrElse('h', 0)

    intCounts(3) = letterCounts.getOrElse('h', 0) - intCounts(8)
    intCounts(5) = letterCounts.getOrElse('f', 0) - intCounts(4)
    intCounts(7) = letterCounts.getOrElse('s', 0) - intCounts(6)

    intCounts(1) = letterCounts.getOrElse('o', 0) - intCounts(0) - intCounts(2) - intCounts(4)
    intCounts(9) = letterCounts.getOrElse('i', 0) - intCounts(5) - intCounts(6) - intCounts(8)

    intCounts.zipWithIndex.filter { case (a,b) => a>0 }.map(_._2).mkString(",")
  }

  def main(args: Array[String]): Unit = {
    val input = "zeorifveneves"

    println(getDigits(input))
  }
}
