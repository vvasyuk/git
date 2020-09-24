package com.tryout.spark.Ingest.model.A

import org.apache.spark.sql.Encoders
import org.apache.spark.sql.types.StructType

case class AIn(
  col0: String,
  col1: String,
  col2: String,
  col3: String
)

object AIn {
  def getSchema: StructType = {
    Encoders.product[AIn].schema
  }
}
