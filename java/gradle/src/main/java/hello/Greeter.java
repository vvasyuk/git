package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
//import com.typesafe.config.Config;
//import com.typesafe.config.ConfigFactory;

@Configuration
public class Greeter {
//  public Greeter(@Value("${config}") String configFileName, ConfigurableEnvironment environment) throws Exception {
//    final File configFile = new File(configFileName);
//    Config config = ConfigFactory.parseFile(configFile);
//    System.out.println(config.getString("VAR"));
//
//    environment.getPropertySources().addLast(new org.springframework.core.env.PropertySource<Config>("configFile", config) {
//      @Override
//      public String getProperty(String s) {
//        try{
//          return source.getString(s);
//        }catch(Exception e){
//          return null;
//        }
//      }
//    });
//  }
}