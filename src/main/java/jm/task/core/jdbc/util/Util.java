package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_base";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "damuhe65";


    //здесь не получилось применить try  с ресурсами из-за return
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.printf("connect error!!! все пропало!!");
        }
        return connection;
    }


}
