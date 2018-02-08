package com.example.demo;

import com.example.demo.util.Utils;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jopa on 12/30/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties={"config=./src/test/config/test.conf"})
@ActiveProfiles("queueMode")
public class DemoQueueApplicationTest {

    @Autowired
    private CamelContext ctx;

    @Autowired
    private JmsTemplate jmstemplate;

    @Test
    public void queueTest() throws IOException {

        NotifyBuilder notify = new NotifyBuilder(ctx)
                .wereSentTo("direct:process").whenDone(1).create();

        jmstemplate.send("activemq", Utils.getTestXml("src/test/resources/data/book_namespace.xml"));

        boolean done = notify.matches(100000, TimeUnit.SECONDS);

        if(!FileSystemUtils.deleteRecursively(new File("src\\test\\resources\\.camel"))) {
            System.out.println("Problem occurs when deleting the directory : ");
        }

    }
}
