package com.app.service.util;

import java.sql.*;

/**
 * Utility class for common DB operations such as:
 * Creating a connection
 * Overloaded methods for closing prepared statements and result sets
 */

public class DBUtils {

    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/PCParts";
    private static final String DB_USER = "postgres";

    public static final String[] CATEGORY_TABLES = {"os", "cpu", "storage", "power_supply", "cooler", "keyboard", "memory", "gpu", "case_", "motherboard", "monitor"};

    public static Connection initDBConnection() {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        catch (SQLException e)
        {
            System.err.println("Connection to PostgreSQL failed.");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(PreparedStatement preparedStatement) throws SQLException {
        try
        {
            if (preparedStatement != null)
                preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        try
        {
            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
