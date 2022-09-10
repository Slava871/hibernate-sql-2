package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE table_kata_usr(id INT NOT null AUTO_INCREMENT, name Varchar(30), lastName Varchar(30), age INT NOT null, PRIMARY KEY (id))";
            session.createNativeQuery(sql).addEntity(User.class);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("при создании таблицы возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable () {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE test_base.table_kata_usr";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            System.out.println("при удалении таблицы возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser (String name, String lastName,byte age){
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }catch (HibernateException e) {
            System.out.println("при добавлении пользователя  возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void removeUserById ( long id){
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e) {
            System.out.println("при удалении пользователя  возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers () {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM table_kata_usr";
            List<User> userList = session.createNativeQuery(sql).addEntity(User.class).list();
            transaction.commit();
            return userList;
        }catch (HibernateException e) {
            System.out.println("при получении  пользователей  возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void cleanUsersTable () {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.createSQLQuery("truncate table table_kata_usr").executeUpdate();
            transaction.commit();
        }catch (HibernateException e) {
            System.out.println("при очистке таблицы  возникли проблемы");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}