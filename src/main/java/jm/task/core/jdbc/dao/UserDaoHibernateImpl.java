package jm.task.core.jdbc.dao;

import org.hibernate.*;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE User(id int NOT NULL AUTO_INCREMENT," +
                "Name varchar (20) NOT NULL," +
                "LastName varchar (20)," +
                "Age INT NOT NULL," +
                "PRIMARY KEY(id))";
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE User";

        try(Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()){
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            System.out.println("Юзер с именем - "+name+" добавлен в базу данных");
            }
        catch(Exception e) {
            e.getStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
        Transaction tx = session.beginTransaction();
        session.delete(session.get(User.class,id));
        tx.commit();

        }catch (Exception e) {
            System.out.println("Error" + e.getStackTrace());
        }
    }

    @Override
    public List<User> getAllUsers()  {

        List<User> userList = new ArrayList<>();

        try(Session session = Util.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User");
            return query.list();
        }catch (Exception e) {
            System.out.println("Eror"+e.getStackTrace());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession();) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE User");
            query.executeUpdate();
            tx.commit();
            session.close();
        }catch (Exception e) {
            System.out.println("Eror"+e.getStackTrace());
        }
        //System.out.println("All user was deleted from table");
    }
}
