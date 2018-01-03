//package com.example.demo.conf;
//
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.JMSException;
//
///**
// * Created by Jopa on 12/30/2017.
// */
//@Configuration
//@EnableJms
//@Profile("queueMode")
//public class QueueConfig {
//
//    @Autowired
//    private BeanFactory springContextBeanFactory;
//
//    @Bean
//    public DefaultJmsListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
//        return factory;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
//        return new JmsTemplate(connectionFactory);
//    }
//}
