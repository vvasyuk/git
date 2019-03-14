package camel.conf;

import camel.jmx.JmxMXBeanImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Configuration
@Profile("jmxRoute")
public class JmxRoute {

    public JmxRoute(CamelContext camelContext) throws Exception {
        ManagementFactory.getPlatformMBeanServer()
                .registerMBean(new JmxMXBeanImpl(), new ObjectName("jmxExample", "name", "simpleBean"));
    }

    @Bean
    public RouteBuilder demoRoute() {

        return new RouteBuilder() {
            public void configure() {
                //from("timer://runOnce?repeatCount=1")
                Namespaces ns = new Namespaces("def", "urn:org.apache.camel.component:jmx"); //.add("prefix", "namespace")
                from("jmx:platform?objectDomain=jmxExample&key.name=simpleBean")
                        .setHeader("testHeader", xpath("/def:AttributeChangeNotification/def:message").stringResult().namespaces(ns))
                        .log("${header[testHeader]}")
                        .process((exchange) -> {
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println(exchange.getIn().getHeader("testHeader"));
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        });


            }
        };
    }
}

/*
--- to use ssl with jmx:
-Dcom.sun.management.jmxremote.port=1099
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl.need.client.auth=true
-Djavax.net.ssl.keystore=c:/path/to/keys.jks
-Djavax.net.ssl.keystorePassword=password

---disabele security
-Dcom.sun.management.jmxremote.port=1099
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false

service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi
*/