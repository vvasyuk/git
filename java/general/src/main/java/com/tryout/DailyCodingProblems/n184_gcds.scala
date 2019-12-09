package com.tryout.DailyCodingProblems

object n184_gcds {

  def execute(seq: Seq[Int]): Unit = {
    //val a = 1
    //val b = 9
    //println(gcd(a,b))
    println(gcdHelper(seq))
  }

  def gcdHelper(seq: Seq[Int]):Int={
    val res = seq.foldLeft(0)((a,e) => gcd(e,a))
    res
  }

  def gcd(x: Int, y: Int):Int = {
    if(y==0){
      x
    }else if (x%y==0){
      y
    }else{
      gcd(y,x%y)
    }
  }

  def main(args: Array[String]): Unit = {
    println("gcds_184 started")
    execute(Seq(42, 56, 14))
  }
}
