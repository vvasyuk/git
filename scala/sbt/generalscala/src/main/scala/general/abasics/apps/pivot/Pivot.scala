package pivot

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.functions._

import scala.collection.immutable.TreeMap

object Pivot {
  type K = (Int,Int,String,String)

  def main(args: Array[String]): Unit = {
    println("Start")
    val d = readData2("to_pivot.txt")

    val r = customUnpivot(getStructure(d))
    val r1 = flatTree(r).toString
    println(r1)
  }

  def flatTree(in: TreeMap[K, TreeMap[Int, String]])={
    in.foldLeft(new StringBuilder){(acc, v)=>
      val tmp = new StringBuilder
      tmp ++= v._1._3 + "\t" + v._1._4
      v._2.foreach(col=> tmp++="\t"+col._2)
      tmp ++= "\n"
      acc ++= tmp
    }
  }

  def customUnpivot(in: Vector[Structured]): TreeMap[K, TreeMap[Int,String]] ={
    val res = in.foldLeft( TreeMap[K, TreeMap[Int,String]]()){ (acc, v) =>
      val k: K = (v.sectionOrd.toInt, v.insSectionOrd.toInt, v.rep1, v.rep2)
      val cols = acc.get(k).getOrElse(TreeMap[Int,String]())
      val newCols = cols ++ TreeMap[Int,String]((v.colOrd.toInt,v.value))
      val newTree = TreeMap[K, TreeMap[Int, String]]((k, newCols))
      acc ++ newTree
    }
    res
  }

  def getStructure(in:Vector[Array[String]]) ={
    for{
      line <- in
      res=Structured(line(0),line(1),line(2),line(3),line(4),line(5),line(6))
    } yield (res)

  }

  def readData2(fileName:String) = {
    for {
      line <- Source.fromResource(fileName).getLines().drop(1).toVector
      splitted = line.split("\t", -1).map(_.trim)
      //buf = ArrayBuffer(splitted: _*)
    } yield (splitted)
  }

//  def sparkPivot(): Unit ={
//    val spark: SparkSession = SparkSession.builder()
//      .master("local[1]")
//      .appName("SparkByExamples.com")
//      .getOrCreate()
//
//    val data = Seq(("Banana",1000,"USA"), ("Carrots",1500,"USA"), ("Beans",1600,"USA"),
//      ("Orange",2000,"USA"),("Orange",2000,"USA"),("Banana",400,"China"),
//      ("Carrots",1200,"China"),("Beans",1500,"China"),("Orange",4000,"China"),
//      ("Banana",2000,"Canada"),("Carrots",2000,"Canada"),("Beans",2000,"Mexico"))
//
//    import spark.sqlContext.implicits._
//    val df = data.toDF("Product","Amount","Country")
//    df.show()
//  }
}

case class Structured(col: String,colOrd: String,sectionOrd: String,insSectionOrd: String,rep1: String,rep2: String,value: String)
