package com.example.demo.conf;

import com.example.demo.domain.Book2;
import com.example.demo.util.ArrayListAggregationStrategy;
import com.example.demo.util.ThreadAttributes;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jopa on 11/5/2017.
 */
@Configuration
@Profile("regularMode")
public class ServiceBookReaderRoute {
    //@Value("${FILE_URI}") private String fileUri;
    @Autowired
    CamelContext camelContext;
    @Autowired
    @Qualifier("routeConfig")
    Map<String, String> routeConfig;

    @Bean
    public RouteBuilder demoRoute(@Autowired DataFormat jaxb) throws JAXBException {

        return new RouteBuilder() {
            public void configure(){
//                camelContext.setTracing(true);
                Map<String,Boolean> concurrentMap = new ConcurrentHashMap<String,Boolean>();

//                onException(Exception.class)
//                        .maximumRedeliveries(1)
//                        .handled(true);
//                from(   routeConfig.get("TEST_ROUTE_URI"),routeConfig.get("TEST1_ROUTE_URI")).routeId("fileRoute")
//                        .setHeader("seq", header("CamelFileNameOnly").regexReplaceAll(routeConfig.get("SEQ_REGEX"), routeConfig.get("SEQ_REGEX_GROUP")))
//                        .log("${header.seq}")
//                        .to("direct:process")
//                        .end()
//                        .log("the end ${header.seq}")
//                        .to("direct:end");

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
                        .split().tokenizeXML("book").streaming()
                        .unmarshal(jaxb)
                        .aggregate(header(Exchange.FILE_NAME_ONLY), new ArrayListAggregationStrategy()).completionSize(15).completionPredicate(header("CamelSplitComplete")).eagerCheckCompletion()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
//                                List<Book2> bookList = (List<Book2>) exchange.getIn().getBody();
//                                bookList.forEach(x -> System.out.println(x));
//                                System.out.println("#######################################");
                            }
                        });

                from("direct-vm:shutdown").routeId("shutdownRoute")
                        .choice()
                        .when(header("fileCount").isEqualTo(header("totalNumber")))
                        .process((exchange) -> {
//                                    exchange.getContext().getInflightRepository().remove(exchange);
//                                    exchange.getContext().stopRoute("fileRoute");
//                                    getContext().getShutdownStrategy().setTimeout(2);
//                                    System.exit(0);

                            new Thread(() -> {
                                try {
                                    getContext().stopRoute("fileRoute");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.exit(0);
                            }).start();

                        })
                        .end();

                from("quartz2://myTimer?trigger.repeatInterval=2000&trigger.repeatCount=3")
                        .onCompletion()
                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    System.out.println("onCompletion");
                                }
                            })
                        .end()
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("Processed");
                            }
                        });
            }
        };
    }
}




