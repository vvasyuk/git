package com.ignite;

import com.ignite.util.StringUtil;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.IntStream;

@SpringBootApplication
@ComponentScan("com.ignite.config")
public class IgniteApplication {

	public static void main(String[] args) {

		SpringApplication.run(IgniteApplication.class, args);
	}
}
