package abook_fp_2

object n_2_1_fib {

  def main(args: Array[String]): Unit = {
    assert(fib(0) == -1)
    assert(fib(1) == 0)
    assert(fib(2) == 1)
    assert(fib(3) == 1)
    assert(fib(4) == 2)
    assert(fib(5) == 3)
  }

  def fib(n: Int): Int = {
    def go(a: Int, b: Int, n: Int): Int = {
      if(n==2){
        b
      }else{
        go(b,a+b,n-1)
      }
    }

    n match {
      case i if i <= 0 => -1
      case 1 => 0
      case 2 => 1
      case _ =>go(0,1,n)
    }
  }
}
