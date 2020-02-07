package edu.java.service.impl;

import edu.java.dao.ICustomerDao;
import edu.java.dao.hibernate.CustomerDaoImpl;
import edu.java.model.Customer;
import edu.java.service.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private ICustomerDao customerDao;

    public CustomerServiceImpl() {
        this.customerDao = new CustomerDaoImpl();
    }

    @Override
    public Long add(Customer entity) {
        return customerDao.add(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void remove(Long aLong) {
        customerDao.remove(aLong);
    }

    @Override
    public Customer getById(Long aLong) {
        return customerDao.getById(aLong);
    }

    @Override
    public List<Customer> getList() {
        return customerDao.getList();
    }
}
