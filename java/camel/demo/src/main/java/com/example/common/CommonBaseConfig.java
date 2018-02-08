package com.example.common;

import com.example.demo.domain.Book2;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jopa on 1/5/2018.
 */
public class CommonBaseConfig {

    Connection con;
    Config config;
    Map map = new HashMap();

    public CommonBaseConfig(@Value("${config}") String configFileName, ConfigurableEnvironment environment) throws Exception {
        final File configFile = new File(configFileName);
        this.config = ConfigFactory.parseFile(configFile);
        System.out.println(config.getString("VAR"));

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

    @Bean
    Config config(){
        return this.config;
    }

    @Bean
    @Qualifier("routeConfig")
    Map<String, String> getRouteConfig(){
        return config.getConfig("routeConfig").entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().unwrapped().toString()));
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
