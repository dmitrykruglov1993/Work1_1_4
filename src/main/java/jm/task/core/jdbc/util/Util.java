package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;


public class Util {
    private static final String URL="jdbc:mysql://localhost:3306/mysql?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USER_NAME="root";
    private static final String PASSWORD="1234";

    private static SessionFactory sessionFactory;

    public static Connection getConnection(){
        Connection connectoin = null;
        try {
            connectoin = DriverManager.getConnection(URL,USER_NAME,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connectoin;
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try{
                Properties properties = new Properties();
                properties.setProperty(Environment.URL,URL);
                properties.setProperty(Environment.USER,USER_NAME);
                properties.setProperty(Environment.PASS,PASSWORD);
                Configuration configuration = new Configuration();
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            }catch (Exception e) {
                System.out.println("Connection faild " + e);
            }
        }
        return sessionFactory;
    }

}
