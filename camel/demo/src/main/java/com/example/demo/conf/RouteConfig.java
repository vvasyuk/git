package com.example.demo.conf;

import com.example.demo.domain.Book;
import com.example.demo.domain.Book2;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.model.config.StreamResequencerConfig;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Jopa on 10/23/2017.
 */
//@Configuration
public class RouteConfig {

    @Autowired
    CamelContext camelContext;

    @Bean
    public RouteBuilder demoRoute() throws JAXBException {

        return new RouteBuilder() {
            public void configure() throws JAXBException {
//                camelContext.setStreamCaching(true);
//                camelContext.getProperties().put(CachedOutputStream.TEMP_DIR, ".\\src\\main\\resources\\cache");
//                camelContext.getProperties().put(CachedOutputStream.THRESHOLD, "1048576");
//                camelContext.getProperties().put(CachedOutputStream.BUFFER_SIZE, "131072");
//                camelContext.getProperties().put(CachedOutputStream.THRESHOLD, "0");

                camelContext.setTracing(true);

                JAXBContext jaxbContext2 = JAXBContext.newInstance(Book2.class);
                DataFormat jaxb = new JaxbDataFormat(jaxbContext2);

                from("file:.\\src\\main\\resources?antInclude=book*.xml")
                        .streamCaching()
                        .log("${header.CamelFileNameOnly}");
//                        .setHeader("currentNumber", regexReplaceAll(header("CamelFileNameOnly"), ".*_.*_(.*)_.*", "$1"))
//                        .setHeader("totalNumber", regexReplaceAll(header("CamelFileNameOnly"), ".*_.*_.*_(.*)\\.xml", "$1"))
//                        .resequence(header("currentNumber")).stream()
//                        .split()
//                        .tokenizeXML("book")
//                        .streaming()
//                        .to("direct-vm:process");
                //body().tokenize('TOKEN')
//                        .process(new Processor() {
//                            public void process(Exchange exchange) throws Exception {
//                                System.out.println(exchange.getIn().getBody());
//                                System.out.println("#######################################");
//                            }
//                        });


//                        .to("direct:order")
//                        .to("direct-vm:split")
//                        .to("direct-vm:end");

                from("direct:order")
                        //.resequence(header("currentNumber")).stream(new StreamResequencerConfig(5000, 4000L))
                        .resequence(header("currentNumber")).stream()
                        .to("direct:split");
                from("direct:split")
//                        .process(new Processor() {
//                            public void process(Exchange exchange) throws Exception {
//                                System.out.println("#######################################");
//                            }
//                        })

                        .split().tokenizeXML("book").streaming().to("direct-vm:process");

                from("direct-vm:end")
                    .log("${header.currentNumber}")
                    .log("${header.totalNumber}")
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


//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        exchange.getIn().getBody();
//                    }
//                })

//                .process(new Processor() {
//                    Long lastSeq;
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        Long seqnum = exchange.getIn().getHeader("seqnum", Long.class);
//                        if (lastSeq == null) {
//                            lastSeq = seqnum;
//                        } else {
//                            if (seqnum != lastSeq + 1) {
//                                throw new IllegalStateException("Skipped sequence number between " + lastSeq + " and " + seqnum);
//                            }
//                            lastSeq = seqnum;
//                        }
//                    }
//                })

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
