package com.tryout.spark

import org.apache.spark.sql.SparkSession

package object Ingest {
  def getSession = SparkSession
    .builder()
    .appName("Ingest")
    .config("spark.master", "local")
    .getOrCreate()
}