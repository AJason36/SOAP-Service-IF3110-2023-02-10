package com.soap.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.mysql.cj.jdbc.MysqlDataSource;

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

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(System.getenv("DB_HOST"));
        dataSource.setPort(Integer.parseInt(System.getenv("DB_PORT")));
        dataSource.setDatabaseName(System.getenv("MYSQL_DATABASE"));
        dataSource.setUser(System.getenv("MYSQL_USER"));
        dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));

        try {
            return dataSource.getConnection();
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

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp gregorianXMLToTimestamp(XMLGregorianCalendar calendar) {
        return new Timestamp(calendar.toGregorianCalendar().getTimeInMillis());
    }

    public static XMLGregorianCalendar timestampToGregorianXML(Timestamp timestamp) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(timestamp);

            int miliseconds = timestamp.getNanos() / 1000000;
            XMLGregorianCalendar xmlCalendar = javax.xml.datatype.DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(calendar);

            xmlCalendar.setMillisecond(miliseconds);
            return xmlCalendar;
        } catch (Exception e) {
            System.out.println("Error converting timestamp to XMLGregorianCalendar");
            throw new RuntimeException(e);
        }
    }

    public static Timestamp plusDays(Timestamp timestamp, int days) {
        return new Timestamp(timestamp.getTime() + days * 24 * 60 * 60 * 1000);
    }

}
