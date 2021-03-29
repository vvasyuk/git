package general.abasics.apps

object TwoElWrapperTest {

  def main(args: Array[String]): Unit = {
    val result = for{
      fResult <- f(100)
      gResult <- g(fResult)
      hResult <- h(gResult)
    } yield hResult

    val result1 = f(100).flatMap(
      fResult => g(fResult).flatMap(
            gResult => h(gResult).map(
              hResult => hResult)
          )
      )
    println(result)
  }


  def f(a: Int): TwoElWrapper ={
    val result = a*2
    val message = s"\n f: a ($a) * 2 = $result"
    TwoElWrapper(result, message)
  }

  def g(a: Int): TwoElWrapper ={
    val result = a*3
    val message = s"\n f: a ($a) * 3 = $result \n"
    TwoElWrapper(result, message)
  }

  def h(a: Int): TwoElWrapper ={
    val result = a*4
    val message = s"\n f: a ($a) * 4 = $result \n"
    TwoElWrapper(result, message)
  }

  case class TwoElWrapper(v: Int, m: String){
    def map(f: Int => Int): TwoElWrapper = TwoElWrapper(f(v), m)
    def flatMap(f: Int=> TwoElWrapper): TwoElWrapper = {
      val n = f(v)
      TwoElWrapper(n.v, m + n.m)
    }
  }
}
