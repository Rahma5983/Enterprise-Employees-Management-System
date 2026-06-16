package com.enterprise.ems.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // 3306 is the standard default port for MySQL. Adjust if you configured a custom port.
    private static final String URL = "jdbc:mysql://localhost:3306/EnterpriseEMS"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "rahmakhangoenka@555"; // ⚠️ Replace this with your actual root password

    /**
     * Establishes a raw connection to the MySQL Database.
     * We use a static method so we can easily request a connection from anywhere in our app.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly loads the MySQL driver class into memory
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Check your pom.xml dependencies!", e);
        }
    }
}