package com.tryout.spark.Ingest.impl.B

import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}

import com.tryout.spark.Ingest.model.A.{AIn, AOut}

class BMapper(p: String) {

  val inPattern = DateTimeFormatter.ofPattern(p)
  val outPattern = DateTimeFormatter.ofPattern("yyyy")

  def map(in: Iterator[AIn]): Iterator[AOut] = in.map(x => {
    val z = ZonedDateTime.parse(s"${x.col2} ${ZoneId.of("UTC")}", inPattern)
    AOut(
      col0 = x.col0,
      col1 = x.col1,
      col2 = x.col2,
      col3 = x.col3,
      col4 = z.format(outPattern)
    )
  })

}
