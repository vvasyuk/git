package camel.conf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("testInputStream")
public class InputStreamRoute {

    @Bean
    public RouteBuilder demoRoute() {

        return new RouteBuilder() {
            public void configure() {
                from("timer://runOnce?repeatCount=1")
                        .to("bean:SomeService?method=getInputStream")
                        //.convertBodyTo(String.class)              //input stream can be converted to string or splited
                        .split(body().tokenize(",")).streaming()
                        .process((exchange) -> {
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println(exchange.getIn().getBody().toString());
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        });


            }
        };
    }
}

