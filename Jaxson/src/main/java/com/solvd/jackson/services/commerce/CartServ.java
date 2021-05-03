package com.solvd.jackson.services.commerce;

import com.solvd.jackson.impl.commerce.CartDAO;
import com.solvd.jackson.models.commerce.Cart;
import com.solvd.jackson.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartServ implements IAbstractServ<Cart> {
    private final static Logger logger = LogManager.getLogger(CartServ.class);
    private final CartDAO cartDAO = new CartDAO();

    public CartServ() {
    }

    @Override
    public void create(Cart cart) {
        cartDAO.create(cart);
        logger.info("Cart created in DB: " + cart.toString());
    }

    @Override
    public Cart getByID(int id) {
        Cart cart = cartDAO.getByID(id);
        logger.info("Getting cart by " + id + ": "+ cart.toString());
        return cart;
    }

    @Override
    public void update(Cart cart) {
        cartDAO.update(cart);
        logger.info("Updating cart: " + cart.toString());
    }

    @Override
    public void delete(int id) {
        cartDAO.delete(id);
        logger.info("Deleting cart with id " + id);
    }
}
