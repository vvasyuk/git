package com.example.demo.trax;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
public class BaseConfig {

    Config config;
    Map map = new HashMap();

    public BaseConfig(@Value("${config}") String configFileName, ConfigurableEnvironment environment) {
        final File configFile = new File(configFileName);
        this.config = ConfigFactory.parseFile(configFile);
        System.out.println("test");
        System.out.println(config.getString("VAR"));

        map = config.getConfig("testmap").entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().unwrapped()));
        System.out.println("isEmpty " + map.isEmpty());
        System.out.println("hasPath (testmap1) " + config.hasPath("testmap1"));

        //System.out.println(map.get("name"));
        //System.out.println(map.get("age"));
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
    }
}
