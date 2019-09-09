package rest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.mapping.RequestListener
import com.sun.xml.internal.ws.developer.Serialization
import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterEach
import java.util.concurrent.TimeUnit

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import scalaj.http.Http


class ScalaTestWiremock extends FlatSpec with BeforeAndAfterEach {
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
    val path = "/23"
    stubFor(
      get(urlEqualTo(path))
        .willReturn(aResponse()
          .withHeader("Content-Type", "text/plain")
          .withBody("some response")
          .withStatus(200)))

    val resp = Http("http://localhost:8080/23").asString
    println(resp.body)

  }
}
