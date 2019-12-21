package com.tryout.DailyCodingProblems.p20

object n205_next_higher_permutation {
  def execute(arr: Array[Int]): Unit = {
    var n = arr.size
    var tailStart = n-1

    while (tailStart!=0 && arr(tailStart-1) > arr(tailStart)){
      tailStart-=1
    }

    if(tailStart==0) println("no such permutations")

    var swap = tailStart
    while(swap < n && arr(tailStart-1) < arr(swap)){
      swap+=1
    }
    swap-=1

    arr.foreach(print(_))
    val tmp = arr(tailStart-1)
    arr(tailStart-1)=arr(swap)
    arr(swap)=tmp

    while (tailStart<n-1){
      val tmp = arr(tailStart)
      arr(tailStart) = arr(n-1)
      arr(n-1)=tmp
      tailStart+=1
      n-=1
    }

    println()
    arr.foreach(print(_))
  }

  def main(args: Array[String]): Unit = {
    execute(Array(4,8,9,7,5))
  }
}
