package DAO;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;
    private Configuration configuration;
    private SessionFactory sessionFactory;
//
//    public void setSessionHibernate(Session session) {
//        this.session = session;
//    }

    public UserHibernateDAO(Configuration configuration) {
        this.configuration = configuration;
        sessionFactory = getSessionFactory();
    }

    public boolean createUser(User user) {
        session = sessionFactory.openSession();
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
        return session.get(User.class, id);
    }

    public List<User> readAllUser() {
        session = sessionFactory.openSession();
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
        session = sessionFactory.openSession();
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
        session = sessionFactory.openSession();
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

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;

    }


    private SessionFactory createSessionFactory() {
        Configuration configuration = this.configuration;
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
