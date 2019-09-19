package rest

import org.mockito.Mockito._
import org.scalatest.FlatSpec
import scalaj.http.Http
import spark.Spark.awaitInitialization

class ApiMockitoTest extends FlatSpec {

  "Mock 1" should "be ok" in {
    val mockService = mock(classOf[RemoteService])
    when(mockService.getFromRemote(mockService.host)).thenReturn("mocked answer")

    Api.initRoutes(mockService)
    awaitInitialization

    val res = Http("http://localhost:4567/22").asString
    print(res.body)
  }
}
