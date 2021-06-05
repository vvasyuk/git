package dcp
import scala.collection.mutable.HashSet

object q001_twoSum {
  def main(args: Array[String]): Unit = {
    val arr = Array(10, 15, 3, 7, 1)
    val k = 17

    sumTwo(arr, 17)

    sumTwo2(arr, 17)
  }

  def sumTwo(arr: Array[Int], k: Int): Boolean =
    val s = HashSet[Int]()
    var res = false
    for (i <- arr if !res)
      println(s"processing ${i}")
      if !s(k-i) then
        println(s"adding ${i} to set")
        s += i
        else
        println(s"found a sum")
        res = true
    res

  def sumTwo2(arr: Array[Int], k: Int): Boolean = 
    val s = HashSet[Int]()
    var res = false
    arr.foldLeft((s,res))((acc, v) => {
      var (s, r) = acc
      if !s(k-v) then
        println(s"adding ${v} to set")
        s += v
        else
        println(s"found a sum")
        r = true
      (s,r)
    })
    res
}
