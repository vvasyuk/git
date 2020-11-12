package com.tryout.spark.aggr

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, collect_set, concat, count, lit, pow, row_number, size, sum}

object unionWithWindows {
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

    dfTable.show(false)

    val dfDelta = Seq[(String,String,String,String,Integer,Integer)](
      ("0","0", "rev0", "cost0", 10, 10),
      ("1","1", null, "cost1",null, 1),
      ("2","2", "rev2", "cost2", 1, 11),
      ("13","13", null, "cost1", null, 10)
    ).toDF("id1", "id2", "rev", "cost", "rev_m", "cost_m")

    dfDelta.show(false)

    val unioned = dfTable.union(dfDelta).orderBy(col("id1"))

    val pk = Window.partitionBy("id1", "id2")
    val all = Window.partitionBy("id1", "id2","rev","cost").orderBy("id1")
    unioned
      .withColumn("rev_m", sum("rev_m").over(pk))
      .withColumn("cost_m", sum("cost_m").over(pk))
      .filter(col("rev") =!= "")
      .filter(col("cost") =!= "")
      .withColumn("cntDistinct", size(collect_set(concat(col("id1"), col("id2"))).over(all)))
      .withColumn("rowNumber", row_number.over(all))
      .filter(col("cntDistinct") === 1 && col("rowNumber") === 1)
    .show(false)
    // 0-0 = 11-11
    // 1-1 = 11-11
    // 2-2 = 11-11
    // 13-13 = should be removed
  }
}
