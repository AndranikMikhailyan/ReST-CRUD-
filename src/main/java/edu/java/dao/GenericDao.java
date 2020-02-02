package edu.java.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    void add(T entity);

    void update(T entity);

    void remove(ID id);

    T getById(ID id);

    List<T> getList();
}
