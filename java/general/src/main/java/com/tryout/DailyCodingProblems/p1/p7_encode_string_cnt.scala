package com.tryout.DailyCodingProblems.p1

import scala.collection.mutable.ArrayBuffer

// Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
//For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
//You can assume that the messages are decodable. For example, '001' is not allowed.
object p7_encode_string_cnt {

  def main(args: Array[String]): Unit = {
    val m = Map(
      1->"a",
      2->"b",
      3->"c",
      4->"d",
      5->"e",
      6->"f",
      7->"g",
      8->"h",
      9->"i",
      10->"g",
      11->"k",
      12->"l",
      13->"m",
      14->"n",
      15->"o",
      16->"p",
      17->"q",
      18->"r",
      19->"s",
      20->"t",
      21->"u",
      22->"v",
      23->"w",
      24->"x",
      25->"y",
      26->"z"
    )
    val in = List(1,1,1,1)

    println(numEncodings(in))

    val s = in.size
    val res = ArrayBuffer.fill[Int](s+1)(0)
    res(s) = 1
    println(dyn_numEncodings(in, s-1, res))
    print()
  }

  def numEncodings(in: List[Int]): Int = in match {
    case 1::y::rest if y<=6 => numEncodings(y::rest) + numEncodings(rest)
    case x::rest => numEncodings(rest)
    case _ => 1
  }
  def dyn_numEncodings(in: List[Int], i:Int, res: ArrayBuffer[Int]):Int = {
    if(i<0){return res(0)}
    if(i+1 <= in.size-1 && (in(i)+in(i+1)).toInt<=26){
      res(i) = res(i+2)
    }
    res(i) = res(i) + res(i+1)
    dyn_numEncodings(in, i-1, res)
  }
}
