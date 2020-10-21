package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL="jdbc:mysql://localhost:3306/mysql?serverTimezone=Europe/Moscow&useSSL=false";
    private final String USER_NAME="root";
    private final String PASSWORD="1234";

    public Connection getConnection(){
        Connection connectoin = null;
        try {
           connectoin = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            System.out.println("Connection good");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return connectoin;
    }
}
