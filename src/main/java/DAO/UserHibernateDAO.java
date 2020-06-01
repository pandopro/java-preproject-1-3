package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public void setSessionHibernate(Session session) {
        this.session = session;
    }

    public boolean createUser(User user) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }

        return true;

    }

    public User readUser(long id) {
        return new User();
    }

    public List<User> readAllUser() {
        List<User> userList = null;
        Transaction transaction = session.beginTransaction();
        try {
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new HibernateException("");
        } finally {
            session.close();
        }

        return userList;
    }

    public boolean delete(long id) {
        Transaction transaction = session.beginTransaction();
        try {

            session.delete(session.load(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;

        } finally {
            session.close();

        }
        return true;
    }

    public boolean editUser(User user) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            return false;

        } finally {
            session.close();
        }
        return true;
    }


}
