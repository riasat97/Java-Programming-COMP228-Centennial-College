package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * - COMP228 - Week 10 In-Class Lab
 * - Student Name: Riasat Raihan Noor
 * - Purpose: Establishes a JDBC connection to the MySQL 'javafxsample' database.
 */
public class Database {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/javafxsample";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Database() {
    }

    public static Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException exception) {
            System.out.println("Driver Error: " + exception.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (SQLException exception) {
            System.out.println("Connection Error: " + exception.getMessage());
        }

        return connection;
    }
}