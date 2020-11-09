package abook_fp_2

object n_2_2_isSorted {

  def main(args: Array[String]): Unit = {
    val l = Array(1,2,3,4,5,6)
    println(isSorted[Int](l, (a: Int,b: Int) => a<b))

  }

  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def loop(a: Int, b: Int, as: Array[A]): Boolean = {
      b match {
        case i if i == as.size => true
        case i if ordered(as(a), as(b)) => loop(b, b+1, as)
        case _ => false
      }
    }
    loop(0, 1, as)
  }
}
