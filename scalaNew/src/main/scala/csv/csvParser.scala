import java.util.Map

import com.typesafe.config.{Config, ConfigFactory, ConfigValue}

import scala.io.Source
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class csvParser {
  def parse(x: Map.Entry[String, ConfigValue]): (String,String,String,String)= {
    val spltd = x.getKey.split("\\\"\\.")
    val crdnts = spltd(0).replaceAll("\\\"","").split(",")
    (crdnts(0),crdnts(1),spltd(1),x.getValue().unwrapped().toString)
  }

  def ranger(rng: String, tCnt: Int): Range = {
    val r = rng.split("\\..")
    val tmp = if ("n".equals(r(1))) tCnt else r(1).toInt
    Range(r(0).toInt,tmp)
  }

  def applySomeCfg(line: Int, lineCnt: Int, col: Int, colCnt: Int, cell: String, cTupleSet: mutable.Set[(String,String,String,String)]): String = {
    var acc = "{text:" + cell + "}"
    cTupleSet.foreach(t=>{
      if ((ranger(t._1, lineCnt) contains line) && (ranger(t._2, colCnt) contains col)) {
        acc = acc.replaceAll("}", "," + t._3 + ":" + t._4 + "}")
      }
    })
    acc
  }
  def readData(fileName:String): Vector[(String, Array[String])] = {
    for {
      line <- Source.fromResource(fileName).getLines().toVector
      splitted = line.split(",").map(_.trim)
    } yield (splitted.head, splitted.tail)
  }

  def readData2(fileName:String): Vector[ArrayBuffer[String]] = {
    for {
      line <- Source.fromResource(fileName).getLines().toVector
      splitted = line.split(",").map(_.trim)
      buf = ArrayBuffer(splitted: _*)
    } yield (buf)
  }

  def apply(): Unit ={
    println("Start")
    val d = readData2("t1.csv")

    val c = ConfigFactory.load().getConfig("pattern1")
    val cTupleSet = c.getConfig("textFormat")
      .entrySet().asScala.map( x=>{
      parse(x)
    })

    print(d(0).size)

    //data preparation

    d.zipWithIndex.foreach{ case (line, lidx) =>
      if (line(0).contains("blank") && line.size < d(0).size) {
        Range(0, d(0).size-line.size + c.getIntList("colsToDelete").size()).foreach(x=>{
          line+=""
        })
      }
      c.getIntList("colsToDelete").forEach(x=>line.remove(x))
    }

    val table = d.zipWithIndex.map{ case (line, lidx) =>
      line.zipWithIndex.map{ case (cell, cellIdx) =>
        applySomeCfg(lidx, d.size, cellIdx, line.size, cell, cTupleSet)
      }
    }

    table.foreach(line=>{
      line.foreach(cell=>{
        print(cell + ",")
      })
      println()
    })
    println("End")
  }
}
