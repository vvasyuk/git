package com.tryout.DailyCodingProblems.p33

// The 24 game is played as follows. You are given a list of four integers, each between 1 and 9, in a fixed order.
// By placing the operators +, -, *, and / between the numbers, and grouping them with parentheses, determine whether it is possible to reach the value 24.
//
// For example, given the input [5, 2, 7, 8], you should return True, since (5 * 2 - 7) * 8 = 24.
// Write a function that plays the 24 game.
object p334_game_24 {
  def main(args: Array[String]): Unit = {
    val in = Array(5.0, 2.0, 7.0, 8.0)
    println(execute(in))

//    for(i<-0 until in.size-2;
//        x<-apply_opps(in(i), in(i+1))){
//      println(s"$i : $x before: ${in.slice(0,i).mkString(",")} after: ${in.slice(i+2,in.size).mkString(",")}")
//    }
  }
  def apply_opps(a:Double,b:Double) ={
    Array(a+b, a-b, a/b, a*b)
  }
  def execute(in: Array[Double]):Boolean={
    if(in.size==1){
      in(0)==24
    }else if(in.size==2){
      (for(i<-apply_opps(in(0),in(1)))yield{execute(Array(i))}).exists(_==true)
    }else{
      (for(i<-0 until in.size-2;
          x<-apply_opps(in(i), in(i+1)))yield{
        import Array._
        execute(concat(in.slice(0,i), Array(x), in.slice(i+2,in.size)))
      }).exists(_==true)
    }
  }
}