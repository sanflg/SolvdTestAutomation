package com.solvd.jaxB.dao.interfaces;

public interface IAbstractDAO<T> {
    void create(T t);
    T getByID(int id);
    void update(T t);
    void delete (int id);
}
