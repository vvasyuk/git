package com.example.demo.conf;

import com.example.demo.domain.Book2;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.List;


/**
 * Created by Jopa on 11/5/2017.
 */
@Configuration
public class AAARoute {

    @Autowired
    CamelContext camelContext;

    @Bean
    public RouteBuilder demoRoute(@Autowired DataFormat jaxb) throws JAXBException {

        return new RouteBuilder() {
            public void configure(){

                from(   "file:.\\src\\main\\resources?antInclude=book_test_*.xml"
                        //,"file:.\\src\\main\\resources?antInclude=book_test1_*.xml"
                )
                        .to("direct:fileCounter")
                        .split().tokenizeXML("book").streaming()
                        .unmarshal(jaxb)
//                        .process((exchange) -> {
//                            List<Book2> bookList = (List<Book2>) exchange.getIn().getBody();
//                            bookList.forEach(x -> System.out.println(x));
//                            System.out.println("#######################################");
//                            }
//                        )
                        .end();
//                        .to("direct:end");

                from("direct:fileCounter")
                        .process((exchange) -> {
                                    Integer cnt = ThreadAttributes.get("cnt");
                                    cnt=cnt==null?1:cnt+1;
                                    ThreadAttributes.set("cnt", cnt); //to set an attribute
                                    System.out.println(Thread.currentThread() + " - " + cnt +  " - " + exchange.getIn().getHeader("CamelFileNameOnly"));
                                }
                        );

                from("direct:end").routeId("endRoute")
                        .choice()
                            .when(header("fileCount").isEqualTo(header("totalNumber")))
                                .to("bean:SomeService?method=doOPrint")
                        .end();

                from("direct:process")
                        .unmarshal(jaxb)
                        .aggregate(header(Exchange.FILE_NAME_ONLY), new ArrayListAggregationStrategy()).completionSize(15).completionPredicate(header("CamelSplitComplete")).eagerCheckCompletion()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                List<Book2> bookList = (List<Book2>) exchange.getIn().getBody();
                                bookList.forEach(x -> System.out.println(x));
                                System.out.println("#######################################");
                            }
                        });
            }
        };
    }
}

