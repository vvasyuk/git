package general.abasics.apps

object VendingMachine2Test {

  def insertCoin(s: VM, coin: Coin): VM = s.copy(balance = s.balance + coin.amnt)
  def buyProduct(s: VM, p: Product): VM = ???

  def main(args: Array[String]): Unit = {
    val l: List[Coin] = List[Coin](Penny, Penny, Nickel)
    val sum = l.foldLeft(0)((a,b)=>a + b.amnt)
    println(sum)

    val initial = VM(0, Map(Pepsi -> 3, Cola -> 5))
    val s1 = insertCoin(initial, Dime)
    val s2 = insertCoin(s1, Nickel)
    val s3 = insertCoin(s2, Penny)

    println(s3)
  }

  abstract class Coin(val amnt: Int)
  case object Penny extends Coin(1)
  case object Nickel extends Coin(5)
  case object Dime extends Coin(10)
  case object Quarter extends Coin(25)

  abstract class Product(val price: Int)
  case object Pepsi extends Product(15)
  case object Cola extends Product(10)

  case class VM(balance: Int, products: Map[Product, Int])


}
