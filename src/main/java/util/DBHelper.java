package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static DBHelper dbHelper;
    private static SessionFactory sessionFactory;
    private DBHelper() {
    }

    public static DBHelper getInctance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userpreproject");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "271463");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }


//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            Configuration configuration = getConfiguration();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
//            builder.applySettings(configuration.getProperties());
//            ServiceRegistry serviceRegistry = builder.build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        }
//        return sessionFactory;
//    }

    //for jdbc -
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("userpreproject?").          //db name
                    append("user=root&").          //login
                    append("password=271463").     //password
                    append("&serverTimezone=UTC");   //setup server time
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}