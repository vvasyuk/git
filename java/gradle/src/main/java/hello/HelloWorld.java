package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorld {

  public static void main(String[] args) throws InterruptedException {

    SpringApplication.run(HelloWorld.class, args);
  }
}