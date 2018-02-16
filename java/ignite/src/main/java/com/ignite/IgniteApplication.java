package com.ignite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ignite.config")
public class IgniteApplication {

	public static void main(String[] args) {

		SpringApplication.run(IgniteApplication.class, args);
	}
}
