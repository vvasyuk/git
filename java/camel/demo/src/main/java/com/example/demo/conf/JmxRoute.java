package com.example.demo.conf;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Configuration
@Profile("jmxRoute")
public class JmxRoute {

    public JmxRoute(CamelContext camelContext) throws Exception {
        JmxImpl bean = new JmxImpl();
        ManagementFactory.getPlatformMBeanServer()
                .registerMBean(bean, new ObjectName("jmxExample", "name", "simpleBean"));
    }

    @Bean
    public RouteBuilder demoRoute() {

        return new RouteBuilder() {
            public void configure() {
                //from("timer://runOnce?repeatCount=1")
                from("jmx:platform?objectDomain=jmxExample&key.name=simpleBean")
                        .process((exchange) -> {
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("xyi");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        });


            }
        };
    }
}

//java -jar jmxterm-1.0-alpha-4-uber.jar -l service:jmx:rmi:///jndi/rmi://THEHOST:9410/jmxrmi -u LDAPUSER -p LDAPPASS
//-Dorg.apache.camel.jmx.createRmiConnector=true
//-Dorg.apache.camel.jmx.mbeanObjectDomainName=org.apache.camel
//-Dorg.apache.camel.jmx.rmiConnector.registryPort=1099
//-Dorg.apache.camel.jmx.serviceUrlPath=camel
//service:jmx:rmi:///jndi/rmi://localhost:1099/camel

//context.getManagementStrategy().getManagementAgent().setCreateConnector(true);

//public class JmxClientMain {
//    private String serviceUrl = "service:jmx:rmi:///jndi/rmi://"
//            + "localhost:1099/jmxrmi/camel"; ❶
//    public static void main(String[] args) throws Exception {
//        JmxClientMain main = new JmxClientMain();
//        main.run();
//    }
//    public void run() throws Exception {
//        JmxCamelClient client = new JmxCamelClient();
//        client.connect(serviceUrl); ❷
//        System.out.println("Version: " + client.getCamelVersion()); ❸
//        System.out.println("Uptime: " + client.getCamelUptime()); ❸
//        client.disconnect(); ❹
//    }
//}

//context.getManagementStrategy().getManagementAgent().setServiceUrlPath("/foo/bar");
//context.getManagementStrategy().getManagementAgent().setRegistryPort(2113);
//context.getManagementStrategy().getManagementAgent().setCreateConnector(true);