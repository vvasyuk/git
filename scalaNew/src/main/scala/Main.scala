import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    println("Start")

    val conf = ConfigFactory.load
    val foo1 = conf.getInt("foo")

    println(foo1)

    val d = readData("D:\\work\\tryout\\scalaNew\\src\\main\\resources\\t1.csv")

    d.foreach(i=>println(i._1 + "-" + i._2.mkString(",")))

  }

  def readData(fileName:String): Vector[(String, Array[String])] = {
    for {
      line <- Source.fromFile(fileName).getLines().toVector
      splitted = line.split(",").map(_.trim)
    } yield (splitted.head, splitted.tail)
  }
}
