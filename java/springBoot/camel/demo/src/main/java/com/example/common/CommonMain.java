package com.example.common;

import com.example.demo.DemoApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Jopa on 1/5/2018.
 */
public class CommonMain {
    private static final Logger logger = LogManager.getLogger(DemoApplication.class);

    public static void main(String[] args) throws InterruptedException {

        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.addInitializers();
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);

//		logger.debug("Debugging log");
//		logger.info("Info log");
//		logger.warn("Hey, This is a warning!");
//		logger.error("Oops! We have an Error. OK");
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
