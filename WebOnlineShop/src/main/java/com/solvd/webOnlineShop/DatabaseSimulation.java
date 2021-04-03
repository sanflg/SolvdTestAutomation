package com.solvd.webOnlineShop;

import com.solvd.webOnlineShop.payment.Cart;
import com.solvd.webOnlineShop.payment.Ticket;
import com.solvd.webOnlineShop.product.Product;
import com.solvd.webOnlineShop.user.registeredUser.Customer;

import java.util.*;

public class DatabaseSimulation {
    //Due to lack of data base treatment, collections are used to store the new elements.

    private static final Set<Customer> usersSet = new HashSet<>();
    private static final Set<String> usersUserNames = new HashSet<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final List<Cart> cartsList = new ArrayList<>();
    private static final List<Ticket> ticketList = new LinkedList<>();

    public static Set<Customer> getUsersSet() {
        return usersSet;
    }

    public static Set<String> getUsersUserNames() {
        return usersUserNames;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static List<Cart> getCartsList() {
        return cartsList;
    }

    public static List<Ticket> getTicketList() {
        return ticketList;
    }
}
