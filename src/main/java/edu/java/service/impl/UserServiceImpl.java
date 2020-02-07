package edu.java.service.impl;

import edu.java.dao.IUserDao;
import edu.java.dao.hibernate.UserDaoImpl;
import edu.java.model.User;
import edu.java.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Long add(User entity) {
        return userDao.add(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void remove(Long aLong) {
        userDao.remove(aLong);
    }

    @Override
    public User getById(Long aLong) {
        return userDao.getById(aLong);
    }

    @Override
    public List<User> getList() {
        return userDao.getList();
    }
}
