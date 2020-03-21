package com.tryout.DailyCodingProblems.p27

import scala.Int
import scala.collection.mutable

object n274_eval_string {

  def main(args: Array[String]): Unit = {


    val in = List("1", "-", "(", "2", "+", "3", ")")
    //val in = List("1", "+", "2", "+", "3")
    val ops = mutable.Stack[Char]()
    val values = mutable.Stack[Int]()

    parse(in, ops, values)
    apply(ops, values)

    ops.foreach(println(_))
    values.foreach(println(_))
  }

  def parse(in: List[String], ops: mutable.Stack[Char], values: mutable.Stack[Int]): Unit = {
    in.mkString("").toCharArray.foreach(c=>c match{
      case i if i.isDigit => values.push(i.asDigit)
      case '+' => ops.push(c)
      case '-' => ops.push(c)
      case '(' => ops.push(c)
      case ')' => {
        ops.popWhile(_!='(').foreach(x=>{
          val v1 = values.pop()
          val v2 = values.pop()
          if (x=='+'){
            values.push(v1+v2)
          }else{
            v2-v1
          }
        })
        ops.pop()
      }
      case _ =>
    })
  }

  def apply(ops: mutable.Stack[Char], values: mutable.Stack[Int]) = {
    while(ops.nonEmpty){
      val v1 = values.pop()
      val v2 = values.pop()
      val op = ops.pop()
      if (op=='+'){
        values.append(v1+v2)
      }else{
        values.append(v2-v1)
      }
    }
  }
}
