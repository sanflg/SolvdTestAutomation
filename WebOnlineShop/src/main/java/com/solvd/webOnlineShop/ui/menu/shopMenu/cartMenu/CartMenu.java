package com.solvd.webOnlineShop.ui.menu.shopMenu.cartMenu;

import com.solvd.webOnlineShop.exceptions.ZeroCartDimensionException;
import com.solvd.webOnlineShop.payment.Cart;
import com.solvd.webOnlineShop.product.Product;
import com.solvd.webOnlineShop.generics.AbstractMenuCollections;
import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.ui.menu.shopMenu.ShopMenu;
import com.solvd.webOnlineShop.user.AbstractUser;
import com.solvd.webOnlineShop.user.registeredUser.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CartMenu extends AbstractMenuCollections<CartMenuEnum, ArrayList<Product>, Product> {
    private static final Logger logger = LogManager.getLogger(MainMenu.class);
    private AbstractUser user;

    public CartMenu(Customer user) {
        logger.info("Printing Cart Menu");
        this.user = user;
        CartMenuEnum result = changeOption(CartMenuEnum.class);
        manageCases(result, user);
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public void manageCases(CartMenuEnum result, Customer user) {

        Cart newCart = user.getCart();

        switch (result) {

            case SEE_PRODUCTS:
                try {
                    seeAllProductsInCart(newCart);
                } catch (ZeroCartDimensionException e) {
                    logger.error(e + "This cart don't have products");
                }
                break;

            case FINISH_PURCHASE:
                try {
                    new PaymentMenu(newCart);
                } catch (ZeroCartDimensionException e) {
                    logger.error(e + "This cart don't have products");
                }
                break;

            case DELETE_ITEM:
                try {
                    deleteItem(newCart);
                } catch (ZeroCartDimensionException e) {
                    logger.error(e + "This cart don't have products");
                }
                break;
            case EXIT:
                break;

            default:
                new ShopMenu(this.getUser());
                break;
        }
    }

    public void seeAllProductsInCart(Cart cart) throws ZeroCartDimensionException {
        logger.info("Looking trough all the elements in the cart");

        double total = 0;

        if (cart.getCartProductsList().size() == 0){
            throw new ZeroCartDimensionException("ZeroCartDimensionException Occurred: ");
        }

        cart.getCartProductsList().forEach((product) -> {
        cart.affectPrice(product.getPrice());logger.info(product.toString());});

        if (cart.getCartProductsList().size() < 1) {
            logger.warn("No items in the cart");
        }
        logger.info("Total price to pay in see all products in cart: "+ total);
    }

    public void deleteItem(Cart cart) throws ZeroCartDimensionException {
        logger.info("Trying to delete a element");
        if(cart.getCartProductsList().size() == 0){
            throw new ZeroCartDimensionException("ZeroCartDimensionException Occurred: ");
        }

        int option = manageOptionsCollection((ArrayList<Product>) cart.getCartProductsList());
        Product product = cart.getCartProductsList().get(option);
        cart.getCartProductsList().remove(option);
        cart.affectPrice(-product.getPrice());
        logger.info("Object dropped" + product.toString());
    }
}
