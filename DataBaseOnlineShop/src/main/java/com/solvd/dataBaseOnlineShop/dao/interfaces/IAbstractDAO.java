package com.solvd.dataBaseOnlineShop.dao.interfaces;

public interface IAbstractDAO<T> {
    void create(T t);
    T getByID(int id);
    void update(T t, int id);
    void delete (int id);
}
