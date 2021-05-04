package general.abasics.a01Classes.classes

class CheckSumAccumulator {
  private var sum = 0                     // body of class is executed during class initialization (is a constructor)

  def add(b:Byte): Unit = sum += b        // b param is val, cannot be reassigned
  def checksum(): Int = ~(sum&0xFF) + 1
}

import scala.collection.mutable
object CheckSumAccumulator {
  private val cache = mutable.Map.empty[String, Int]
  def calculate(s: String): Int =
    if (cache.contains(s)) {
      println("found in cache")
      cache(s)
    } else {
      println("not found in cache")
      val acc = new CheckSumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }

  def main(args: Array[String]): Unit = {
    val acc = new CheckSumAccumulator
    println(acc.checksum())
    acc.add(2)
    println(acc.checksum())

    println(CheckSumAccumulator.calculate("test"))
    println(CheckSumAccumulator.calculate("test"))
  }
}