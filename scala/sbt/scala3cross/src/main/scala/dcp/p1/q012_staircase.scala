package dcp.p1

// There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
//
//For example, if N is 4, then there are 5 unique ways:
//
//1, 1, 1, 1
//2, 1, 1
//1, 2, 1
//1, 1, 2
//2, 2


//+---+---+---+---+
//| 1 | 1 | 1 | 1 |
//+---+---+---+---+
//  5   3   2   1
object q012_staircase {

  def main(args: Array[String]): Unit = println(staircase(4))
  

  def staircase(i: Int): Int =
    val arr = Array.fill(i+1)(0)
    arr(i-1) = 1
    arr(i) = 1
      
    for(
      x <- (0 to i-2).reverse
    ) arr(x) = arr(x+1) + arr(x+2)
    println(s"array state: ${arr.mkString(",")}")
    arr(0)
}
