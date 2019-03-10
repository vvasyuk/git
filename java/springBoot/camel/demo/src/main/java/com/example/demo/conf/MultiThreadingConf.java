package com.example.demo.conf;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Profile("multiThreading")
public class MultiThreadingConf {

    public MultiThreadingConf(JdbcTemplate jdbcTemplate) {
        System.out.println("Will try to run");

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT col1, col2 FROM test");
        for (Map row : rows) {
            System.out.println(row.get("col1") + ":" + row.get("col2"));
        }

        List<Map<String, Object>> rowws = jdbcTemplate.queryForList("SELECT col1, col2 FROM test where col1=?", new Object[] { "10" });
        for (Map row : rowws) {
            System.out.println(row.get("col1") + ":" + row.get("col2"));
        }

        String col1 = jdbcTemplate.queryForObject(
                    "SELECT col1 FROM test WHERE col2 = ?", new Object[] { "10" }, String.class);
        System.out.println(col1);
    }

    //    @Bean
//    public RouteBuilder demoRoute() {
//        return new RouteBuilder() {
//            public void configure() {
//                Map<String, String> map = new HashMap<>();
//                map.put("10A", "10,11,12");
//                map.put("20A", "20,21,22");
//
//                map.forEach((key, value) -> {
//                    from("timer://runOnce?repeatCount=1")
//                            .setHeader("key", constant(key))
//                            .setHeader("value", constant(value))
////                            .log("${header[key]}")
//                            .inOnly("seda:process")
//                            .log("End inOnly");
//                });
//                from("seda:process?concurrentConsumers=5")
//                        .log("in process")
//                        .process((exchange) -> {
//                            List<Message> messages = new ArrayList<>();
//                            Message inMessage = exchange.getIn();
//                            String[] slices = exchange.getIn().getHeader("value").toString().split(",");
//                            for (int i=0; i<slices.length; i++){
//                                Message msg = new DefaultMessage();
//                                msg.setBody("defaultBody"+i);
//                                msg.setHeaders(new HashMap(inMessage.getHeaders()));
//                                msg.setHeader("slice", slices[i]);
//                                messages.add(msg);
//                            }
//                            exchange.getOut().setBody(messages);
//                        })
//                        .split(body()).parallelProcessing()
//                            .to("direct-vm:single-process");
//
//                from("direct-vm:single-process")
//                        //.transacted()
//                        .process((exchange) -> {
//                            System.out.println(exchange.getIn().getHeader("slice"));
//                        });
//            }
//        };
//    }
}
