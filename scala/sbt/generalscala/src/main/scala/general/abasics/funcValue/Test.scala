package funcValue

object Test {
  //https://alvinalexander.com/scala/how-to-use-functions-as-variables-values-in-scala-fp
  def main(args: Array[String]): Unit = {
    val f = (i: Int) => { i % 2 == 0 }
    val f1: Int => Boolean = { _ % 2 == 0 }
    println(f(1))
    println(f1(1))

    val f2: (Int) => Boolean = i => { i % 2 == 0 }
    val f3: Int => Boolean = i => { i % 2 == 0 }
    val f4: Int => Boolean = i => i % 2 == 0
    val f5: Int => Boolean = _ % 2 == 0
  }
}
