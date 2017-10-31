package com.example.demo.conf;

import com.example.demo.domain.Book;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteBuilder demoRoute(final JAXBContext jaxbContext) throws JAXBException {

        return new RouteBuilder() {
            public void configure() {
                DataFormat jaxb = new JaxbDataFormat(jaxbContext);
                from("file:.\\src\\main\\resources?fileName=book.xml&noop=true")
                        .split().tokenizeXML("book").streaming()
                        //.aggregate(header(Exchange.FILE_NAME_ONLY), new StringBodyAggregator()).completionSize(2).completionTimeout(1500)
                        .unmarshal(jaxb)
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                Book obj = (Book) exchange.getIn().getBody();
                                System.out.println(obj.toString());
                                //System.out.println(exchange.getIn().getBody(String.class));
                                System.out.println("#######################################");
                            }
                        })
                        .end()
                        .to("bean:SomeService?method=doOPrint");


//                from("quartz2://myTimer?trigger.repeatInterval=2000&trigger.repeatCount=-1")
//                        .process(new Processor() {
//                            @Override
//                            public void process(Exchange exchange) throws Exception {
//                                System.out.println("Processed");
//                            }
//                        });
//                from("direct:start1")
//                        .log("Hello, ${body}!");

//                from("file:D:\\work\\camel\\data?fileName=book.xml")
//                        .convertBodyTo(String.class)
//                        .process(new Processor() {
//                            public void process(Exchange exchange) throws Exception {
//                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");
//                                System.out.println(exchange.getIn().getBody());
//                        // do some business logic with the input body
//                    }
//                });
            }
        };
    }
}
