package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends UserDao {

    UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();

    default void createUsersTable() throws SQLException {
        userDaoJDBCImpl.createUsersTable();
    }

    default void dropUsersTable() throws SQLException {
        userDaoJDBCImpl.dropUsersTable();
    }

    default void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBCImpl.saveUser(name, lastName, age);
    }

    default void removeUserById(long id) throws SQLException {
        userDaoJDBCImpl.removeUserById(id);
    }

    default List<User> getAllUsers() throws SQLException {
        return userDaoJDBCImpl.getAllUsers();
    }

    default void cleanUsersTable() throws SQLException {
        userDaoJDBCImpl.cleanUsersTable();
    }

}
