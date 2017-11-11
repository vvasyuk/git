package com.example.demo.util;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Jopa on 11/10/2017.
 */
public class DatabaseUtil {

    static OracleDataSource oracleDS = null;

    public static DataSource getOracleDataSource(String url, String user, String pswd){
        try {
            oracleDS = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        oracleDS.setURL(url);
        oracleDS.setUser(user);
        oracleDS.setPassword(pswd);
        return oracleDS;
    }

    public static Connection getConnection() {
        Connection con =null;
        try {
            con = getOracleDataSource("jdbc:oracle:thin:@localhost:1521:XE", "test", "12345678").getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
//    DataSource ds = DatabaseUtil.getOracleDataSource();
//
//    Connection con = null;
//    Statement stmt = null;
//    ResultSet rs = null;
//        try {
//        con = ds.getConnection();
//        stmt = con.createStatement();
//        rs = stmt.executeQuery("select col1, col2 from a_mat");
//        while(rs.next()) {
//            System.out.println("col1=" + rs.getString("col1") + ", col2=" + rs.getString("col2"));
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
}
