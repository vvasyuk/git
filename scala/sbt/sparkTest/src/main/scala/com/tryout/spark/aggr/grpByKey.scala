package com.tryout.spark.aggr
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit, pow, sum}

object grpByKey {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df = Seq(
      ("13","abc", "11111111", "7"),
      ("13","abc", "22222222", "7"),
      ("13","abc", "33333333", "7"),
      ("13","abd", "66666666", "7"),
      ("14","abd", "66666666", "7")
    ).toDF("id1", "id2", "number", "decimal")

    df.as[Record]
      .groupByKey(rec => (rec.id1, rec.id2))
      .flatMapGroups((_, iter) => {
        iter.toList.headOption
      })
      .show(false)
  }
}
case class Record(id1: String, id2: String, number: String, decimal: String)
