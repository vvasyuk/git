package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

@Configuration
public class Greeter {
  public Greeter(@Value("${config}") String configFileName, ConfigurableEnvironment environment) throws Exception {
    final File configFile = new File(configFileName);
    Config config = ConfigFactory.parseFile(configFile);
    System.out.println(config.getString("VAR"));

    environment.getPropertySources().addLast(new org.springframework.core.env.PropertySource<Config>("configFile", config) {
      @Override
      public String getProperty(String s) {
        try{
          return source.getString(s);
        }catch(Exception e){
          return null;
        }
      }
    });


      Config c = (Config) environment.getPropertySources().get("configFile").getSource();

      List<Integer> listInt = c.getIntList("VARAR");
      System.out.println(listInt.get(0));
      System.out.println(environment.getProperty("VAR", String.class));

      //System.out.println(c.getInt("VARL"));

      System.out.println(c.getLong("VARL"));

  }
}