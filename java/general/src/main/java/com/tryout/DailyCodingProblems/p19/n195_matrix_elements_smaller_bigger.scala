package com.tryout.DailyCodingProblems.p19

object n195_matrix_elements_smaller_bigger {

  def toRight(arr: Array[Array[Int]], row: Int, col:Int, value:Int):Int={
    var cnt = 0
    Range(0, row).foreach(x=>{
      var y = col+1
      while (x<arr(0).size && value>arr(x)(y)){
        y+=1
        cnt+=1
      }
    })
    cnt
  }

  def toBottom(arr: Array[Array[Int]], row: Int, col:Int, value:Int):Int={
    var cnt = 0
    Range(0, col).foreach(y=>{
      var x = row+1
      while (x<arr.size && value>arr(x)(y)){
        x+=1
        cnt+=1
      }
    })
    cnt
  }

  def execute(arr: Array[Array[Int]], p1: (Int, Int), p2: (Int, Int)): Unit = {
    var cnt = 0

    val a = arr(p1._1)(p1._2)
    val b = arr(p2._1)(p2._2)
    // cnt of smaller rectangle (to the top left from point p1)
//    cnt += (p1._1+1)*(p1._2+1)
//    cnt += toRight(arr, p1._1, p1._2, a)
//    cnt += toBottom(arr, p1._1, p1._2, a)

    var x = 0
    for (y <- Range(0, arr(0).size).reverse){
      while (x<arr.size && a>arr(x)(y)){
        println(arr(x)(y))
        cnt+=y+1
        x+=1
      }
    }

    //Count numbers greater than b
    x = 0
    for (y <- Range(0, arr(0).size).reverse){
      while (x<arr.size && b>arr(x)(y)){
        println(arr(x)(y))
        cnt+=arr(0).size - (y+1)
        x+=1
      }
    }

    println("cnt: " + cnt)
  }

  def main(args: Array[String]):Unit={
    val ar1 = Array(1, 3, 7, 10, 15, 20)
    val ar2 = Array(2, 6, 9, 14, 22, 25)
    val ar3 = Array(3, 8, 10, 15, 25, 30)
    val ar4 = Array(10, 11, 12, 23, 30, 35)
    val ar5 = Array(20, 25, 30, 35, 40, 45)

    val arr = Array(ar1,ar2,ar3,ar4,ar5)
    execute(arr, (1,1), (3,3))
  }
}
