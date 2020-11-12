package abook_fp_2

object n_2_3_currying {

  def main(args: Array[String]): Unit = {
    val fSum = (a:Int, b:Int) => a+b
    val partial1 = curry(fSum)
    assert(fSum(1,1) == 2)
    assert(partial1(1)(1) == 2)
    val partial2 = partial1(1)
    assert(partial2(1) == 2)
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    (a:A) => (b:B) => f(a,b)
  }
}
