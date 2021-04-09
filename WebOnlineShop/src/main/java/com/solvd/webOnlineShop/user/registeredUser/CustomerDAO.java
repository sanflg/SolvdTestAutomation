package com.solvd.webOnlineShop.user.registeredUser;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.generics.IAbstractDAO;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerDAO implements IAbstractDAO<Customer, String, HashMap<String,String>> {
    private static CustomerDAO customerDAO;

    //Singleton
    public static CustomerDAO getCustomerDAO() {
        if (customerDAO == null){
            customerDAO = new CustomerDAO();
        }
        return customerDAO;
    }

    private CustomerDAO() {
    }

    @Override
    public void save(Customer user) {
        DatabaseSimulation.getUsersSet().add(user);
    }

    @Override
    public Customer get(String name) {

        AtomicReference<Customer> storeCustomer = new AtomicReference<>();

        DatabaseSimulation.getUsersSet().forEach(user->{
            if (name.equals(user.getUserName())) storeCustomer.set(user);});
        return storeCustomer.get();
    }

    @Override
    public void update(Customer user, HashMap<String,String> values) {
        if (values.containsKey("userName")) user.setUserName(values.get("userName"));
        if (values.containsKey("password")) user.setPassword(values.get("password"));
        if (values.containsKey("email")) user.setEmail(values.get("email"));
        if (values.containsKey("name")) user.setName(values.get("name"));
        Date newUpdate = new Date();
        user.setLastUpdate(newUpdate);
    }

    @Override
    public void remove(Customer user) {
        DatabaseSimulation.getUsersSet().remove(user);
    }
}
