package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends UserDao {

    UserDaoHibernateImpl u = new UserDaoHibernateImpl();

    default void createUsersTable() throws SQLException {
        u.createUsersTable();
    }

    default void dropUsersTable() throws SQLException {
        u.dropUsersTable();
    }

    default void saveUser(String name, String lastName, byte age) throws SQLException {
        u.saveUser(name, lastName, age);
    }

    default void removeUserById(long id) throws SQLException {
        u.removeUserById(id);
    }

    default List<User> getAllUsers() throws SQLException {
        return u.getAllUsers();
    }

    default void cleanUsersTable() throws SQLException {
        u.cleanUsersTable();
    }

}
