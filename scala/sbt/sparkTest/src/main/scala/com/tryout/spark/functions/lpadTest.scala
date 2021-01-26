package com.tryout.spark.functions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, concat, upper, lpad}

object lpadTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("SparkSessionExample")
      .config("spark.master", "local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val dfTable = {Seq[(String,String,String,String,Integer,Integer)](
      ("0","0", "rev0", "cost0", 1, 1),
      ("1","1", "rev1", "cost1", 11, 10),
      ("2","2", "rev2", null, 10, null)
    ).toDF("id1", "id2", "rev", "cost", "rev_m", "cost_m")
    }

    dfTable.withColumn("lpaded",
      lpad(col("id1"), 2, "0"))
      .show(false)

  }
}
