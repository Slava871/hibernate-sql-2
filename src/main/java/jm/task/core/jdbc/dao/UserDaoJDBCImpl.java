package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = new Util().getConnection();

    public UserDaoJDBCImpl() {

    }

    //проверяем, существует ли таблица
    public boolean ifExist() throws SQLException {
        Statement statement = null;
        String tableName = "table_kata_usr";
        boolean exists = connection.getMetaData().getTables(null, null, tableName, null).next();
        if (!exists) {
            if (connection != null) {
                connection.close();
            }
            return false;
        } else {
            if (connection != null) {
                connection.close();
            }
            return true;
        }
    }

    public void createUsersTable() throws SQLException {
        UserDaoJDBCImpl u = new UserDaoJDBCImpl();
        if (!u.ifExist()) {
            String sql = "CREATE TABLE table_kata_usr(id INT NOT null AUTO_INCREMENT, name Varchar(30), lastName Varchar(30), age INT NOT null, PRIMARY KEY (id))";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("такая таблица уже существует");
        }
    }


    public void dropUsersTable() throws SQLException {

        String sql = "DROP TABLE table_kata_usr";
        UserDaoJDBCImpl u = new UserDaoJDBCImpl();
        if (u.ifExist()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("такой таблицы  не существует");
        }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException {

        String sql = "  INSERT INTO table_kata_usr (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) throws SQLException {

        String sql = "DELETE FROM table_kata_usr WHERE id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
    public List<User> getAllUsers() throws SQLException {

        List<User> userList = new ArrayList<>(); //лист строк из табл
        String sql = "SELECT id, name, lastName, age FROM table_kata_usr";
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {

        String sql = "TRUNCATE table_kata_usr";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}