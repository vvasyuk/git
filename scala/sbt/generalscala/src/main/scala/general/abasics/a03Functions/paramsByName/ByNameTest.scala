package general.abasics.a03Functions.paramsByName

object ByNameTest {
  def main(args: Array[String]): Unit = {
    myAssert(() => 1<5)
    //myAssert( 1<5)
    byNameAssert(1<5)
  }

  def myAssert(predicate: () => Boolean) = {
    if (!predicate()) throw new AssertionError()
  }

  def byNameAssert(predicate: => Boolean) = {
    if (!predicate) throw new AssertionError()
  }


}
