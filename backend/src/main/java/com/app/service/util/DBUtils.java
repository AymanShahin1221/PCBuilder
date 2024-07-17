package com.app.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    private DBUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection initDBConnection() {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        catch (SQLException e)
        {
            logger.error("Connection to PostgreSQL failed.");
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
            logger.error("Could not close prepared statement.");
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
            logger.error("Could not close prepared statement or resultset.");
        }
    }
}
