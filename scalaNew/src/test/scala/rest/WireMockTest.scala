//package rest
//
//import com.github.tomakehurst.wiremock.WireMockServer
//import org.scalatest.{BeforeAndAfterEach, FlatSpec}
//import org.scalatest._
//
//class WireMockTest extends FlatSpec with BeforeAndAfterEach {
//  private val port = 4567
//  private val hostname = "localhost"
//
//  private val wireMockServer = new WireMockServer(4567)
//
//  override def beforeEach {
//    wireMockServer.start()
//  }
//
//  override def afterEach {
//    wireMockServer.stop()
//  }
//
//  val response = "XYI"
//
//  "Your Client" should {
//    "send proper request" in {
//      val path = s"/v1/some/api"
//      // Configure mock server stub response
//      // json4s is useful to constructing response string if the response is JSON
//      wireMockServer.stubFor(
//        get(urlPathEqualTo(path))
//          .willReturn(aResponse()
//            .withHeader("Content-Type", "application/json")
//            .withBody(Serialization.write(response))
//            .withStatus(200)))
//
//      // Send request by using your HTTP client
//      val ret = youClient.get()
//
//      // Verify the request is valid
//      wireMockServer.verify(
//        getRequestedFor(urlPathEqualTo(path))
//          .withHeader("Content-Type", "application/json"))
//    }
//  }
//
//}
