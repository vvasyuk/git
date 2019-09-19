package rest

import org.mockito.MockitoSugar._
import org.mockito.matchers
import org.scalatest.FlatSpec
import scalaj.http.Http

class ApiMockitoTest extends FlatSpec {

  "Mock 1" should "be ok" in {
    val mockService = mock[RemoteService]
    when(mockService.getFromRemote("https://mockbin.org")).thenReturn("mocked answer")

    Api.initRoutes(mockService)

    val res = Http("http://localhost:4567/22").asString
    print(res)
  }
}
