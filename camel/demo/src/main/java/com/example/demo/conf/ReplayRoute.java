package com.example.demo.conf;

import com.example.demo.domain.Book2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by Jopa on 11/11/2017.
 */
@Configuration
@Profile("replayMode")
public class ReplayRoute {

    @Bean
    public RouteBuilder demoRoute(@Autowired DataFormat jaxb) throws JAXBException {

        return new RouteBuilder() {
            public void configure() {

                from("timer://runOnce?repeatCount=1").routeId("replayRoute")
                        .onCompletion()
                            .to("direct:shutdown")
                        .end()
                        .pollEnrich("file:.\\src\\main\\resources?fileName=book_test_001_003.xml")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println(exchange.getIn().getHeader("CamelFileNameOnly"));
                                System.out.println("#######################################");
                            }
                        });
                        //.to("direct:process");

                from("direct:process").routeId("processRoute")
                        .unmarshal(jaxb)
                        .aggregate(header(Exchange.FILE_NAME_ONLY), new ArrayListAggregationStrategy()).completionSize(15).completionPredicate(header("CamelSplitComplete")).eagerCheckCompletion()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                List<Book2> bookList = (List<Book2>) exchange.getIn().getBody();
                                bookList.forEach(x -> System.out.println(x));
                                System.out.println("#######################################");
                            }
                        });

                from("direct:shutdown").routeId("shutdownRoute")
                        .process((exchange) -> {
                            new Thread(() -> {
                                try {
                                    getContext().stopRoute("replayRoute");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.exit(0);
                            }).start();
                        })
                        .end();
            }
        };
    }
}
