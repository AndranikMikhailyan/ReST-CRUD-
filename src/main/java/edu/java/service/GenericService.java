package edu.java.service;

import java.util.List;

public interface GenericService<T, ID> {

    ID add(T entity);

    void update(T entity);

    void remove(ID id);

    T getById(ID id);

    List<T> getList();
}
