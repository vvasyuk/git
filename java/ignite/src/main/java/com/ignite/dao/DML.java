package com.ignite.dao;

import java.sql.*;

/**
 * Created by Jopa on 2/11/2018.
 */
public class DML {

    public static void sqlInsert (Connection conn) throws SQLException {
        // Populate City table
        try (PreparedStatement stmt =
                     conn.prepareStatement("INSERT INTO City (id, name) VALUES (?, ?)")) {

            stmt.setLong(1, 1L);
            stmt.setString(2, "Forest Hill");
            stmt.executeUpdate();

            stmt.setLong(1, 2L);
            stmt.setString(2, "Denver");
            stmt.executeUpdate();

            stmt.setLong(1, 3L);
            stmt.setString(2, "St. Petersburg");
            stmt.executeUpdate();
        }

        // Populate Person table
        try (PreparedStatement stmt =
                     conn.prepareStatement("INSERT INTO Person (id, name, city_id) VALUES (?, ?, ?)")) {

            stmt.setLong(1, 1L);
            stmt.setString(2, "John Doe");
            stmt.setLong(3, 3L);
            stmt.executeUpdate();

            stmt.setLong(1, 2L);
            stmt.setString(2, "Jane Roe");
            stmt.setLong(3, 2L);
            stmt.executeUpdate();

            stmt.setLong(1, 3L);
            stmt.setString(2, "Mary Major");
            stmt.setLong(3, 1L);
            stmt.executeUpdate();

            stmt.setLong(1, 4L);
            stmt.setString(2, "Richard Miles");
            stmt.setLong(3, 2L);
            stmt.executeUpdate();
        }
    }

    public static void sqlSelect (Connection conn) throws SQLException {
        // Get data using an SQL join sample.
        Statement stmt = conn.createStatement();
            try (ResultSet rs =
                         stmt.executeQuery("SELECT p.name, c.name " +
                                 " FROM Person p, City c " +
                                 " WHERE p.city_id = c.id")) {

                System.out.println("Query result:");

                while (rs.next())
                    System.out.println("sql    " + rs.getString(1) +
                            ", " + rs.getString(2));
            }

    }
}
