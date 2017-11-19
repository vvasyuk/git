package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={"config=./src/test/config/test.conf"})
@ActiveProfiles("regularMode")
public class DemoApplicationTests {

	@Autowired
	private CamelContext ctx;

	@Test
	public void contextLoads() throws IOException {
		ProducerTemplate producerTemplate=ctx.createProducerTemplate();
		producerTemplate.sendBodyAndHeader("file:.\\src\\main\\resources?antInclude=book_test_*.xml", FileUtils.readFileToString(new File("src/test/data/book_test_001_003.xml")), Exchange.FILE_NAME, "book_test_001_003.xml");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String streamToString(InputStream str){
		Scanner scanner = new Scanner(str, "UTF-8").useDelimiter("\\A");
			if (scanner.hasNext())
				return scanner.next();
		return "";
	}
}
