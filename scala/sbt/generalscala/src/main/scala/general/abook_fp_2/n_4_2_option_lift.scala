package general.abook_fp_2

object n_4_2_option_lift {
  def main(args: Array[String]): Unit = {
    val present:myOption[String] = Some("str")
    val absent:myOption[String] = None


    val fLifted = lift(f)

    val res = fLifted(present)
    //res.map(x=>println("present: " + x))


  }

  def lift[A,B](f: A => B): myOption[A] => myOption[B] =  _ map f
  //def lift[A,B](f: A => B): myOption[A] => myOption[B] = (opt: myOption[A]) => opt map f
  def f(in:String):String=in + "a"
  }


