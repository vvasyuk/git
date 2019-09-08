//package rest
//
//import io.specto.hoverfly.junit.core.Hoverfly
//import io.specto.hoverfly.junit.rule.HoverflyRule
//import org.junit.{After, Before, Test}
//import scalaj.http.{Http, HttpOptions, HttpResponse}
//import spark.route.Routes
//
//class ApiTest {
//
//  @Before
//  def setUp(){
//    //val newRoutes: Routes  = new Routes();
//    //newRoutes.establishRoutes();
//    Api.initRoutes()
//  }
//
//  @After
//  def tearDown()  {
//    //stop();
//  }
//
//  @Test
//  def testGet(){
//    //Thread.sleep(10000)
//    val response: HttpResponse[String] = Http("http://localhost:4567/hello").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString
//    assert("Hello".equals(response.body))
//  }
//
//  @Test
//  def testGet23(){
//    //Thread.sleep(10000)
//    val response: HttpResponse[String] = Http("http://localhost:4567/22").option(HttpOptions.connTimeout(10000)).option(HttpOptions.readTimeout(50000)).asString
//    assert("23".equals(response.body))
//  }
//
//
//}
