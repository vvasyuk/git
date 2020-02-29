package com.tryout

object TimeTest {
  def main(args: Array[String]): Unit = {
//    val t = new Time
//    println(t.hour)
//    t.hour=25
//    println(t.hour)
//    val q = Queue(1,2,3)
//    println(q.head)
    val c = new Cell[String]("2")
    println(c.get)

  }
}
trait Queue[T] {
  def head: T
  def tail: Queue[T]
  def enqueue(x: T): Queue[T]
}
object Queue {
  def apply[T](xs: T*): Queue[T] =
    new QueueImpl[T](xs.toList, Nil)
  private class QueueImpl[T](private val leading: List[T],private val trailing: List[T]) extends Queue[T] {
    def mirror =
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else
        this
    def head: T = mirror.leading.head
    def tail: QueueImpl[T] = {
      val q = mirror
      new QueueImpl(q.leading.tail, q.trailing)
    }
    def enqueue(x: T) =
      new QueueImpl(leading, x :: trailing)
  }
}
class Cell[T](init: T) {
  private[this] var current = init
  def get = current
  def set(x: T) = { current = x }
}