package com.example.demo.conf;

import com.example.demo.domain.Book2;
import com.example.demo.util.DatabaseUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;


import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jopa on 10/23/2017.
 */
@Configuration
public class BaseConfig {

    Connection con;
    Config config;
    Map map = new HashMap();

    public BaseConfig(@Value("${config}") String configFileName, ConfigurableEnvironment environment) throws Exception {
        final File configFile = new File(configFileName);
        this.config = ConfigFactory.parseFile(configFile);
        System.out.println("test");
        System.out.println(config.getString("VAR"));

        map = config.getConfig("testmap").entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().unwrapped()));
        System.out.println("isEmpty " + map.isEmpty());
        System.out.println("hasPath (testmap1) " + config.hasPath("testmap1"));

        //System.out.println(map.get("name"));
        //System.out.println(map.get("age"));

//        con = DatabaseUtil.getConnection();
//        if (con==null || con.isClosed()){
//            throw new Exception("Could not get a working connection.");
//        }

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

    @Bean("fileUris")
    List<String> getFileUris(){
        return config.getStringList("fileUris");
    }

//    @Bean
//    Connection getConnection() {
//        return DatabaseUtil.getConnection();
//    }

    @Bean
    JAXBContext newJaxbContext2 () throws JAXBException {
        return JAXBContext.newInstance(Book2.class);
    }

    @Bean
    @Scope("prototype")
    DataFormat newJaxb (JAXBContext jaxbContext2){
        return new JaxbDataFormat(jaxbContext2);
    }

}
