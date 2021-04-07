package com.solvd.webOnlineShop.generics;

import com.solvd.webOnlineShop.exceptions.NoObjectException;

public interface IAbstractDAO<L,T, S> {
    void save(L object);
    L get(T variable);
    void update(L object, S values);
    void remove(L object) throws NoObjectException;
}
