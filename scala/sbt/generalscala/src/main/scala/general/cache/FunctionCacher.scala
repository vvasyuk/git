package cache

import java.util.concurrent.ConcurrentHashMap

object FunctionCacher {
  def main(args: Array[String]): Unit = {
    println("hi")

    cacheWrap("f1", ()=>f1("one"))
    cacheWrap("f1", ()=>f1("one"))
    cacheWrap("f2", ()=>f2("one", "two"))
    cacheWrap("f2", ()=>f2("one", "two"))
  }

  def f1(x: String)={
    "f1: " + x
  }
  def f2(x: String, y: String)={
    "f2: " + x + y
  }
  def cacheWrap(fName:String, x: ()=>String)={
    cache.get(fName) match {
      case s: String => println("cache hit"); s
      case null => println("cache miss"); cache.put(fName, x())
    }
  }
}

object cache{
  private var fs = new ConcurrentHashMap[String,String]()
  def get(x:String)=fs.get(x)
  def put(x:String, y: String)=fs.putIfAbsent(x,y)
}
