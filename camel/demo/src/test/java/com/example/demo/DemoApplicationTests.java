package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={"config=./src/test/config/test.conf"})
@ActiveProfiles("regularMode")
public class DemoApplicationTests {

	@Autowired
	private CamelContext ctx;
	@Autowired
	@Qualifier("routeConfig")
	Map<String, String> routeConfig;

	@Test
	public void contextLoads() throws IOException {
		ProducerTemplate producerTemplate=ctx.createProducerTemplate();
		NotifyBuilder notify = new NotifyBuilder(ctx)
				.wereSentTo("direct:process").whenDone(1).create();

		producerTemplate.sendBodyAndHeader(routeConfig.get("TEST_ROUTE_URI"),
				FileUtils.readFileToString(new File("src\\test\\resources\\data\\book_test_001_003.xml"), "UTF-8"),
				Exchange.FILE_NAME, "book_test_001_003.xml");

		boolean done = notify.matches(100000, TimeUnit.SECONDS);

	}
}
