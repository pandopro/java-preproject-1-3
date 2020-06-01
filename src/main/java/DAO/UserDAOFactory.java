package DAO;

import util.DBHelper;

import java.sql.SQLException;

public class UserDAOFactory {
    private static UserDAO userDAO;

    public static UserDAO getUserDAO() {
      //  String type = "Hibernate";
         String type = "jdbc";
        if (type.equalsIgnoreCase("jdbc")) {
            userDAO = new UserJdbcDAO(DBHelper.getMysqlConnection());
            try {
                ((UserJdbcDAO) userDAO).createTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            if (userDAO == null) {
                userDAO = new UserHibernateDAO(DBHelper.getSessionFactory().openSession());
            } else {
                userDAO.setSessionHibernate(DBHelper.getSessionFactory().openSession());
            }
        }
        return userDAO;
    }
}