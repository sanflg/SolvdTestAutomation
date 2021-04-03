package com.solvd.webOnlineShop.payment;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.product.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Cart{
    private static final Logger logger = LogManager.getLogger(Cart.class);
    private List<Product> cartProductsList = new ArrayList<>();
    private double price = 0;

    public Cart() {
        DatabaseSimulation.getCartsList().add(this);
        logger.info("New cart created");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void affectPrice(double price){
        this.price = this.price + price;
    }

    public List<Product> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<Product> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }
}
