package com.example.demo;

import com.example.common.CommonMain;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication extends CommonMain {
}
