package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
@Import({IgniteServerConfiguration.class})
@Profile("server")
public class ServerConfig {

    public ServerConfig(Ignite ignite) throws Exception {
        System.out.println("ServerConfig start");
        IgniteCache<Object, Object> cache = ignite.getOrCreateCache("test");
        cache.put("1", "one");

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
}
