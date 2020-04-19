package com.tryout.DailyCodingProblems.p30

// Given a clock time in hh:mm format, determine, to the nearest degree, the angle between the hour and the minute hands.
//
//Bonus: When, during the course of a day, will the angle be zero?
object p303_clock_angle_between_h_m {

  def angle(hour: 12, minute: 30): Int = {
    val h = 30 * hour + 6 * (minute / 12)
    val m = 6 * minute
    Math.min(Math.abs(h-m), 360-Math.abs(h-m))
  }

  def main(args: Array[String]): Unit = {
    angle(12,30)
  }
}
