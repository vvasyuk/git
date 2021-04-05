package general.abasics.state

object stateZipWithIndex {
  def main(args: Array[String]): Unit = {
    val res = zipWithIndex(List(4,5,6))
    println(res)

    val res2 = zipWithIndex2(List(4,5,6))
    println(res2)
  }

  def zipWithIndex[A](list: List[A]): List[(A,Int)] = {
    def op(el: A, lastIndex: Int) = {
      ((el,lastIndex), lastIndex+1)
    }
    list.foldLeft((List.empty[(A,Int)], 0)){  // initial - (List.empty[(A,Int)], 0)
      case ((l,i),a) =>{
        val (tup, nextI) = op(a,i)
        (tup::l, nextI)
      }
    }
  }._1.reverse

  import cats.data._
  import cats.implicits._

  def zipWithIndex2[A](list: List[A]): List[(A,Int)] = {
    val b = list.traverse { a =>
      for {
        index <- State.get[Int]
        _ <- State.set[Int](index + 1)
      } yield (a, index)
    }

    b.runA(0).value
  }
}
