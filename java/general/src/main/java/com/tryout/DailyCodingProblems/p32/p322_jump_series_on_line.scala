package com.tryout.DailyCodingProblems.p32

// Starting from 0 on a number line, you would like to make a series of jumps that lead to the integer N.
//
//On the ith jump, you may move exactly i places to the left or right.
//
//Find a path with the fewest number of jumps required to get from 0 to N.
object p322_jump_series_on_line {

  def main(args: Array[String]): Unit = {
    val N = 11
    
    jumpPath(N)
  }

  def jumpPath(N: Int): Unit = {
    var n = N
    var isMegative = false
    if (n<0){
      n = -n
      isMegative = true
    }

    var (k,total) = (0,0)
    while(total<0 || (total >0 && (total-n)%2!=0)){
      k+=1
      total+=k
    }

  }
}
