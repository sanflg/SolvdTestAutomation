package com.solvd.jackson;

import com.solvd.jackson.impl.commerce.CartDAO;
import com.solvd.jackson.models.commerce.Cart;

public class Main {
    public static void main(String[] args) {

        CartDAO cartDAO = new CartDAO();

        Cart cart = new Cart(999,999);
        cartDAO.create(cart);

        cart.setIndividualId(777);
        cartDAO.update(cart);

        cartDAO.delete(999);

        System.out.println(cartDAO.getByID(1).toString());
    }
}
