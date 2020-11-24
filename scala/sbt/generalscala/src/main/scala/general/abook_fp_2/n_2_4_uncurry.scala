package general.abook_fp_2

object n_2_4_uncurry {

  def main(args: Array[String]): Unit = {
    val fSum = (a:Int) => (b:Int) => a+b
    assert(fSum(1)(1)==2)
    val fSumUncurry = uncurry(fSum)
    assert(fSumUncurry(1, 1)==2)
  }

  def uncurry[A,B,C](f: A => B => C): (A, B) => C ={
    (a: A, b: B) => f(a)(b)
  }
}
