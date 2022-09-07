package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();

        //userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("jo", "Jonson", (byte) 25);
        System.out.println("User с именем – " + userService.getAllUsers().get(0).getName() + " добавлен в базу данных");

        userService.saveUser("Anna", "Ivanova", (byte) 30);
        System.out.println("User с именем – " + userService.getAllUsers().get(1).getName() + " добавлен в базу данных");

        userService.saveUser("Ivan", "Ivanov", (byte) 35);
        System.out.println("User с именем – " + userService.getAllUsers().get(2).getName() + " добавлен в базу данных");

        userService.saveUser("Dasha", "Petrova", (byte) 20);
        System.out.println("User с именем – " + userService.getAllUsers().get(3).getName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
