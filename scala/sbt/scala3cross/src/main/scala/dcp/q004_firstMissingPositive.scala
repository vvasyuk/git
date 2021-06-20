package dcp

// Given an array of integers, find the first missing positive integer in linear time and constant space. In other words,
// find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

object q004_firstMissingPositive {
  def main(args: Array[String]): Unit =
    val in1 = Array(3, 4, -1, 1)
    val in2 = Array(1, 2, 0)
    println(s"res: ${firstMissingPositive(in1)}")

  def firstMissingPositive(arr: Array[Int]): Int = {
    def _swap(idx: Int, arr: Array[Int]): Unit = {
      println(s"processing idx $idx")
      while(arr(idx) != idx+1 && arr(idx) > 0 && arr(idx) <= arr.size && arr(idx) != arr(arr(idx)-1))
        println(s"swappint: $idx ${arr(idx)}")
        val a = arr(idx)
        arr(idx) = arr(a-1)
        arr(a-1) = a
    }

    for(i <- 0 until arr.size)
      _swap(i, arr)

    def _checkMissing(arr: Array[Int]): Int = {
      println(arr.zipWithIndex.mkString(","))
      val res = arr.zipWithIndex.find((a,idx) => a!=idx+1)
      res.getOrElse((-1,-2))._2+1
    }

    val res = _checkMissing(arr)
    println(arr.mkString(","))
    res
  }
}
