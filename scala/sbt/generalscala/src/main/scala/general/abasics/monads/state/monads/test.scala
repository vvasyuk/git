package monads

object test{
  def apply(s: String)={
    println(s)
  }
  def useApply(s:String): Unit ={
    apply(_)
  }

  def main(args: Array[String]): Unit = {
    useApply("this is the end")
  }
}
