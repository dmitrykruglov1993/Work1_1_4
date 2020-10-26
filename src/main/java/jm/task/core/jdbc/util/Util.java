package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL="jdbc:mysql://localhost:3306/mysql?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USER_NAME="root";
    private static final String PASSWORD="1234";

    public static Connection getConnection(){
        Connection connectoin = null;
        try {
           connectoin = DriverManager.getConnection(URL,USER_NAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connectoin;
    }
}
