package com.tryout.DailyCodingProblems.p31

import scala.collection.mutable.ArrayBuffer

// You are going on a road trip, and would like to create a suitable music playlist. The trip will require N songs,
// though you only have M songs downloaded, where M < N. A valid playlist should select each song at least once, and guarantee a buffer of B songs between repeats.
//
//Given N, M, and B, determine the number of valid playlists.
object p318_playlists {
  def main(args: Array[String]): Unit = {
    val m = 10
    val n = 15
    val b = 3

    execute(m,n,b)
  }

  def execute(m: Int, n: Int, b: Int): Int = {
    val ways = ArrayBuffer.fill(m,n)(0)
    ways(0)(0) = 1
    
  }
}
// def valid_playlists(n, m, b):
//    ways = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
//    ways[0][0] = 1
//
//    for i in range(1, n + 1):
//        for j in range(1, m + 1):
//            ways[i][j] = ways[i - 1][j - 1] * (m - (j - 1)) + ways[i - 1][j] * j
//
//    return ways[n][m]