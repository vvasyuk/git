package dcp.p0

// Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
// For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

object q007_numEncodings {
  def main(args: Array[String]): Unit =
    println(numEncodings("111"))

  def numEncodings(s: String): Int =
    val m = (
      1 -> "a",
      2 -> "b",
      3 -> "c",
      4 -> "d",
      5 -> "e",
      6 -> "f",
      7 -> "g",
      8 -> "h",
      9 -> "i",
      10 -> "j",
      11 -> "k",
      12 -> "l",
      13 -> "m",
      14 -> "n",
      15 -> "o",
      16 -> "p",
      17 -> "q",
      18 -> "r",
      19 -> "s",
      20 -> "t",
      21 -> "u",
      22 -> "v",
      23 -> "w",
      24 -> "x",
      25 -> "y",
      26 -> "z"
    )
    val arr = Array.fill[Int](s.length+1)(0)
    arr(s.length) = 1
    println(s.zipWithIndex.reverse.mkString(","))

    s.zipWithIndex.reverse.foldLeft(arr){ (acc, elem) =>
      if arr.size-2 == elem._2 then
        arr(elem._2) = arr(elem._2 + 1)
      else
        if (s.slice(elem._2, elem._2+2)).toInt <=26 then
          arr(elem._2) = arr(elem._2 + 2)
        arr(elem._2) += arr(elem._2 + 1)
      acc
    }

    arr(0)
}
