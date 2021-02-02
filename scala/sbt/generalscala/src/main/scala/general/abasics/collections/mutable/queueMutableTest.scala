package general.abasics.collections.mutable
import scala.collection.mutable.Queue

object queueMutableTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val q1 = Queue(1, 2)

    // Modify
    q1 += 3; q1.dequeue();q1.dequeue(); assert(q1.dequeue() == 3)
    q1 ++= List(1,2); assert(q1.dequeue() == 1); assert(q1.dequeue() == 2)
    q1.enqueue(1);q1.enqueue(2); assert(q1.dequeue() == 1); assert(q1.dequeue() == 2)

    // set specific
    val q3 = Queue[Int]()
    q3 ++= List(1,2,3,4)
    assert(q3.dequeueFirst(_%2 == 0) == Some(2)); assert(q3 == Queue(1,3,4))
    assert(q3.dequeueAll(_%2 == 1) == Seq(1,3)); assert(q3 == Queue(4))

    // Transforms
    val q2 = Queue(1, 2)
    assert(q2.map(_*2) == Queue(2,4)); assert(q2 == Queue(1,2))
    assert(q2.filter(_%2==1) == Queue(1))
    assert(Queue(1,2,3).take(2) == Queue(1,2))
    assert(Queue(1,2,3).drop(2) == Queue(3))
    assert(Queue(1,2,3).slice(0,2) == Queue(1,2))
    assert(Queue(1,2,2,3).distinct == Queue(1,2,3))
    assert(Queue("apple", "pear").flatMap(_.toUpperCase()) == Queue('A', 'P', 'P', 'L', 'E', 'P', 'E', 'A', 'R'))
    assert(Queue(1,3,2).sorted == Queue(1,2,3))
    assert(Queue(1,3,2).sortWith(_ < _) == Queue(1,2,3))
    assert(Queue(1,3,2).sortWith(_ > _) == Queue(3,2,1))
    assert(Queue(1,2).zip(Queue(3,4)) == Queue((1,3), (2,4)))
    assert(Queue(11, 22, 33).zipWithIndex == Queue((11,0),(22,1),(33,2)))

    // Queries
    val q4 = Queue(1, 2, 3, 4)
    assert(q4.find(x => x == 1) == Some(1))
    assert(q4.exists(_ == 1) == true)
    assert(q4.front == 1);assert(q4 == Queue(1,2,3,4))

    // Aggregations
    assert(q4.mkString(",") == "1,2,3,4")
    assert(q4.count(x=>x == 1) == 1)
    assert(q4.foldLeft(0)((a,b) => a+b ) == 10)
    assert(q4.reduce((a,b)=>a+b) == 10)
  }
}
