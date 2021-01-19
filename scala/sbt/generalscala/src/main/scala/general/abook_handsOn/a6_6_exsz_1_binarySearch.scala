package general.abook_handsOn

object a6_6_exsz_1_binarySearch {
//  Implement a simple binary search algorithm in Scala, to find an element within a sorted
//  Array in O(log n) time.

  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4,5,6,7,8,9,10)
    val res = binarySearch(arr, 0, arr.size-1, 2)
    //println(res.get)

    val resGeneric = binarySearchGeneric(arr, 0, arr.size-1, 2)
    println(resGeneric)
  }

  def binarySearch(arr: Array[Int], from: Int, to: Int, i: Int): Option[Int] = {
    val mid = arr((to-from)/2)
    if (mid == i)
      Some(mid)
    else if (from > to)
      None
    else if (mid > i)
      binarySearch(arr, mid+1, to, i)
    else
      binarySearch(arr, from, mid-1, i)
  }

  def binarySearchGeneric[T: Ordering](arr: IndexedSeq[T], from: Int, to: Int, i: T): Option[Int] = {
    val midIdx = from+((to-from)/2)
    if (arr(midIdx) == i)
      Some(midIdx)
    else if (from > to)
      None
    else if (Ordering[T].lt(arr(midIdx),i))
      binarySearchGeneric(arr, midIdx+1, to, i)
    else
      binarySearchGeneric(arr, from, midIdx-1, i)
  }

}
