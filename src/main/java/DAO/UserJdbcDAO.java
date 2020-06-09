package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    Connection connection;

    public UserJdbcDAO(Connection myConnection) {
        connection = myConnection;
    }

    @Override
    public boolean createUser(User user) {
        try {
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user(name , email, country) values(?,?, ?)");
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getCountry());
                preparedStatement.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException throwables) {
                connection.rollback();// использование с одной транзакцией избыточно?
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public List<User> readAllUser() {
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from user");
            ps.executeQuery();
            ResultSet result = ps.getResultSet();
            while (result.next()) {
                if (!result.wasNull()) {
                    list.add(new User(Long.parseLong(result.getString("id")), result.getString("name"), result.getString("email"), result.getString("country")));
                }
            }
            result.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delete(long id) {
        try {
            try {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editUser(User user) {
        try {
            try {
                connection.setAutoCommit(false);
                PreparedStatement ps = connection.prepareStatement("update user set name=?,email=?,country=? where id=?");
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getCountry());
                ps.setLong(4, user.getId());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists user(id bigint auto_increment,name varchar(256) NOT NULL, email varchar(256) NOT NULL, country varchar(256), primary key (id))");
        stmt.close();
    }
}