package rest

import org.scalatest.FlatSpec
import org.scalamock.scalatest.MockFactory


class ScalaTestMock extends FlatSpec with MockFactory {

  "Mock Function" should "be 42" in {
    val m = mockFunction[Int, String]
    m.expects(42).returning("Forty two").once
    assert(m(42) == "Forty two")
  }

  "Mock Trait" should "be Colombia" in {
    val m = mock[PlayerBase]
    (m.country _).expects() returning ("Colombia")
    assert(m.country == "Colombia")
  }

  "Mock case class method" should "be Colombia" in {
    class MockablePlayer extends Player(0, null, null)
    val m = mock[MockablePlayer]
    (m.meth _).expects() returning ("meth")
    assert(m.meth == "meth")
  }

  "Mock object method" should "Player1" in {
    val m = mock[PlayerTrait]
    (m.methodA _).expects() returning ("meth")
    assert(m.methodA == "meth")
  }
}
case class Player(id: Int, nickname: String, country: String){
  def meth() = "qaz"
}
trait PlayerBase{
  def id
  def nickname
  def country:String
}

trait PlayerTrait {
  def methodA():String
  def methodB():String
}

object PlayerObj extends PlayerTrait {
  def methodA() = {"qwert"}
  def methodB() = {"qwert"}
}



//val httpClient = mock[HttpClient]
//val counterMock = mock[Counter]
//
//inAnyOrder {
//  (httpClient.sendRequest _).expects(Method.GET, *, *).twice
//  (httpClient.sendRequest _).expects(Method.POST, "http://scalamock.org", *).noMoreThanOnce
//  (httpClient.sendRequest _).expects(Method.POST, "http://example.com", *).returning(Http.NotFound)
//  inSequence {
//  (counterMock.increment _) expects(*) onCall { arg: Int => arg + 1}
//  (counterMock.decrement _) expects(*) onCall { throw new RuntimeException() }
//}
//}