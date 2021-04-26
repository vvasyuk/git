package general.abasics.apps

object VendingMachineTest {

//  def loop: IO[Unit] = for {
//    _ <- putStrLn("Type something: ")
//    input <- getLine
//    _ <- putStrLn(s"You said '$input'.")
//    _ <- if (input == "quit") IO(Unit) else loop //RECURSE
//  } yield ()
//  loop.run

//  def mainLoop(gameState: GameState, random: Random): IO[Unit] = for {
//    _ <- IO { showPrompt() }
//    userInput <- IO { getUserInput() }
//    _ <- if (userInput == "H" || userInput == "T") for {
//      // this first line is a hack; a for-expression must begin with a generat
//      _ <- IO { println("you said H or T") }
//      coinTossResult = tossCoin(random)
//      newNumFlips = gameState.numFlips + 1
//      newGameState = createNewGameState(userInput, coinTossResult, gameStat
//        _ <- IO { printGameState(printableFlipResult(coinTossResult
//        _ <- mainLoop(newGameState, random)
//      } yield Unit
//      else for {
//      _ <- IO { println("did not enter H or T") }
//    _ <- IO { printGameOver() }
//    _ <- IO { printGameState(gameState) }
//    } yield Unit
//    } yield Unit
//    mainLoop(s, r).unsafeRunSync()

  def main(args: Array[String]): Unit = {
    def session: State = for{
      _ <- insertCoin(10)
      _ <- insertCoin(15)
      _ <- insertCoin(10)
      _ <- givePepsi(1)
      change <- reset
    } yield change

//    def session: State = insertCoin(10)
//      .flatMap(_ =>
//        insertCoin(15)
//          .flatMap(_ =>
//            givePepsi(1)
//              .flatMap(_ =>
//                reset
//                  .map(change => change)
//              )
//          )
//      )

    val initial = VM(0, 1,1)
    val r = session.run(initial)
    println(r)
  }

  case class VM(balance: Int, cola: Int, pepsi: Int)

  case class State(run: VM => (VM, Int)){
    def flatMap(f: Int => State): State = State{ (s:VM) =>
      val (s1, i) = run(s)
      val r1 = f(i)
      val res = r1.run(s1)
      res
    }

    def map(f: Int => Int): State = State{ (s: VM)=>
      val (s1, i) = run(s)
      (s1, f(i))
    }
  }

  def refillPepsiStock(amount: Int): State = ???
  def givePepsi(amount: Int): State = State{ (s:VM)=>
    println("please have a pepsi")
    val newState = s.copy(pepsi = s.pepsi - amount, balance = s.balance - 25)
    (newState, newState.pepsi)
  }
  def insertCoin(amount: Int): State = State{ (s:VM)=>
    val newState = s.copy(balance = s.balance+amount)
    println(s"coin inserted: ${amount}, current balance: ${newState.balance}")
    (newState, newState.balance)
  }
  def reset(): State = State{ (s:VM)=>
    println(s"please have a change: ${s.balance}")
    val newState = s.copy(balance = 0)
    (newState, newState.balance)
  }
  // TODO 1. add refillPepsiStock
  // TODO 2. use seales trait for coins
  // TODO 3. add logic inside session
  // TODO 4. prompt to insert a coin
}
