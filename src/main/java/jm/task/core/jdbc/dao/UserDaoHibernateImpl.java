package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            String sql = "CREATE TABLE table_kata_usr(id INT NOT null AUTO_INCREMENT, name Varchar(30), lastName Varchar(30), age INT NOT null, PRIMARY KEY (id))";
            session.createNativeQuery(sql).addEntity(User.class);
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            String sql = "DROP TABLE test_base.table_kata_usr";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            String sql = "SELECT * FROM table_kata_usr";
            List<User> userList = session.createNativeQuery(sql).addEntity(User.class).list();
            session.getTransaction().commit();
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.createSQLQuery("truncate table table_kata_usr").executeUpdate();
        }
    }
}