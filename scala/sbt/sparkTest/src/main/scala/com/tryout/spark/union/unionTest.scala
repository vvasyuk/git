package com.tryout.spark.union

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object unionTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df0 = Seq(
      ("2", "id2", "d2", "4"),
      ("4", "id4", "d4", "5"),
      ("13", "id13", "d13", "5")
    ).toDF("id1", "id2", "df1_data", "df1_measure")

    val df1 = Seq(
      ("2", "id2", "d2", "1"),
      ("3", "id3", "d3", "3"),
      ("13", "id13", "aaa", "5")
    ).toDF("id1", "id2", "df1_data", "df1_measure")

    // will leave non duplicates
    val df11 = df1.join(df0, df1("id1")===df0("id1"), "left_anti")

    //df11.show(false)

    val unioned = df0.union(df11)
    unioned.show(false)
  }
}
