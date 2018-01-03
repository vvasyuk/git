package com.example.demo.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.apache.activemq.usage.SystemUsage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.jms.Destination;
import javax.jms.JMSException;
import java.util.UUID;

/**
 * Created by Jopa on 12/30/2017.
 */
@Configuration
@Profile("queueMode")
public class ActiveMqConfig {
    private EmbeddedActiveMQBroker broker;

    public EmbeddedActiveMQBroker getBroker(){return broker;}

    @Bean(name = "queueDestination")
    public Destination queueDestination(@Value("ACTIVEMQ") String queueName) throws JMSException{
        return new ActiveMQQueue(queueName);
    }

    @PostConstruct
    public void startActiveMQ() throws Exception {
        broker = new EmbeddedActiveMQBroker();
        broker.setBrokerName("activemq-broker-" + UUID.randomUUID());
        broker.getBrokerService().setDataDirectory("target");
        broker.getBrokerService().setUseJmx(false);
        broker.getBrokerService().setUseShutdownHook(false);
        SystemUsage systemUsage = broker.getBrokerService().getSystemUsage();
        systemUsage.getStoreUsage().setLimit(1024*1024*32);
        systemUsage.getTempUsage().setLimit(1024*1024*32);
        broker.start();
    }
}
