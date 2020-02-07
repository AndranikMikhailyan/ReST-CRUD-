package edu.java.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    ID add(T entity);

    void update(T entity);

    void remove(ID id);

    T getById(ID id);

    List<T> getList();
}
