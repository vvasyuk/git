package com.tryout.spark

import com.tryout.spark.SparkTest.{dummyJob3Alt, getSession}
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest._
import resource.managed

class SparkTestTest extends FlatSpec with BeforeAndAfterEach with Matchers {
  implicit var sparkSession: SparkSession = _

  override def beforeEach(): Unit = {
    import org.apache.log4j.{Level,Logger}
    Logger.getLogger("org").setLevel(Level.ERROR)
    sparkSession = SparkSession
        .builder()
        .appName("SparkTestTest")
        .master("local")
        .getOrCreate()
  }

//  "A test" should "should be ok" in {
//    assert(1 === 1)
//  }

  "A dummyJob3Alt test" should "should be ok" in {
    val files = List("D:\\work\\tryout\\sbt\\data\\in\\a.csv", "D:\\work\\tryout\\sbt\\data\\in\\b.csv", "D:\\work\\tryout\\sbt\\data\\in\\c.csv")
//    val m = mock[PlayerTrait]
//    (m.methodA _).expects() returning ("meth")
    managed(getSession).acquireAndGet(spark => dummyJob3Alt(files))
  }

  "A df comparison test" should "should be ok" in {
    val spark = getSession
    import spark.implicits._
    val df = Seq((1, "bat"), (2, "mouse"), (3, "horse")).toDF("number", "word").collect()
    val correct = List( Row(1,"bat"),Row(2,"mouse"),Row(3,"horse"))

    df shouldBe(correct)
  }

}
