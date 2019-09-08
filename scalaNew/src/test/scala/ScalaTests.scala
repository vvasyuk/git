import collection.mutable.Stack
import org.scalatest._

class ScalaTests extends FlatSpec with Matchers {
  "An empty Set" should "have size 0" in {
    "1" should be (2)
  }

  "eee" should "beee" in {
    false
  }

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    //stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}
