import scala.collection.mutable

object Hello {
  def main(args: Array[String]): Unit = {
    //keeperWithThreads

    val states: Either[Int,Map[String, String]] = Right(Map("AL" -> "Alabama", "AK" -> "Alaska"))
    val huetes: Either[Int,Map[String, String]] = Right(Map("CA" -> "Cali", "NY" -> "NewYork"))

    def get(m: Either[Int,Map[String, String]])  = {
      m match{
        case Right(m) => Right(m)
        //case Left(m) => Right(m)
      }
    }

    val func =
      for {
        b <- states
      } yield {
        ( key: String) => { b.contains(key)}
      }

    func("CA")    //Pattern: func: Either[Nothing, String => Boolean]

    //Error:(24, 9) scala.util.Either[Int,String => Boolean] does not take parameters
    //func("CA")    //Pattern: func: Either[Nothing, String => Boolean]

  }

  def keeperWithThreads: Unit = {
    val thread1 = new Thread {
      override def run {
        Thread.sleep(7000)
        Keeper.map(2) = "inited"
      }
    }

    val thread2 = new Thread {
      override def run {
        for (i <- 1 to 10) {
          Thread.sleep(2000)
          try {
            val caches = Caches.getCaches(Keeper.map)
            import caches._
            println("tCache=" + tCache)
          } catch {
            case e: Exception => println("exception caught: " + e.getMessage);
          }
        }
      }
    }

    thread1.start
    thread2.start
  }
}

trait Caches {
  def tCache: String
}

object Caches extends Caches{
  lazy val tCache: String = initCache(Keeper.map)

  def getCaches(m: mutable.Map[Int, String]) = new Caches {
    lazy val tCache: String = initCache(m)
  }

  def initCache(m: mutable.Map[Int, String]): String = {
    if(m(2)==null){
      throw new Exception("myString is null");
    }
    m(2)
  }
}

object Keeper{
  var map = scala.collection.mutable.Map(1 -> "one")
  map += (2 -> null)
}