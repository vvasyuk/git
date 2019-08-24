package coursera.w4

object MainList {
  def isort(xs: List[Int]):List[Int]= {
    xs match {
      case List() => List()
      case y :: ys => insert(y, isort(ys))
    }
  }

  def insert(x:Int, xs: List[Int]):List[Int]= {
    xs match {
      case List() => List(x)
      case y :: ys => if (x<=y) x::xs else y::insert(x,ys)
    }
  }

  def main(args: Array[String]): Unit = {
    val l1 = 3::2::1::4::Nil
    println(l1)
    println(isort(l1))
  }
}
