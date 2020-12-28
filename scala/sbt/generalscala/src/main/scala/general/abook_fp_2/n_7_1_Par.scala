package general.abook_fp_2

import java.util.concurrent.{Callable, ExecutorService, Executors, Future, LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

object n_7_1_Par {

  def sum(v1: Int,v2: Int): Int = v1+v2

  def main(args: Array[String]): Unit = {
    println("Par")

    val u1 = Par.lazyUnit({println("running2"); sum(1,1)})
    val es = Executors.newFixedThreadPool(10);
    val callableTask1: Callable[String] = new Callable[String] {def call:String = {println("running"); TimeUnit.MILLISECONDS.sleep(300); "Task's execution"}}
    val callableTask2: Callable[Int] = new Callable[Int] {def call:Int = {println("running"); 1+1}}

    val future: Future[String] = es.submit(callableTask1)
    assert(future.get(400, TimeUnit.MILLISECONDS) == "Task's execution")

    val res = Par.run(es)(u1)
    println(res.get())

    es.shutdown()




  }
}
