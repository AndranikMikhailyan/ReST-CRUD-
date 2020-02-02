package edu.java.dao.hibernate;

import edu.java.dao.HibernateUtil;
import edu.java.dao.ICustomerDao;
import edu.java.model.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerDaoImpl implements ICustomerDao {
    @Override
    public void add(Customer entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Customer entity) {
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
        Customer customer = session.get(Customer.class, aLong);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Customer getById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, aLong);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
        session.close();
        return customers;
    }
}
