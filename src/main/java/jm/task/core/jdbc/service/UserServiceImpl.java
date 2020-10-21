package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {

    Connection connection = getConnection();

    public void createUsersTable() {

        String sql = "CREATE TABLE Users(id int NOT NULL AUTO_INCREMENT," +
                "Name varchar (20) NOT NULL," +
                "LastName varchar (20)," +
                "Age INT NOT NULL," +
                "PRIMARY KEY(id))";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        String sql = "DROP TABLE Users";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users (name, lastName, age)" +
                " VALUE ('"+ name +"','"+ lastName +"', '"+ age +"')";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("User с именем ' "+ name +"' добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }


    public void removeUserById(long id) {
        User user = new User();
        String sql = "DELETE FROM Users WHERE id = '" + id + "'";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> UserList = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                UserList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
