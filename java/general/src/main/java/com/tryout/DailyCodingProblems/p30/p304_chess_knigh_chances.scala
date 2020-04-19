package com.tryout.DailyCodingProblems.p30

import scala.collection.mutable

object p304_chess_knigh_chances {
  def getMoves(x: Int, y: Int) = {
    val moves = Array((1, 2), (2, 1), (2, -1), (1, -2), (-1, -2), (-2, -1), (-2, 1), (-1, 2))
    for(move<-moves)yield{
      val(a,b)=move
      (x+a, y+b)
    }
  }

  def onBoard(pos: (Int, Int)): Boolean = {
    val (x,y) = pos
    x>=0 && x<=7 && y>=0 && y<=7
  }

  def getProbability(x: Int, y: Int, k: Int, memo: mutable.Map[(Int, Int, Int), Double]): Double = {
    if(memo.contains(x,y,k)){
      return memo(x,y,k)
    }

    if(k==0){
      return if (onBoard(x,y)) 1 else 0
    }
    if(!onBoard(x,y)){
      return 0
    }
    val jumps =  getMoves(x,y)
    val res = for(j<-jumps)yield{
      val (x,y) = j
      getProbability(x,y,k-1,memo)
    }
    memo.put((x,y,k), 0.125 * res.sum)
    memo(x,y,k)
  }

  def main(args: Array[String]): Unit = {

    val moves = getMoves(0,0)
    moves.foreach(pos=>println("pos: " + pos + " on board: " + onBoard(pos)))

    val memo = mutable.Map[(Int,Int,Int),Double]()
    println(getProbability(0,0,1,memo))

  }
}