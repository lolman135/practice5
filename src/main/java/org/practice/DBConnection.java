package org.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DBConnection} class establishes and provides a connection
 * to a PostgreSQL database.
 */
public class DBConnection {


    private static final String URL = "jdbc:postgresql://localhost:5432/practice5";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private final Connection connection;

    /**
     * Creates a new {@code DBConnection} and connects to the database.
     * @throws SQLException if a database access error occurs.
     */
    public DBConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Returns the database connection.
     * @return a {@code Connection} object to the database.
     */
    public Connection getConnection(){
        return connection;
    }
}
