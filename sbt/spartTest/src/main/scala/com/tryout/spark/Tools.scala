package com.tryout.spark

import org.apache.spark.sql.SparkSession

object Tools {
  def getSession = SparkSession
    .builder()
    .appName("SparkSessionExample")
    .config("spark.master", "local")
    .getOrCreate()
}
