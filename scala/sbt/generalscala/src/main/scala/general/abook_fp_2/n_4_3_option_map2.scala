package general.abook_fp_2

object n_4_3_option_map2 {
  def main(args: Array[String]): Unit = {
    val a:myOption[Int] = Some(1)
    val b:myOption[Int] = Some(2)

    val res = map2(a,b)(insuranceRateQuote)
    println(res)

  }

  def map2[A,B,C](a: myOption[A], b: myOption[B])(f: (A, B) => C): myOption[C] ={
    a flatMap (aa => b map (bb => f(aa, bb)))
  }

  def map2_2[A,B,C](a: myOption[A], b: myOption[B])(f: (A, B) => C): myOption[C] ={
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  }



  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double = {
    age+numberOfSpeedingTickets
  }
}
