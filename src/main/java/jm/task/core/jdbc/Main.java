package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Tom", "Jonson", (byte) 27);
        System.out.println("User с именем – " + ((User)userService.getAllUsers().get(0)).getName() + " добавлен в базу данных");

        userService.saveUser("Anna", "Petrova", (byte) 23);
        System.out.println("User с именем – " + userService.getAllUsers().get(1).getName() + " добавлен в базу данных");

        userService.saveUser("Ivan", "Ivanov", (byte) 35);
        System.out.println("User с именем – " + userService.getAllUsers().get(2).getName() + " добавлен в базу данных");

        userService.saveUser("Dasha", "Sidorova", (byte) 20);
        System.out.println("User с именем – " + userService.getAllUsers().get(3).getName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
