package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static final String url = "jdbc:mysql://localhost:3306/online_mt_system";   //database link
    private static final String username = "root";   //database username
    private static final String password = "sliit";  //database password

    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DBConnect() {}

    // Static method to get the connection (singleton design pattern-lazy initialization)
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection failed!");
        }
        return connection;
    }
}
