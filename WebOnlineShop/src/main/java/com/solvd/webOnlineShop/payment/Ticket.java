package com.solvd.webOnlineShop.payment;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.product.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Ticket {
    private static final Logger logger = LogManager.getLogger(Ticket.class);
    private List<Product> cartProductsList;
    private final Date date;

    public Ticket(ArrayList<Product> cartProductsList) {
        this.cartProductsList = cartProductsList;
        DatabaseSimulation.getTicketList().add(this);
        logger.info("New ticket created");
        date = new Date();
    }

    public List<Product> getCartProductsList() {
        return cartProductsList;
    }
    public void setCartProductsList(ArrayList<Product> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    @Override
    public boolean equals(Object comparedTicket) {
        if (this == comparedTicket) return true;
        if (comparedTicket == null || getClass() != comparedTicket.getClass()) return false;
        Ticket ticket = (Ticket) comparedTicket;
        return Objects.equals(getCartProductsList(), ticket.getCartProductsList());
    }

    public Date getDate() {
        return date;
    }

    public String getStringDate(){
        return getDate().toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartProductsList());
    }

    public void printMe() {
        logger.info("ticket generated and wrote with date: " + getStringDate());
    }
    
    public static void printAllProductsIn() {
        logger.info("Method for printing all the products invoked");
    }
}
