//package com.tryout.spark.Ingest.impl.A
//
//import java.time.{ZoneId, ZonedDateTime}
//import java.time.format.DateTimeFormatter
//
//import com.tryout.spark.Ingest.model.A.{AIn, AOut}
//
//class AMapper(p: String) {
//
//  val inPattern = DateTimeFormatter.ofPattern(p)
//  val outPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//
//  def map(in: Iterator[AIn]): Iterator[AOut] = in.map(x => {
//    val z = ZonedDateTime.parse(s"${x.col2} ${ZoneId.of("UTC")}", inPattern)
//    AOut(
//      col0 = x.col0,
//      col1 = x.col1,
//      col2 = x.col2,
//      col3 = x.col3,
//      col4 = z.format(outPattern)
//    )
//  })
//
//}
