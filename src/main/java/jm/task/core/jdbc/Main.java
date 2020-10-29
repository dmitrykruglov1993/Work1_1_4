package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();

        user.createUsersTable();

        user.saveUser("sdfsdf","adgfadg",(byte) 23);
        user.saveUser("hgkfdr","poiuyrt",(byte) 85);
        user.saveUser("zxcvbn","likjuyh",(byte) 79);
        user.saveUser("aqwsde","frtghyy",(byte) 9);
        System.out.println(user.getAllUsers().toString());

        user.cleanUsersTable();

        user.dropUsersTable();
    }
}
