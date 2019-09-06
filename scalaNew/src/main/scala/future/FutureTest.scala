package future

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object FutureTest {
  def main(args: Array[String]): Unit = {
    //simpleFuture
    //futureCallback
    // this does not block
//    returnFuture(11).onComplete {
//      case Success(result) => println(s"result = $result")
//      case Failure(e) => e.printStackTrace
//    }
//    multipleFutures
    listFutures

    Thread.sleep(2000)
  }

  def simpleFuture(): Unit ={
    // used by 'time' method
    implicit val baseTime = System.currentTimeMillis

    // 2 - create a Future
    val f = Future {
      Thread.sleep(500)
      1 + 1
    }

    // 3 - this is blocking (blocking is bad)
    val result = Await.result(f, 1 second)
    println(result)
    Thread.sleep(1000)
  }
  def futureCallback(): Unit ={
    val f = Future {
      Thread.sleep(500)
      1 + 1
    }
    println("before onComplete")
    f.onComplete {
      case Success(value) => println(s"Got the callback, meaning = $value")
      case Failure(e) => e.printStackTrace
    }
    // do the rest of your work
    println("A ..."); Thread.sleep(100)
    println("B ..."); Thread.sleep(100)
    println("C ..."); Thread.sleep(100)
    println("D ..."); Thread.sleep(100)
    println("E ..."); Thread.sleep(100)
    println("F ..."); Thread.sleep(100)
    Thread.sleep(2000)
  }

  def returnFuture(i: Int): Future[Int] = Future {
    Thread.sleep(100)
    i + 1
  }

  def multipleFutures(): Unit ={
    val result1 = returnFuture(1)
    val result2 = returnFuture(2)
    val result3 = returnFuture(3)

    println("before for-comprehension")
    val result = for {
      r1 <- result1
      r2 <- result2
      r3 <- result3
    } yield (r1 + r2 + r3)

    println("after for")
    result onSuccess {
      case result => println(s"total = $result")
    }
  }

  def listFutures(): Unit ={
    val l = List(1,2,3,4,5)

    val futureMap = l.map(x=>(x,returnFuture(x)))

    futureMap.foreach(x=>{
      Await.result(x._2, Duration.Inf)
      println("_1: " + x._1 + " _2: " + x._2)
    })

  }
}
