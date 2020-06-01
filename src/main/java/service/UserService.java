package service;

import DAO.UserDAOFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAOFactory userDAOFactory;

    private UserService() {
        userDAOFactory = new UserDAOFactory();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean createUser(String name, String email, String country) {
        userDAOFactory.getUserDAO().createUser(new User(name, email, country));
        return true;
    }

    public List<User> allUsers() {
        List<User> list = userDAOFactory.getUserDAO().readAllUser();
        return list;
    }

    public boolean deleteUser(long id) {
        return userDAOFactory.getUserDAO().delete(id);
    }

    public boolean editUser(User user) {
        return userDAOFactory.getUserDAO().editUser(user);
    }
}
