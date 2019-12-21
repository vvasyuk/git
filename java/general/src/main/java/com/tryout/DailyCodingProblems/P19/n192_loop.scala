package com.tryout.DailyCodingProblems.P19

object loop{
  def main(args: Array[String]):Unit={
    val arr = Array(1,3,1,2,0,1)
    execute(arr)
  }

  def execute(arr:Array[Int]):Unit={
    var further_so_far = 0

    Range(0, arr.size).foreach(x=>{
      if(x>further_so_far){
        null
      }else{
        further_so_far = Math.max(further_so_far, x+arr(x))
      }
    })

    println(further_so_far >= arr.size-1)
  }
}
