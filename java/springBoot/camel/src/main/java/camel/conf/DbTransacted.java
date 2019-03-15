package camel.conf;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;

@Configuration
@Profile("DbTransacted")
public class DbTransacted {

        @Bean
    public RouteBuilder demoRoute() {
        return new RouteBuilder() {
            public void configure() {

                Map<String, String> map = new HashMap<>();
                map.put("10", "10");
                map.put("20", "20");
                map.put("30", "30");
                map.put("40", "40");
                map.put("50", "50");

                map.forEach((key, values) -> {
                    from("timer://runOnce?repeatCount=1")
                            .setHeader("key", constant(key))
                            .setHeader("value", constant(values))
                            .setHeader("pre", constant("sql:call my_pack.set_v1(:#value)"))
                            .setHeader("statement", constant("select my_pack.get_v1 from dual"))

                            .inOnly("seda:processParallel");
                });
                from("seda:processParallel?concurrentConsumers=5")
                        .to("direct:process");

                from("direct:process")
                        //.log("${header[key]} ${header[value]}")
                        //.transacted()
                        //.to("sql:call my_pack.set_v1(:#value)")
                        //.to("sql:select :#key key, my_pack.get_v1 value from dual?outputType=StreamList")
                        .to("bean:SqlTransaction?method=executeTransaction")
                        .split(body()).streaming()
//                            .to("log:row")
                            .process((exchange) -> {
                                LinkedCaseInsensitiveMap cols = (LinkedCaseInsensitiveMap) exchange.getIn().getBody();
                                System.out.println(exchange.getIn().getHeader("key")+":"+cols.get("GET_V1"));
                            })
                        .end();

//                Map<String, String> map = new HashMap<>();
//                map.put("10", "10");
//                //map.put("20", "20");
//
//                map.forEach((key, value) -> {
//                    from("timer://runOnce?repeatCount=1")
//                            .to("sql:CREATE TABLE test (col1 VARCHAR(10),col2 VARCHAR(10))?dataSource=oracleDatasource")
//                            .to("sql:INSERT INTO test VALUES ('10', '10')?dataSource=oracleDatasource")
//                            .to("sql:INSERT INTO test VALUES ('20', '20')?dataSource=oracleDatasource")
//                            .setHeader("key", constant(key))
//                            .setHeader("value", constant(value))
////                            .log("${header[key]}")
//                            .inOnly("seda:process")
//                            .log("End inOnly");
//                });
//                from("seda:process?concurrentConsumers=5")
//                        .process((exchange) -> {
//                            List<Message> messages = new ArrayList<>();
//                            Message inMessage = exchange.getIn();
//                            String[] slices = exchange.getIn().getHeader("value").toString().split(",");
//                            for (int i=0; i<slices.length; i++){
//                                Message msg = new DefaultMessage(exchange.getContext());
//                                msg.setBody("defaultBody"+i);
//                                msg.setHeaders(new HashMap(inMessage.getHeaders()));
//                                msg.setHeader("slice", slices[i]);
//                                messages.add(msg);
//                            }
//                            exchange.getOut().setBody(messages);
//                        })
//                        .split(body()).parallelProcessing()
//                            .to("direct-vm:single-process")
//                        .end();
//
//                from("direct-vm:single-process")
//                        .transacted()
//                        //?outputType=StreamList
//                        //.to("sql:select * from test where col2=:#slice?dataSource=derbyDatasource")
//                        .choice()
//                            .when(header("slice").isEqualTo("10"))
//                                .log("inside when ")
//                                .to("sql:insert into mytest(col1, col2) values('13', '13')?dataSource=oracleDatasource")
//                        .end()
//                        .to("sql:select count(*) cnt from mytest where col2='13'?dataSource=oracleDatasource")
//                        .split(body()).streaming()
//                            .log(body().toString())
//                            .process((exchange) -> {
//                                //System.out.println(Thread.currentThread());
//                                System.out.println(exchange.isTransacted());
//                            })
//                        .end()
////                        .to("sql:delete from test?dataSource=derbyDatasource")
////                        .to("sql:select * from test?dataSource=derbyDatasource")
////                        .split(body()).streaming()
////                        .process((exchange) -> {
////                            System.out.println(exchange.getIn().getBody().toString());
////                        })
//                        .end();
            }
        };
    }
}
