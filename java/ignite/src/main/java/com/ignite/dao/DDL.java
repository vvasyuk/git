package com.ignite.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jopa on 2/11/2018.
 */
public class DDL {
    public static void sqlTableCreate(Connection conn) throws SQLException {
        // Create database tables
        Statement stmt = conn.createStatement();

        // Create table based on REPLICATED template
        stmt.executeUpdate("CREATE TABLE City (" +
                " id LONG PRIMARY KEY, name VARCHAR) " +
                " WITH \"template=replicated\"");

        // Create table based on PARTITIONED template with one backup
        stmt.executeUpdate("CREATE TABLE Person (" +
                " id LONG, name VARCHAR, city_id LONG, " +
                " PRIMARY KEY (id, city_id)) " +
                " WITH \"backups=1, affinityKey=city_id\"");
    }
}
