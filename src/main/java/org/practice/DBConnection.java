package org.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String URL = "jdbc:postgresql://localhost:5432/practice5";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";

    private Connection connection;

    public DBConnection() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection(){
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
