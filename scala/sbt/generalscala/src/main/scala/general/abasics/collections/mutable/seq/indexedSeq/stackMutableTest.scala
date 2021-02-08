package general.abasics.collections.mutable.seq.indexedSeq

import scala.collection.mutable.Stack

// instead of mutable stack - use list :: and head methods
// there is also ArrayStack which maybe be faster
object stackMutableTest {
  def main(args: Array[String]): Unit = {
    // Builders
    assert(Stack(1, 2, 3) == Stack(1, 2, 3))
    Stack[Int]() // empty vector
    assert(Stack.range(1, 4) == Vector(1, 2, 3))
    val s1 = Stack(1, 2, 3)

    // Modify
    assert(s1 == Stack(1, 2, 3))
    val x = s1.pop();
    assert(x == 1);
    assert(s1 == Stack(2, 3))
    s1.push(1);
    assert(s1 == Stack(1, 2, 3))
    s1.push(-2, -1, 0);
    assert(s1 == Stack(0, -1, -2, 1, 2, 3))
    s1.popWhile(_ < 0);
    assert(s1 == Stack(0, -1, -2, 1, 2, 3))
    s1.popWhile(_ < 1);
    assert(s1 == Stack(1, 2, 3))
    assert(s1.top == 1);
    assert(s1 == Stack(1, 2, 3))

    // vector specific


    // Factory Methods
    assert(Stack.fill(3)(7) == Stack(7, 7, 7))
    assert(Stack.tabulate(3)(n => s"hi$n") == Stack("hi0", "hi1", "hi2"))
    assert(Stack(1, 2) ++ Stack(3, 4) == Stack(1, 2, 3, 4))

    // Transforms
    assert(Stack(1, 2, 3).map(_ * 2) == Stack(2, 4, 6))
    assert(Stack(1, 2, 3).filter(_ % 2 == 1) == Stack(1, 3))
    assert(Stack(1, 2, 3).take(2) == Stack(1, 2))
    assert(Stack(1, 2, 3).drop(2) == Stack(3))
    assert(Stack(1, 2, 3).slice(0, 2) == Stack(1, 2))
    assert(Stack(1, 2, 2, 3).distinct == Stack(1, 2, 3))
    assert(Stack("apple", "pear").flatMap(_.toUpperCase()) == Stack('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(Stack(1, 3, 2).sorted == Stack(1, 2, 3))
    assert(Stack(1, 3, 2).sortWith(_ < _) == Stack(1, 2, 3))
    assert(Stack(1, 3, 2).sortWith(_ > _) == Stack(3, 2, 1))
    assert(Stack(1, 2).zip(Stack(3, 4)) == Stack((1, 3), (2, 4)))
    assert(Stack(11, 22, 33).zipWithIndex == Stack((11, 0), (22, 1), (33, 2)))


    // Queries
    assert(Stack(1, 2, 3).find(_ == 2) == Some(2))
    assert(Stack(1, 2, 3).exists(_ == 2) == true)

    // Aggregations
    assert(Stack(1, 2, 3).mkString(",") == "1,2,3")
    assert(Stack(1, 2, 3).fold(0)(_ + _) == 6) // fold can only aggregate into elements of subtype of original collections
    assert(Stack(1, 2, 3).foldLeft(0)(_ + _) == 6)
    assert(Stack(1, 2, 3).reduce(_ + _) == 6)
    assert(Stack(1, 2, 3).groupBy(_ % 2) == Map(0 -> Stack(2), 1 -> Stack(1, 3)))
  }
}
