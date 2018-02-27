package com.ignite;

import net.sourceforge.sizeof.SizeOf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan("com.ignite.config")
public class IgniteApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(IgniteApplication.class, args);
		//Iterator<String> s = getSome();
		//System.out.println("s " + SizeOf.deepSizeOf(s));
		//System.out.println("Hi");


//		Instant a = Instant.now();
//		Thread.sleep(700);
//		Instant b = Instant.now();
//		Duration timeElapsed = Duration.between(a, b);
//		System.out.println(timeElapsed.getNano()/1000000);
	}



	public static Iterator<String> getSome() {
		List<String> l = new ArrayList<>();
		for (int i=0; i<100000;i++ ){
			l.add("aassddffgg");
		}

		System.out.println(SizeOf.deepSizeOf(l)); //this will print the object size in bytes

		return l.iterator();
	}
}
