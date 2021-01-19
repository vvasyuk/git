package general.abook_handsOn

object a6_6_exsz_3_dfs {
  def searchPathsBFS[T](start: T, graph: Map[T, Seq[T]]): Map[T, List[T]] = {
    val seen = collection.mutable.Map(start -> List(start))
    val queue = collection.mutable.ArrayDeque(start -> List(start))
    while (queue.nonEmpty) {
      val (current, path) = queue.removeHead()
      for (next <- graph(current) if !seen.contains(next)) {
        println(next)
        val newPath = next :: path
        seen(next) = newPath
        queue.append((next, newPath))
      }
    }
    seen.toMap
  }

  def shortestPath[T](start: T, dest: T, graph: Map[T, Seq[T]]): Seq[T] = {
    val shortestReversedPaths = searchPathsBFS(start, graph)
    shortestReversedPaths(dest).reverse
  }

  def depthSearchPathsDFS[T](start: T, graph: Map[T, Seq[T]]): Map[T, List[T]] = {
    val seen = collection.mutable.Map(start -> List(start))
    val pathLengths = collection.mutable.Map(start -> 0)
    val queue = collection.mutable.ArrayDeque((start, List(start), 0))
    while (queue.nonEmpty) {
      val (current, path, pathLength) = queue.removeLast()
      for {
        next <- graph.getOrElse(current, Nil)
        if !seen.contains(next) && !pathLengths.get(next).exists(_ <= pathLength + 1)
      } {
        println(next)
        val newPath = next :: path
        seen(next) = newPath
        pathLengths(next) = pathLength + 1
        queue.append((next, newPath, pathLength + 1))
      }
    }
    seen.toMap
  }

  def shortestPathDFS[T](start: T, dest: T, graph: Map[T, Seq[T]]): Seq[T] = {
    val shortestReversedPaths = depthSearchPathsDFS(start, graph)
    shortestReversedPaths(dest).reverse
  }

  def main(args: Array[String]): Unit = {
    val res = depthSearchPathsDFS("a",
      graph = Map(
        "a" -> Seq("b", "c"),
        "b" -> Seq("c", "d", "e"),
        "c" -> Seq("d"),
        "d" -> Seq("f")
      )
    )
    println(res)
  }
}
