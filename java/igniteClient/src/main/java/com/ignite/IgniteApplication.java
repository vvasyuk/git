package com.ignite;

//import net.sourceforge.sizeof.SizeOf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Shell;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.JLineShellAutoConfiguration;
import org.springframework.shell.standard.StandardAPIAutoConfiguration;
import org.springframework.shell.standard.commands.StandardCommandsAutoConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@ComponentScan("com.ignite.config")
@SpringBootApplication(exclude = {SpringShellAutoConfiguration.class, JLineShellAutoConfiguration.class, StandardAPIAutoConfiguration.class, StandardAPIAutoConfiguration.class, StandardCommandsAutoConfiguration.class})
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

//	public static Iterator<String> getSome() {
//		List<String> l = new ArrayList<>();
//		for (int i=0; i<100000;i++ ){
//			l.add("aassddffgg");
//		}
//		//System.out.println(SizeOf.deepSizeOf(l)); //this will print the object size in bytes
//		return l.iterator();
//	}
}