package com.soap.dao;

import java.util.List;

// Dao stands for Data Access Object
public interface DaoInterface<T> {
    public T findById(int id);
    public List<T> findAll();
    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
