package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {

    // get a session factory
    public static SessionFactory getSessionFactory() {
        Configuration configuration = createConfig();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }


    private static Configuration createConfig() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        // Hibernate settings equivalent to hibernate.cfg.xml's properties

        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test_base");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "damuhe65");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        //отключаем автокоммиты
        settings.put(Environment.AUTOCOMMIT, "hibernate.connection.autocommit=true");



        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        return configuration;
    }
}