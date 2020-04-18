package com.tryout.DailyCodingProblems.p30

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object p302_spacedivide {

  def createGraph(in: Array[Array[String]]) = {
    val g = mutable.Map[(Int,Int), ListBuffer[(Int,Int)]]()

    for(
      row<-0 to in.size-1;
      col<-0 to 5){
      println(row + ":" + col + " - " + in(row)(col))
      in(row)(col) match{
        case "\\" => {
          g.getOrElseUpdate((row,col), ListBuffer[(Int,Int)]())+=((row+1,col+1))
          g.getOrElseUpdate((row,col), ListBuffer[(Int,Int)]())+=((row,col))
        }
        case "/" => {
          g.getOrElseUpdate((row,col+1), ListBuffer[(Int,Int)]())+=((row+1,col))
          g.getOrElseUpdate((row+1,col), ListBuffer[(Int,Int)]())+=((row,col+1))
        }
        case _ =>
      }
    }
    g
  }

  def main(args: Array[String]): Unit = {
    val in = Array(
      Array("\\"," "," "," "," ","/"),
      Array(" ","\\"," "," ","/"," "),
      Array(" "," ","\\","/"," "," ")
    )

    in.foreach(row=>{row.foreach(print(_));println("")})
    val g = createGraph(in)
    println("")
  }
}

//from collections import defaultdict
//
//def create_graph(matrix):
//    graph = defaultdict(list)
//
//    for i, row in enumerate(matrix):
//        for j, col in enumerate(row):
//            if matrix[i][j] == '/':
//                graph[(i, j + 1)].append((i + 1, j))
//                graph[(i + 1, j)].append((i, j + 1))
//            elif matrix[i][j] == '\\':
//                graph[(i, j)].append((i + 1, j + 1))
//                graph[(i + 1, j + 1)].append((i, j))
//
//    return graph
//A = [["\\","","","","","/"]
//     ["","\\","","","/",""],
//     ["","","\\","/","",""]]