package com.tryout.DailyCodingProblems

object n203_find_min_in_sorted_shifted_arr {


  def execute(arr: Array[Int], start:Int, end:Int): Int = {
    if (start==end){
      arr(start)
    }else{
      val mid = (start+end)/2
      if(arr(mid) >arr(end)){
        execute(arr, mid+1, end)
      }else{
       execute(arr, start, mid)
      }
    }

  }

  def main(args: Array[String]):Unit={
    println("lowest: " +
    execute(Array(5, 7, 10, 3, 4), 0, 4)
    )
  }
}
