package edu.java.dao.hibernate;

import edu.java.dao.GenericDao;
import edu.java.dao.HibernateUtil;
import edu.java.dao.IUserDao;
import edu.java.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public Long add(User entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public void update(User entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, aLong);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, aLong);
        session.close();
        return user;
    }

    @Override
    public List<User> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("FROM User", User.class).list();
        session.close();
        return users;
    }
}
