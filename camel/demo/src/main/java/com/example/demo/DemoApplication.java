package com.example.demo;

import com.example.demo.util.DatabaseUtil;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {
	private static final Logger logger = LogManager.getLogger(DemoApplication.class);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.LOG);
        ConfigurableApplicationContext context = app.run(args);

//		logger.debug("Debugging log");
//		logger.info("Info log");
//		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
//		logger.fatal("Damn! Fatal error. Please fix me.");

//		CamelSpringBootApplicationController applicationController =
//				context.getBean(CamelSpringBootApplicationController.class);
//		applicationController.blockMainThread();

//		while(true){
//			Thread.sleep(3000);
//		}
//        ProducerTemplate producer = context.getBean(SpringCamelContext.class).createProducerTemplate();
//        producer.sendBody("direct:start1", "World");
	}
}
