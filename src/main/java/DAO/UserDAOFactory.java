package DAO;

import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class UserDAOFactory {
    private static UserDAO userDAO;

    public UserDAO getUserDAO() {
        try {
            Properties prop = loadProp();
            String type = prop.getProperty("daotype");
            if ("jdbc".equals(type)) {
                userDAO = new UserJdbcDAO(DBHelper.getConnection());
                ((UserJdbcDAO) userDAO).createTable();
            } else if("hibernate".equals(type)){
                if (userDAO == null) {
                    userDAO = new UserHibernateDAO(DBHelper.getConfiguration());
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

    private Properties loadProp() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        prop.load(inputStream);
        return prop;
    }

}
