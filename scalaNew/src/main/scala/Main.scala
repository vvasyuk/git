import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    println("Start")

    val conf = ConfigFactory.load
    val foo1 = conf.getInt("foo")

    val t1Desc = conf.getString("tableFormats.t1.description")
    val t1Conf = conf.getConfig("tableFormats.t1")

    println(t1Conf.getString("description"))

    val d = readData("t1.csv")



    println("columns:" + d(0)._2.length)
    d.foreach(i=>println(i._1 + "-" + i._2.mkString(",")))

  }

  def readData(fileName:String): Vector[(String, Array[String])] = {
    for {
      line <- Source.fromResource(fileName).getLines().toVector
      splitted = line.split(",").map(_.trim)
    } yield (splitted.head, splitted.tail)
  }
}
