package com.soap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static Connection conn = createConnection();

    public static Connection getConnection() {
        if (conn == null) {
            conn = createConnection();
        }

        return conn;
    }

    private static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver");
            throw new RuntimeException(e);
        }

        String url = System.getenv("DATABASE_URL");
        String user = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error creating connection");
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection");
            throw new RuntimeException(e);
        }
    }   

    public static void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error beginning transaction");
            throw new RuntimeException(e);
        }
    }

    public static void commitTransaction() {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error committing transaction");
            throw new RuntimeException(e);
        }
    }

    public static void rollbackTransaction() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            System.out.println("Error rolling back transaction");
            throw new RuntimeException(e);
        }
    }
}
