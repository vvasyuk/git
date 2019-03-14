package camel;

import camel.common.CommonMain;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"camel"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication extends CommonMain {

}
