package rest

import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.scalatest.{FlatSpec, GivenWhenThen}
import scalaj.http.Http
import spark.Spark.awaitInitialization

class ApiMockitoTest extends FlatSpec with GivenWhenThen {

  "Mock rest api" should "be ok" in {
    val mockService = mock(classOf[RemoteService])
    when(mockService.getFromRemote(anyString())).thenReturn("mocked answer")

    Given("a rest api")
    Api.initRoutes(mockService)
    awaitInitialization
    When("when we make http request")
    val res = Http("http://localhost:4567/22").asString
    Then("we get mocked result")
    assert("mocked answer".equals(res.body))
  }
}
