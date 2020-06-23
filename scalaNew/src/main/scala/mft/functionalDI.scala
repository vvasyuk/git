package mft

object functionalDI {
//  create methods return function which needs connection and returns what is needed
//  def createUserFunc   (u: User ): Connection => Int = ???
//  def createAccountFunc(a: Account): Connection => Int = ???

  class Connection
  case class User(id: Option[Int], name: String)
  case class Account(id: Option[Int], ownerId: Int, balance: Double)

  case class Reader[A, B](f: A => B) {
    def apply(a: A): B = {
      println("inside")
      f(a)
    }
    def flatMap[C](f2: B => Reader[A, C]): Reader[A, C] =
      Reader { a => f2(f(a))(a) }
  }

  val x = createUserReader(User(Some(1), "name"))

  def createUserReader   (u: User ): Reader[Connection, Int] = Reader { _ => 0 }
  def createAccountReader(a: Account): Reader[Connection, Int] = Reader { _ => 1 }


  def registerNewUserReader(name: String): Reader[Connection, Int] =
    createUserReader(User(None, name)).flatMap { uid =>
      createAccountReader(Account(None, uid, 0)) }
// or
//def registerNewUserFunc(name: String): Connection => Int = { c:  Connection =>
//  val uid   = createUserFunc(User(None, name))(c)
//  val accId = createAccountFunc(Account(None, uid, 0))(c)
//  accId
//}
  def main(args: Array[String]): Unit = {
    val r: Reader[Int, Int] = Reader { x => x+1 }
    r(1)
  }
}
