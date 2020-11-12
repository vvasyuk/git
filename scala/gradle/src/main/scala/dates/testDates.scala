package dates

import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.{LocalDateTime, ZoneId, ZonedDateTime}

object testDates {

  def getDate(str: String) = {
    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val d = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

    if(d.getHour==0 && d.getMinute < 30){
      d.minusDays(1).format(outputFormat)
    }else{
      d.format(outputFormat)
    }

  }

  def main(args: Array[String]): Unit = {
    val d1 = ZonedDateTime.now(ZoneId.of("UTC"))
    val d2 = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

    println(getDate("2020-08-14 00:10"))
  }
}
