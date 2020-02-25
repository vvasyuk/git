package com.tryout.DailyCodingProblems.p27

// Write a function, throw_dice(N, faces, total), that determines how many ways it is possible to throw N dice with some number of faces each to get a specific total.
//For example, throw_dice(3, 6, 7) should equal 15.

//Input: d = 1, f = 6, target = 3
//Output: 1
//Explanation:
//You throw one die with 6 faces.  There is only one way to get a sum of 3.

//Input: d = 2, f = 6, target = 7
//Output: 6
//Explanation:
//You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
//1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
object n272_dice_throw_cnt {

  def main(args: Array[String]): Unit = {
    println(throwDice(2,6,7))
  }

  def throwDice(n: Int, faces: Int, total: Int):Int={
    if (n==1) {
      if (total<=faces){
        return 1
      }else
        return 0
    }

    var ways = 0
    for(x <- 1 to faces.min(total)){
      ways += throwDice(n-1, faces, total-faces)
    }
    ways
  }
}

// dp:
//def throw_dice(n, faces, total):
//    ways = [[0 for _ in range(total + 1)] for _ in range(n)]
//
//    for t in range(1, total + 1):
//        ways[0][t] = 1 if t <= faces else 0
//
//    for dice in range(1, n):
//        for t in range(1, total + 1):
//            for face in range(1, min(t, faces + 1)):
//                ways[dice][t] += ways[dice - 1][t - face]
//
//    return ways[-1][-1]