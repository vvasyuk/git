import scala.collection.mutable

object Hello {
  def main(args: Array[String]): Unit = {
    //keeperWithThreads

    val states = Map("AL" -> "Alabama", "AK" -> "Alaska")
    val huetes = Map("CA" -> "Cali", "NY" -> "NewYork")
    states.foreach(x => println(x._1 + " " + x._2))
    //println(states.contains("AL"))

    val func =
      for {
        a <- "1"
        b <- states
        c <- huetes
      } yield {
        //( key: String) => { v.asInstanceOf[Map[String, String]].contains(key)}
        a
      }

    //func("AK")
    println(func)

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