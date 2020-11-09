package abook_fp_2

object n_2_5_compose {

  def main(args: Array[String]): Unit = {
    val f1 = (a:Int) => a+1
    val f2 = (b:Int) => b+2
    val composed = compose(f1,f2)
    assert(composed(1)==4)
  }

  def compose[A,B,C](f: B => C, g: A => B): A => C ={
    (a: A) => f(g(a))
  }
}
