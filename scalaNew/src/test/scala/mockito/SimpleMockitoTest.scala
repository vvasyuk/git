package mockito

import org.junit.Test
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import rest.Api
import scalaj.http.{Http, HttpOptions, HttpRequest, HttpResponse}

class SimpleMockitoTest extends MockitoSugar {

  @Test
  def testOne(){
    val mockedList = mock[List[String]]
    when(mockedList(0)).thenReturn("first");
    println(mockedList(0));
  }

  @Test
  def testTwo(){
    Api.initRoutes()
    Thread.sleep(3000)
    //val mockedHttp = mock[Http.type]
    val mockHttpResponse = mock[HttpResponse[String]]

    //when(HttpResponse).thenReturn(mockHttpResponse)
    when(mockHttpResponse.body).thenReturn("xyi")
    //when(Http.apply("http://localhost:4567/hello").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString).thenReturn(mockHttpResponse)
    val res = Http.apply("http://localhost:4567/hello").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString
    println(res.body);
    println("end")
  }
}
