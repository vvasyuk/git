package pivot

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Pivot {
  def main(args: Array[String]): Unit = {
    println("Start")
    val d = readData2("to_pivot.txt")
    println("Start")
  }

  def readData2(fileName:String): Vector[ArrayBuffer[String]] = {
    for {
      line <- Source.fromResource(fileName).getLines().toVector
      splitted = line.split(",").map(_.trim)
      buf = ArrayBuffer(splitted: _*)
    } yield (buf)
  }
}
