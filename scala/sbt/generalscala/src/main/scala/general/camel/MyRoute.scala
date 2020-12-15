package general.camel

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport
import org.apache.camel.ProducerTemplate

//extends RouteBuilderSupport
object MyRoute  {
  def main(args: Array[String]): Unit = {
    val context: CamelContext = new DefaultCamelContext

    val routeBuilder = new RouteBuilder {
      override def configure(): Unit = {
        //from("timer://runOnce?repeatCount=1")
        from("direct:loadBalancer")
          .loadBalance().roundRobin().to("direct:process1", "direct:process2", "direct:process3")

        from("direct:process1")
          .process((exchange: Exchange) => println(s"process1: ${exchange.getIn.getBody()}"))
        from("direct:process2")
          .process((exchange: Exchange) => println(s"process2: ${exchange.getIn.getBody()}"))
        from("direct:process3")
          .process((exchange: Exchange) => println(s"process3: ${exchange.getIn.getBody()}"))
      }
    }
    context.addRoutes(routeBuilder)
    context.start

    val producerTemplate = context.createProducerTemplate
    producerTemplate.sendBody("direct:loadBalancer","<hello>world!</hello>");
    producerTemplate.sendBody("direct:loadBalancer","<hello>world!</hello>");
    producerTemplate.sendBody("direct:loadBalancer","<hello>world!</hello>");

    while (true) {}
  }
}
