package general.abook_handsOn

object exsz_1_flexibleFizzBuzz {
  def main(args: Array[String]): Unit = {
    flexibleFizzBuzz(s => ())

    var i = 0
    val output = new Array[String](100)
    flexibleFizzBuzz{s =>
      output(i) = s
      i += 1
    }
    println(output.mkString(","))
  }

  def flexibleFizzBuzz(f: String => Unit) = {
    for (i<-Range(0,100)){
      if (i % 3 == 0 && i % 5 == 0) f("FizzBuzz")
      else if (i % 3 == 0) f("Fizz")
      else if (i % 5 == 0) f("Buzz")
      else f(i.toString)
    }
  }
}
