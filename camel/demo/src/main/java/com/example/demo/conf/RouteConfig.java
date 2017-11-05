package com.example.demo.conf;

import com.example.demo.domain.Book;
import com.example.demo.domain.Book2;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteBuilder demoRoute() throws JAXBException {

        return new RouteBuilder() {
            public void configure() throws JAXBException {

                JAXBContext jaxbContext2 = JAXBContext.newInstance(Book2.class);
                DataFormat jaxb = new JaxbDataFormat(jaxbContext2);
                from("file:.\\src\\main\\resources?antInclude=book*.xml")
                        .log("${header.CamelFileNameOnly}")
                        .setHeader("currentNumber", regexReplaceAll(header("CamelFileNameOnly"), ".*_.*_(.*)_.*", "$1"))
                        .setHeader("totalNumber", regexReplaceAll(header("CamelFileNameOnly"), ".*_.*_.*_(.*)\\.xml", "$1"))
                        .log("${header.currentNumber}")
                        .log("${header.totalNumber}")
                        .split().tokenizeXML("book").streaming()
                            //.log("${header.CamelSplitComplete}")
                            .to("direct-vm:process")
                        .end()
                        .to("direct-vm:end");

                from("direct-vm:end")
                    .to("bean:SomeService?method=doOPrint");

                from("direct-vm:process")
                        .unmarshal(jaxb)
                        .aggregate(header(Exchange.FILE_NAME_ONLY), new ArrayListAggregationStrategy()).completionSize(15).completionPredicate(header("CamelSplitComplete")).eagerCheckCompletion()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                List<Book2> bookList = (List<Book2>) exchange.getIn().getBody();
                                bookList.forEach(x -> System.out.println(x));
                                System.out.println("#######################################");
                            }
                        });
                        //.end();

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
//                                Book2 obj = (Book2) exchange.getIn().getBody();
//                                Book obj = (Book) exchange.getIn().getBody();
//                                System.out.println(obj.toString());
//                                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");
//                                System.out.println(exchange.getIn().getBody());
//                        // do some business logic with the input body
//                    }
//                });
            }
        };
    }
}
