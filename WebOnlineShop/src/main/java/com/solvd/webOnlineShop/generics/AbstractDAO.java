package com.solvd.webOnlineShop.generics;

import com.solvd.webOnlineShop.exceptions.NoObjectException;

public interface AbstractDAO<L,T> {
    void save(L object);
    void remove(L object) throws NoObjectException;
}
