//package rest
//
//import org.scalatest.{FlatSpec, GivenWhenThen}
//import scalaj.http.Http
//import spark.Spark.{awaitInitialization, get}
//
//class ApiServiceTest extends FlatSpec with GivenWhenThen {
//
//  "Mock remote rest api" should "be ok" in {
//    Given("a rest api, mocked remote rest")
//    MockRemoteAPI.initRoutes()
//    Api.initRoutes(RemoteService)
//    awaitInitialization
//
//    When("when we make http request")
//    val res = Http("http://localhost:4567/22").asString
//    Then("we get mocked result")
//    assert("mocked remote service".equals(res.body))
//  }
//
//}
//
//object MockRemoteAPI{
//  def initRoutes(): Unit ={
//    get("/bin/8023d488-a89a-45db-aba6-502414a9c523", (req, res) => {
//      "mocked remote service"
//    })
//  }
//}
//
//object Conf{
//  val host = "http://localhost:4567"
//}