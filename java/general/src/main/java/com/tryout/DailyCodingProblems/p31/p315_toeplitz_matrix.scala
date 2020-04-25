package com.tryout.DailyCodingProblems.p31

// In linear algebra, a Toeplitz matrix is one in which the elements on any given diagonal from top left to bottom right are identical.
//
//Here is an example:
//
//1 2 3 4 8
//5 1 2 3 4
//4 5 1 2 3
//7 4 5 1 2
//Write a program to determine whether a given input is a Toeplitz matrix.
object p315_toeplitz_matrix {

  def identity_diag_root(row: Int, col: Int):(Int,Int) = {
    val r = if(row-col>0) row-col else 0
    val c = if (r==0) col-row else 0
    (r,c)
  }

  def main(args: Array[String]): Unit = {
    val in = Array(
      Array(1, 2, 3, 4, 8), //(0,0), (0,1), (0,2), (0,3), (0,4)
      Array(5, 1, 2, 3, 4), //(1,0), (1,1), (1,2), (1,3), (1,4)
      Array(4, 5, 1, 2, 3), //(2,0), (2,1), (2,2), (2,3), (2,4)
      Array(7, 4, 5, 1, 2)  //(3,0), (3,1), (3,2), (3,3), (3,4)
    )
    val res = for(row<-1 until in.size;
        col<-1 until in(row).size)yield{
      val root = identity_diag_root(row,col)
      //println(s"$row/$col: $root")
      in(row)(col)==in(root._1)(root._2)
    }
    println(res.forall(x=>x==true))
  }
}
