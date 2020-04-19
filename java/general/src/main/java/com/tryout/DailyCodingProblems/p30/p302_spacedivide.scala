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
          g.getOrElseUpdate((row+1,col+1), ListBuffer[(Int,Int)]())+=((row,col))
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

  // needs to be tested
  def traverse(v: (Int, Int), g: mutable.Map[(Int, Int), ListBuffer[(Int, Int)]], walls: Set[(Int, Int)], hit_walls: Int, visited: mutable.Set[(Int, Int)], regions: Int): Int = {
    visited.addOne(v)
    var _regions = regions
    var _hit_walls = hit_walls

    for(neighbor<-g(v)){
      if (visited.contains(neighbor)){
        _regions=regions+1
      }else{
        if (walls.contains(neighbor)){
          _hit_walls+=1
          if (_hit_walls>1){
            _regions+=1
          }
        }
        traverse(v,g,walls,_hit_walls,visited,regions)
      }
      _regions
    }

    0
  }

  def getRegions(in: Array[Array[String]]) = {
    val g = createGraph(in)
    val vertices = g.keys.toList

    val (m,n) = (in.size, in(0).size)


    val walls =
      (for(x<-Seq(0,m); y<-0 to n-1)yield{(x,y)}).toSet++
      (for(x<-1 until m; y<-Seq(0,5))yield{(x,y)}).toSet

    walls.foreach(println(_))
    var regions = 0
    val visited = mutable.Set[(Int,Int)]()

    for(v<-vertices){
      val hit_walls = if (walls.contains(v)) 1 else 0
      regions = traverse(v,g,walls, hit_walls, visited, regions)
    }
  }


  def main(args: Array[String]): Unit = {
    val in = Array(
      Array("\\"," "," "," "," ","/"),
      Array(" ","\\"," "," ","/"," "),
      Array(" "," ","\\","/"," "," ")
    )



    in.foreach(row=>{row.foreach(print(_));println("")})
    val res = getRegions(in)
    println("")
  }
}
// def traverse(start, graph, walls, walls_hit, visited, regions):
//    visited.add(start)
//
//    neighbors = graph[start]
//    for neighbor in neighbors:
//        graph[neighbor].remove(start)
//
//    for neighbor in neighbors:
//        if neighbor in visited:
//            regions += 1
//        else:
//            if neighbor in walls:
//                walls_hit += 1
//                if walls_hit > 1:
//                    regions += 1
//            regions, walls_hit = traverse(neighbor, graph, walls, walls_hit, visited, regions)
//
//    return regions, walls_hit


// from itertools import product
//
//def get_regions(matrix):
//    graph = create_graph(matrix)
//    vertices = list(graph.keys())
//
//    m, n = len(matrix), len(matrix[0])
//    walls = set(list(product((0, m), range(n + 1))) + list(product(range(1, m), (0, n))))
//
//    regions = 1
//    visited = set()
//
//    while vertices:
//        start = vertices[0]
//        walls_hit = 1 if start in walls else 0
//        regions, _ = traverse(start, graph, walls, walls_hit, visited, regions)
//        vertices = [v for v in vertices if v not in visited]
//
//    return regions




// defaultdict(<class 'list'>, {
// (0, 0): [(1, 1)],
// (1, 1): [(0, 0), (2, 2)],
// (0, 6): [(1, 5)],
// (1, 5): [(0, 6), (2, 4)],
// (2, 2): [(1, 1), (3, 3)],
// (2, 4): [(1, 5), (3, 3)],
// (3, 3): [(2, 2), (2, 4)]})



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