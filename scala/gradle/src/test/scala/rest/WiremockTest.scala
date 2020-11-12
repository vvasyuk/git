package rest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import org.scalatest.{BeforeAndAfterEach, FlatSpec}
import scalaj.http.Http


class WiremockTest extends FlatSpec with BeforeAndAfterEach {
  private val port = 8080
  private val hostname = "localhost"
  // Run wiremock server on local machine with specified port.
  private val wireMockServer = new WireMockServer(port)
  override def beforeEach {
    wireMockServer.start()
  }

  override def afterEach {
    wireMockServer.stop()
  }

  val response = "some answer"
  "Client" should "give reply" in {
    stubFor(get(urlMatching("/22")).atPriority(10)
      .willReturn(aResponse().proxiedFrom("http://localhost:8080/23")));

    stubFor(
      get(urlEqualTo("/23"))
        .willReturn(aResponse()
          .withHeader("Content-Type", "text/plain")
          .withBody("some response")
          .withStatus(200)))

    val resp = Http("http://localhost:8080/22").asString
    println(resp.body)

  }
}
