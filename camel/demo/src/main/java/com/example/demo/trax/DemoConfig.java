package com.example.demo.trax;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
public class DemoConfig {

    @Bean
    public RouteBuilder demoRoute() {
        return new RouteBuilder() {
            public void configure() {
                from("file:.\\src\\main\\resources\\data?fileName=book.xml")
                        .split().tokenizeXML("book").streaming()
                        .aggregate(header(Exchange.FILE_NAME_ONLY), new StringBodyAggregator()).completionSize(2).completionTimeout(1500)
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println(exchange.getIn().getBody(String.class));
                                System.out.println("#######################################");
                            }
                        })
                        .end();

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
