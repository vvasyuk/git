package general.abasics.collections.mutable
import scala.collection.mutable.Queue

object queueMutableTest {
  def main(args: Array[String]): Unit = {
    // Builders
    val q1 = Queue(1, 2, 3)

    // Modify
    println(q1.dequeue())
    println(q1.dequeue())
    q1+=4
    println(q1.dequeue())
    println(q1.dequeue())
  }
}
