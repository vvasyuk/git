package pivot

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Pivot {
  def main(args: Array[String]): Unit = {
    println("Start")
    val d = readData2("to_pivot.txt")

//    d.foreach(r=>{
//      print(r(0) + r(2)+ r(4))
//      println()
//    })

//    type row = mutable.TreeMap[Int, String]
//    type section = mutable.TreeMap[Int, row]
//    type sections = mutable.TreeMap[Int, section]

    type rows = mutable.TreeMap[Int, String]
    type section = mutable.TreeMap[Int, rows]
    type sections = mutable.TreeMap[Int, section]

    var map: mutable.TreeMap[String, ArrayBuffer[String]] = mutable.TreeMap();
    //var rows2: mutable.TreeMap[Int, String];
    //val stringList = d.foldLeft(List[String]()) { (acc, f) =>
    //val rows = d.foldLeft(mutable.TreeMap(): mutable.TreeMap[Int, ArrayBuffer[String]]) { (acc, f) =>
    val rows = d.foldLeft(mutable.TreeMap(): mutable.TreeMap[Int, rows]) { (acc, f) =>
      var c = acc.get(f(2).toInt)
      var r = c.get(f(3).toInt)
      r=r+f(4)
      c+=(f(2).toInt,r)
      acc+=(f(2).toInt->c)

    }

    println(rows.size)
  }

  def readData2(fileName:String): Vector[ArrayBuffer[String]] = {
    for {
      line <- Source.fromResource(fileName).getLines().drop(1).toVector
      splitted = line.split("\t").map(_.trim)
      buf = ArrayBuffer(splitted: _*)
    } yield (buf)
  }
}
