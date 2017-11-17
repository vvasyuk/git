package com.example.demo.conf;

import com.example.demo.domain.Book2;
import org.apache.camel.CamelContext;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.camel.component.stax.StAXBuilder.stax;


/**
 * Created by Jopa on 11/5/2017.
 */
@Configuration
@Profile("regularMode")
public class AAARoute {

    @Autowired
    CamelContext camelContext;

    @Bean
    public RouteBuilder demoRoute(@Autowired DataFormat jaxb) throws JAXBException {

        return new RouteBuilder() {
            public void configure(){
                Map<String,Boolean> concurrentMap = new ConcurrentHashMap<String,Boolean>();
                from(   "file:.\\src\\main\\resources?antInclude=book_test_*.xml"
                        ,"file:.\\src\\main\\resources?antInclude=book_test1_*.xml"
                )
                        .setHeader("totalNumber", regexReplaceAll(header("CamelFileNameOnly"), "(.*_){3}(.*)\\.xml", "$2"))
                        .setHeader("fileType", regexReplaceAll(header("CamelFileNameOnly"), ".*_(.*)_.*_.*", "$1"))
                        .setHeader("version", xpath("/catalog/head/version/text()[1]"))
                        .log("${header.version}")
//                        .to("direct:fileCounter")

                        .split(stax(Book2.class)).streaming()

//                        .split().tokenizeXML("book").streaming()
//                        .unmarshal(jaxb)
                        .process((exchange) -> {
                            Book2 book = (Book2) exchange.getIn().getBody();
                            System.out.println(book);
                            System.out.println("#######################################");
                            }
                        )
                        .end()
                        .log("the end")
                        .to("direct:end");

                from("direct:fileCounter")
                        .process((exchange) -> {
                                    String fileType = (String) exchange.getIn().getHeader("fileType");
                                    int totalNumber = Integer.parseInt((String) exchange.getIn().getHeader("totalNumber")) ;
                                    concurrentMap.putIfAbsent(fileType, false);

                                    Integer cnt = ThreadAttributes.get("cnt");
                                    cnt=cnt==null?1:cnt+1;
                                    ThreadAttributes.set("cnt", cnt);
                                    System.out.println(Thread.currentThread() + " - " + cnt +  " - " + exchange.getIn().getHeader("CamelFileNameOnly"));

                                    if (totalNumber==cnt){
                                        concurrentMap.put(fileType, true);
                                    }

                                }
                        );

                from("direct:end").routeId("endRoute")
                        .process((exchange) -> {
                                    //concurrentMap.forEach((k,v) -> System.out.println(Thread.currentThread() + " k: " + k + " | v: " + v ));
//                                    if (concurrentMap.values().stream().allMatch(x -> x.equals(true))){
                                        System.out.println("will end");
//                                    }

                                }
                        );

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




