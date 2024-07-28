package com.app.service.util;

import com.app.entity.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.app.service.util.PartCategory.*;

/**
 * Utility class for common DB operations such as:
 * Creating a connection
 * Overloaded methods for closing prepared statements and result sets
 */

public class DBUtils {

    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/PCParts";
    private static final String DB_USER = "postgres";

    public static final String[] CATEGORY_TABLES = {"os", "cpu", "storage", "power_supply", "cooler", "keyboard", "memory", "gpu", "case_", "motherboard", "monitor"};

    private static final Map<PartCategory, Class<? extends PCPart>> categoryClassMap = new HashMap<>();

    private DBUtils() {
        throw new IllegalStateException("Utility class");
    }

    static
    {
        categoryClassMap.put(CASE, Case.class);
        categoryClassMap.put(COOLER, Cooler.class);
        categoryClassMap.put(CPU, com.app.entity.model.CPU.class);
        categoryClassMap.put(GPU, com.app.entity.model.GPU.class);
        categoryClassMap.put(KEYBOARD, Keyboard.class);
        categoryClassMap.put(MEMORY, Memory.class);
        categoryClassMap.put(MONITOR, Monitor.class);
        categoryClassMap.put(MOTHERBOARD, Motherboard.class);
        categoryClassMap.put(OS, OS.class);
        categoryClassMap.put(POWER_SUPPLY, PowerSupply.class);
        categoryClassMap.put(STORAGE, Storage.class);
    }

    public static <T extends PCPart> Class<T> getClassInstance(String categoryName) {
        return (Class<T>) categoryClassMap.get(PartCategory.valueOf(categoryName.toUpperCase()));
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
