object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")

    val simpleClass = new SimpleClass(1, null)


    try{
      val caches = Caches.getCaches(simpleClass)
      import caches._
      println("tCache="+tCache)
    } catch {
      case e: Exception => println("exception caught: " + e.getMessage);
    }

    println("End")
  }
}

trait Caches {
  val tCache: String
}

object Caches {
  def getCaches(simpleClass: SimpleClass) = new Caches {
    val tCache: String = initCache(simpleClass)
  }

  def initCache(simpleClass: SimpleClass): String = {

    if(simpleClass.myString==null){
      throw new Exception("myString is null");
    }
    simpleClass.myString
  }
}