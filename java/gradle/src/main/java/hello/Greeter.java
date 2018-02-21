package hello;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Greeter {
  public Greeter() {
    System.out.println("Hi");
  }
}