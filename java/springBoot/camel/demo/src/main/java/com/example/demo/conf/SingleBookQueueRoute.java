package com.example.demo.conf;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 12/30/2017.
 */
@Configuration
@Profile("queueMode")
public class SingleBookQueueRoute {

    @Bean
    public RouteBuilder demoRoute() {

        return new RouteBuilder() {
            public void configure(){

                from("jms:{{jms.queue.{{JMS_MODE}}}}")
                        .to("direct:process");

                Namespaces ns = new Namespaces("def", "urn:test:123"); //.add("prefix", "namespace")
                from("direct:process")
                        .log("received message")
                        .setHeader("h0", constant("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"))
                        .setHeader("h1", xpath("/def:catalog/def:book/def:author").stringResult().namespaces(ns))
                        .process(exchange -> {
                            System.out.println(exchange.getIn().getHeader("h0"));
                            System.out.println(exchange.getIn().getHeader("h1"));
                            System.out.println(exchange.getIn().getHeader("h0"));
                        });


            }
        };
    }

}
