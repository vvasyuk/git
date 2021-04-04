package general.abasics.state

object state5Test {
  def main(args: Array[String]): Unit = {
    val c1 = Car(0)

    val twoStops = fillTank(10).flatMap(_ => fillTank(20))
    val resTank = twoStops.run(c1)
    println(resTank)

    val threeStops = fillTank(10).flatMap(_ => fillTank(20)).map(a=>a)
    val resTank2 = threeStops.run(c1)
    println(resTank2)

    def fourStops = for{
      _ <- fillTank(10)
      _ <- fillTank(20)
      _ <- fillTank(30)
      result <- fillTank(0)
    } yield result
    val fStops = fourStops.run(c1)
    println(fStops)

  }

  case class State(run: Car => (Car, Int)){
    def flatMap(f: Int => State): State = State{ (s: Car)=>
      val (s1, a) = run(s)
      val r1 = f(a)
      val r2 = r1.run(s1)
      r2
    }

    def map(f: Int=>Int):State= State{ (s: Car)=>
      val (c, a) = run(s)
      (c, f(a))
    }
  }

  case class Car(tank: Int)
  def fillTank(litres: Int): State = State{ (s: Car)=>
    val newTank = s.tank + litres
    (Car(newTank), newTank)
  }
}
