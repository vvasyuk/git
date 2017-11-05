package com.example.demo.conf;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.converter.stream.CachedOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Jopa on 11/5/2017.
 */
@Configuration
public class AAARoute {

    @Autowired
    CamelContext camelContext;

    @Bean
    public RouteBuilder demoRoute(){


        return new RouteBuilder() {
            public void configure(){
                camelContext.setStreamCaching(true);
                camelContext.getStreamCachingStrategy().setSpoolDirectory("D:\\work\\git\\camel\\demo\\src\\main\\resources\\cache");
                camelContext.getStreamCachingStrategy().setSpoolThreshold(256 * 1024);
                camelContext.getStreamCachingStrategy().setBufferSize(64 * 1024);

                from("file:.\\src\\main\\resources?antInclude=book*.xml")
                        .process(new Processor() {
                            public void process(Exchange exchange) {
                                System.out.println(exchange.getIn().getBody());
                                System.out.println("#######################################");
                            }
                        });
            }
        };
    }
}

