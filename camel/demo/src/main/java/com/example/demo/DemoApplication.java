package com.example.demo;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.apache.camel.util.concurrent.ThreadHelper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.LOG);
        ConfigurableApplicationContext context = app.run(args);

		CamelSpringBootApplicationController applicationController =
				context.getBean(CamelSpringBootApplicationController.class);
		applicationController.blockMainThread();

//		while(true){
//			Thread.sleep(3000);
//		}
//        ProducerTemplate producer = context.getBean(SpringCamelContext.class).createProducerTemplate();
//        producer.sendBody("direct:start1", "World");
	}
}
