package org.example.studentapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/final_project_db";
    private static String user = "springuser";
    private static String password = "springpass123!";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
