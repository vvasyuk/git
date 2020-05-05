package com.tryout.DailyCodingProblems.p33

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

// A Boolean formula can be said to be satisfiable if there is a way to assign truth values to each variable such that the entire formula evaluates to true.
//
//For example, suppose we have the following formula, where the symbol ¬ is used to denote negation:
//
//(¬c OR b) AND (b OR c) AND (¬b OR c) AND (¬c OR ¬a)
//
//One way to satisfy this formula would be to let a = False, b = True, and c = True.
//
//This type of formula, with AND statements joining tuples containing exactly one OR, is known as 2-CNF.
//
//Given a 2-CNF formula, find a way to assign truth values to satisfy it, or return False if this is impossible.z ghjcnj
object p330_2_CNF_formula {
  //      ¬B --> C --> ¬A
  //       ∧     ∧
  //       |     |
  //       v     v
  //A --> ¬C --> B
  //  if a is true, we know that c must be false, and then both b and ¬b must be true. But this is impossible! Therefore, a has to be false.

  def main(args: Array[String]): Unit = {
    val g = Map(
      "A" -> Array("¬C"),
      "¬C" -> Array("B", "¬B"),
      "B" -> Array("C"),
      "¬B" -> Array("¬C", "C"),
      "C" -> Array("B", "¬A")
      //"¬A" -> Array("")
    )
    //val stack = topologicalSort(g)
    //stack.popAll().reverse.foreach(println(_))
    //val components = getConnectedComponents(g,stack)
    satisfy(g)
    println()
  }
  def dfs1(v: String, visited: mutable.Set[String], g:  Map[String,Array[String]], stack: mutable.Stack[String]):Unit = {
    visited+=v
    for(neighbor <- g.getOrElse(v, Array()) if !visited.contains(neighbor)){
      dfs1(neighbor, visited, g, stack)
    }
    stack.push(v)
  }

  def topologicalSort(g: Map[String,Array[String]]): mutable.Stack[String] = {
    val visited = mutable.Set[String]()
    val stack = mutable.Stack[String]()

    for(v <- g.keySet if !visited.contains(v)){
      dfs1(v, visited, g, stack)
    }
    stack
  }

  def getTranspose(g: Map[String,Array[String]])={                        // {
    val transpose = mutable.Map[String,ArrayBuffer[String]]()             //    '¬C': ['A', '¬B'],
                                                                          //    'B': ['¬C', 'C'],
    for(k<-g.keys;                                                        //    '¬B': ['¬C'],
        v<-g(k)){                                                         //    'C': ['B', '¬B'],
      transpose(v) = transpose.getOrElse(v,ArrayBuffer[String]())+=k      //    '¬A': ['C']
    }                                                                     //}
    transpose
  }

  def dfs2(node: String, transpose: mutable.Map[String, ArrayBuffer[String]], visited: mutable.Set[String], components: mutable.Map[String, Int], i: Int):Unit = {
    visited+=node
    components(node) = i

    if(transpose.contains(node)) {
      for(n<-transpose(node) if !components.contains(n)){
        dfs2(n,transpose,visited,components,i)
      }
    }
  }

  def getConnectedComponents(g: Map[String,Array[String]], topoSort: mutable.Stack[String])={
    val transpose = getTranspose(g)
    val visited = mutable.Set[String]()
    val components = mutable.Map[String, Int]()
    var i = -1

    for((node,i)<-topoSort.zipWithIndex if !visited.contains(node)){
      dfs2(node,transpose,visited,components,i)
    }
    components
  }

  def negate(x:String)={
    if(x.startsWith("¬")){
      x.substring(1)
    }else{
      "¬" + x
    }
  }

  def satisfy(g: Map[String,Array[String]])={
    val topological = topologicalSort(g)
    val transpose = getTranspose(g)
    val components = getConnectedComponents(g, topological)

    println(components)
    //set([max(v, negate(v), key=lambda x: components[x]) for v in variables])

  }
}

// def dfs1(node, graph, visited, order):
//    visited.add(node)
//
//    for next_node in graph.get(node, []):
//        if next_node not in visited:
//            dfs1(next_node, graph, visited, order)
//
//    order.append(node)
//
//def toposort(graph):
//    order = []
//    visited = set()
//
//    for node in graph:
//        if node not in visited:
//            dfs1(node, graph, visited, order)
//
//    return reversed(order)

// def get_transpose(graph):
//    transpose = defaultdict(list)
//
//    for key, values in graph.items():
//        for v in values:
//            transpose[v].append(key)
//
//    return transpose
//
//def dfs2(node, graph, visited, components, i):
//    visited.add(node)
//
//    components[node] = i
//
//    for next_node in graph.get(node, []):
//        if next_node not in components:
//            dfs2(next_node, graph, visited, components, i)
//
//def get_connected_components(graph, order):
//    transpose = get_transpose(graph)
//    visited = set()
//    components = defaultdict(list)
//    i = -1
//
//    for i, node in enumerate(reversed(order)):
//        if node not in visited:
//            dfs2(node, transpose, visited, components, i)
//
//    return components

// def negate(x):
//    if x[0] == '¬':
//        return x[1:]
//    else:
//        return '¬' + x
//
//def satisfy(variables, *args):
//    graph = defaultdict(list)
//
//    for a, b in args:
//        graph[negate(a)].append(b)
//        graph[negate(b)].append(a)
//
//    order = toposort(dict(graph))
//    transpose = get_transpose(graph)
//    components = get_connected_components(transpose, order)
//
//    if any(components[v] == components[negate(v)] for v in variables):
//        return False
//    else:
//        return set([max(v, negate(v), key=lambda x: components[x]) for v in variables])