package com.tryout.DailyCodingProblems.p29

// You have N stones in a row, and would like to create from them a pyramid.
// This pyramid should be constructed such that the height of each stone increases by one
// until reaching the tallest stone, after which the heights decrease by one.
// In addition, the start and end stones of the pyramid should each be one stone high.
//
//You can change the height of any stone by paying a cost of 1 unit to lower its height by 1,
// as many times as necessary. Given this information, determine the lowest cost method to
// produce this pyramid.
//
//For example, given the stones [1, 1, 3, 3, 2, 1], the optimal solution is to pay 2 to create
// [0, 1, 2, 3, 2, 1].
object p293_pyramid_of_stones {

  def find_cost(idx: Int, height: Int, stones: Array[Int]): Option[Int] = {
    val heights = for(x<-stones.zipWithIndex)yield{
      height-Math.abs(idx-x._2)
    }
    val costs = for(
      t<-stones.zip(heights)
    )yield{
      Math.abs(t._1-t._2)
    }
    //stones.zip(heights).foldLeft(0){(acc,tup) => acc+Math.abs(tup._1-tup._2)}
    if (costs.exists(x=>x>1)) {
      None
    }else{
      Some(costs.sum)
    }
  }

  def build_pyramid(stones: Array[Int]): Int = {
    var minCost = Int.MaxValue

    for(
      x<-1 until stones.size-1;
      h<-Range(stones(x),0,-1)
    ){
      //println(" x: " + x + " h: " + h)
      val c = find_cost(x,h,stones)
      c match{
        case Some(x) => if (x<minCost) minCost=x
        case _ =>
      }
    }
    minCost
  }

  def main(args: Array[String]): Unit = {
    val stones = Array(1, 1, 3, 3, 2, 1)

    assert(2==find_cost(3, 3, stones).get)
    build_pyramid(stones)
  }
}

// dynamic solution
// def build_pyramid(stones):
//    left = [0 for _ in range(len(stones))]
//    right = [0 for _ in range(len(stones))]
//
//    left[0] = 1
//    for i in range(1, len(stones)):
//        left[i] = min(stones[i], left[i - 1] + 1)
//
//    right[-1] = 1
//    for i in range(len(stones) - 2, -1, -1):
//        right[i] = min(stones[i], right[i + 1] + 1)
//
//    min_heights = [min(l, r) for (l, r) in zip(left, right)]
//
//    center = min_heights.index(max(min_heights))
//    height = min_heights[center]
//    cost = find_cost(center, height, stones)
//
//    return cost