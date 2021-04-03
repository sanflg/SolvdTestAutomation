package com.solvd.webOnlineShop.ui;

public interface IUi<T> {

    int NUMBER = 0;

    T changeOption(Class<T> options);

    T manageOptions(Class<T> options);
}
