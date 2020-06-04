package DAO;

import util.DBHelper;
import util.PropertyReader;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class UserDAOFactory {
    private static UserDAO userDAO;

    public UserDAO getUserDAO() {
        try {
            //("db.properties");
            String type = PropertyReader.loadProp("db.properties").getProperty("daotype");
            if ("jdbc".equals(type)) {
                userDAO = new UserJdbcDAO(DBHelper.getConnection());
                ((UserJdbcDAO) userDAO).createTable();
            } else if("hibernate".equals(type)){
                if (userDAO == null) {
                    userDAO = new UserHibernateDAO(DBHelper.getSessionFactory());
                }
            } else {
                throw new RuntimeException("error DAO layer  "+type+"__");
            }
            return userDAO;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



}
