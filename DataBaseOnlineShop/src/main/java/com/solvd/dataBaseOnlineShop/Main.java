package com.solvd.dataBaseOnlineShop;

import com.solvd.dataBaseOnlineShop.dao.dom.impl.commerce.CartDAO;
import com.solvd.dataBaseOnlineShop.models.commerce.Cart;

public class Main {
    public static void main(String[] args) {

        CartDAO cartDAO = new CartDAO();

        Cart cart = cartDAO.getByID(1);

        Cart cart2 = new Cart(999,999);
        cartDAO.create(cart2);

        Cart cart3 = new Cart(2,999);
        cartDAO.update(cart3);

        cartDAO.delete(3);
    }
}
