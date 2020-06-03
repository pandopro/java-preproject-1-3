package DAO;


import model.User;
import org.hibernate.Session;

import java.util.List;

public interface UserDAO {

    public boolean createUser(User user);

    public User readUser(long id);

    public List<User> readAllUser();

    public boolean delete(long id);

    public boolean editUser(User user);

}


