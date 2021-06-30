package dcp.p1

// This problem was asked by Amazon.
//
//Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
//
//For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
object q013_longestSubstringWithKDistinctchars {

  def main(args: Array[String]): Unit = {
    val s = "abccbba"
    val k = 2
    println(longestSubstringWithKDistinctchars(s, k))
  }

  def longestSubstringWithKDistinctchars(s: String, k: Int): Int = {
    val mapRes = s.zipWithIndex.foldLeft((Map[Char, Int](), (0,0), 0)){ (acc, el) =>
      val (c, idx) = el
      val (map, tup, maxLength) = acc

      var newMap = map + (c -> idx)

      val newLower = if newMap.size <= k then
        tup._1
      else
        newMap -= s(tup._1)
        tup._1 + 1

      val newTup = (newLower, (tup._2 + 1))
      val newMax = math.max(maxLength, newTup._2 - newTup._1)
      (newMap, newTup, newMax)
    }
    
    println(mapRes._1.mkString(","))

    mapRes._3
  }
}
