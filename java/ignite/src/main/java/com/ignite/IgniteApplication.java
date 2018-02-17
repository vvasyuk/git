package com.ignite;

import com.ignite.util.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ignite.config")
public class IgniteApplication {

	public static void main(String[] args) {

		//SpringApplication.run(IgniteApplication.class, args);


		Utils.methodElapsedTime(() -> {
			System.out.println("Hello");
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
