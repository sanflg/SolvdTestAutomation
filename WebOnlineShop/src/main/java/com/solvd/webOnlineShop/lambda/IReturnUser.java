package com.solvd.webOnlineShop.lambda;

import com.solvd.webOnlineShop.user.registeredUser.Customer;

import java.util.HashSet;

@FunctionalInterface
public interface IReturnUser {
    Customer returnUser(Customer user, String userName, String password);
}
