package com.tryout.spark.multiply

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object dfMultiply {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df = Seq(
      ("13", "id2", "3", "10")
    ).toDF("id1", "id2", "id3", "number")

    val ds = df.as[Record]
    ds.flatMap{
      case Record(x1, x2, x3, x4) => (0 to 10).map(x => Record((x1.toInt+x).toString, x2, x3, x4))
    }.toDF.show(false)

//    val ds = df.as[(String, String, String, String)]
//    ds.flatMap{
//      case (x1, x2, x3, x4) => List((x1, x2, x3, x4),(x1, x2, x3, x4))
//    }.toDF.show(false)
  }
}

case class Record(id1: String, id2: String, id3: String, number: String)
