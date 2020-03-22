package com.tryout.DailyCodingProblems.p27

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

// The "look and say" sequence is defined as follows: beginning with the term 1,
// each subsequent term visually describes the digits appearing in the previous term.
// The first few terms are as follows:
//
//1
//11
//21
//1211
//111221
//As an example, the fourth term is 1211, since the third term consists of one 2 and one 1
object n275_say_what_see {

  def rec(in: List[Int], cnt: Int): List[Int] = cnt match{
    case 1 => in
    case _ => rec (genNextRow(in),cnt-1)
  }

  def updateBuffer(buf: ArrayBuffer[Int], h: Int) = buf match{
    case ArrayBuffer() => buf+=1; buf+=h
    case ArrayBuffer(_*) => {
      if (buf.last==h){
        buf.insert(buf.size-2, buf(buf.size-2)+1)
      } else {buf+=1; buf+=h}
    }
  }

  def genNextRowRec(l: List[Int], cnt: Int, buf: ArrayBuffer[Int]): ArrayBuffer[Int] = l match{
    case Nil => buf
    case h :: t => {
      updateBuffer(buf, h)
      genNextRowRec(l.tail, cnt-1, buf)
    }
  }

  def main(args: Array[String]): Unit = {
    val in = List(1)
    //println(genNextRow(List(1,2,1,1)))
    println(genNextRowRec(List(1,2,1,1), 5, ArrayBuffer[Int]()))
    //println(rec(in, 5))
  }

  def genNextRow(in: List[Int]):List[Int]={
    val res = ArrayBuffer[Int]()
    var current =in(0);
    var cnt = 0;

    in.foreach(i=>{
      if (i==current){
        cnt+=1
      }else{
        res.addOne(cnt)
        res.addOne(current)
        cnt=1
        current=i
      }
    })
    res.addOne(cnt)
    res.addOne(current)
    res.toList
  }
}
