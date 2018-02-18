package com.ignite;

import com.ignite.util.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
@ComponentScan("com.ignite.config")
public class IgniteApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(IgniteApplication.class, args);

//		Instant a = Instant.now();
//		Thread.sleep(700);
//		Instant b = Instant.now();
//		Duration timeElapsed = Duration.between(a, b);
//		System.out.println(timeElapsed.getNano()/1000000);
	}
}
