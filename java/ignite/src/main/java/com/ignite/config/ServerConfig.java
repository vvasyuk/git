package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.util.Utils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
@Import({IgniteServerConfiguration.class})
@Profile("server")
public class ServerConfig {

    IgniteCache<Integer, Book> cache;

    public ServerConfig(Ignite ignite) throws Exception {
        System.out.println("ServerConfig start");
        this.cache = ignite.getOrCreateCache("test");

        IntStream.range(0, 10).forEach(i -> {
                    cache.put(i, new Book(Utils.getRandonString(50), Utils.getRandonString(50)));
                }
        );

        // Register JDBC driver
        //Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        // Open JDBC connection
        //Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");

        //DDL.sqlTableCreate(conn);
        //DML.sqlInsert(conn);
        //DML.sqlSelect(conn);
        //API.apiSelect();

//        System.out.println("creating cache");
//        API.createCache();
//        System.out.println("printing cache");
    }
    @Bean
    public IgniteCache<Integer, Book> getCache(){
        return this.cache;
    }
}
