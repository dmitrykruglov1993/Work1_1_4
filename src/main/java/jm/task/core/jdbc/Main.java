package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ddfgma","KruksdfssDF", (byte) 5);
        userService.saveUser("wtqrey","gulful", (byte) 16);
        userService.saveUser("dargaa","dgjjmfh", (byte) 87);
        userService.saveUser("arkgty","rturwsyjst", (byte) 69);

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
