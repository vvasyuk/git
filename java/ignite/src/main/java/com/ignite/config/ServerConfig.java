package com.ignite.config;

import com.ignite.dao.API;
import com.ignite.dao.DDL;
import com.ignite.dao.DML;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.*;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
@Profile("server")
public class ServerConfig {

    public ServerConfig() throws Exception {
        System.out.println("Im on TV");

        // Register JDBC driver
        //Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        // Open JDBC connection
        //Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");

        //DDL.sqlTableCreate(conn);
        //DML.sqlInsert(conn);
        //DML.sqlSelect(conn);
        //API.apiSelect();

        System.out.println("creating cache");
        API.createCache();
        //System.out.println("printing cache");
    }
}
