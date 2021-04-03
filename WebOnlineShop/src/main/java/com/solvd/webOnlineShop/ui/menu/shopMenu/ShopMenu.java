package com.solvd.webOnlineShop.ui.menu.shopMenu;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.exceptions.NoObjectException;
import com.solvd.webOnlineShop.exceptions.NoProductsException;
import com.solvd.webOnlineShop.exceptions.UserNotAdminException;

import com.solvd.webOnlineShop.payment.Cart;
import com.solvd.webOnlineShop.product.Product;

import com.solvd.webOnlineShop.generics.AbstractMenuCollections;
import com.solvd.webOnlineShop.ui.menu.adminMenu.AdminMenu;

import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.ui.menu.shopMenu.cartMenu.CartMenu;
import com.solvd.webOnlineShop.user.AbstractUser;
import com.solvd.webOnlineShop.user.registeredUser.Admin;
import com.solvd.webOnlineShop.user.registeredUser.Customer;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;
import com.solvd.webOnlineShop.user.registeredUser.Guest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ShopMenu extends AbstractMenuCollections<ShopMenuEnum, ArrayList<Product>, Product> {
    private static final Logger logger = LogManager.getLogger(ShopMenu.class);

    public ShopMenu(AbstractUser user) {
        logger.info("Printing Shop Menu");
        ShopMenuEnum result = changeOption(ShopMenuEnum.class);
        manageCases(result, user);
    }

    public void manageCases(ShopMenuEnum result, AbstractUser user) {

        switch (result) {
            case SEE_PRODUCTS:
                try {
                    if (user instanceof Guest) Product.printAllProducts();
                    else printAllProductsForPurchase(user);
                }catch (NoProductsException e) {
                    logger.error(e + "There are no products in the list");
                }
                new ShopMenu(user);
                break;

            case SEARCH_PRODUCTS:

                try {
                    if (user instanceof Guest) {
                        searchProducts().forEach(product -> logger.info(product.toString()));
                    }else  searchProductsForPurchase(user);

                } catch (NoProductsException e) {
                    logger.error(e + "No products matched the search. ");
                }

                new ShopMenu(user);
                break;

            case SEE_CART:

                try{
                    checkForCart(user);
                }catch (NoObjectException e){
                    logger.error(e + "Guest users don't have carts");
                }

                new ShopMenu(user);
                break;

            case ADMIN_OPTIONS:
                try{
                    checkIfUserIsAdmin(user);
                    new AdminMenu(user);
                } catch (UserNotAdminException e) {
                    logger.error(e + "This user is not an admin.");
                }
            case CLOSE_SESSION:
                new MainMenu();
        }
    }

    public void checkForCart(AbstractUser user) throws NoObjectException{
        logger.info("Check for cart");
        if (user instanceof Guest){
            throw new NoObjectException("NoCartException occurred: ");
        } else {
            Customer newCustomer = (Customer) user;
            new CartMenu(newCustomer);
        }
    }

    public List<Product> searchProducts() throws NoProductsException{

        logger.info("Starting search");
        List<Product> newArrayOfProducts = new ArrayList<>();
        String search = CustomerTester.generateRandomSearch();

        DatabaseSimulation.getProductList().forEach(product -> {
            if (product.getName().toLowerCase().contains(search)) newArrayOfProducts.add(product);});

        if(newArrayOfProducts.size() == 0){
            throw new NoProductsException("NoProductsException Occurred: ");
        }

        return newArrayOfProducts;
    }

    public void printAllProductsForPurchase(AbstractUser user) throws NoProductsException {
        logger.info("Getting all products for purchase");
        if(DatabaseSimulation.getProductList().size() == 0){
            throw new NoProductsException("NoProductsException Occurred: ");
        }
        int purchaseOption = manageOptionsCollection((ArrayList<Product>) DatabaseSimulation.getProductList());
        addProductToCart(user, DatabaseSimulation.getProductList(), purchaseOption);
    }


    public void searchProductsForPurchase(AbstractUser user) throws NoProductsException{
        logger.info("Starting search for purchase");
        List<Product> productList = searchProducts();
        int purchaseOption = manageOptionsCollection((ArrayList<Product>) productList);
        addProductToCart(user, productList, purchaseOption);
    }

    //Cast the user into Customer to get the cart, then add the chosen element to the cart
    public void addProductToCart (AbstractUser user, List<Product> productList, int option){
        Customer newUser = (Customer) user;
        Cart cartUsed = newUser.getCart();
        Product product = productList.get(option);
        cartUsed.getCartProductsList().add(product);
    }

    public boolean checkIfUserIsAdmin(AbstractUser user) throws UserNotAdminException{
        if (user instanceof Admin) {
            return true;
        }else{
            throw new UserNotAdminException("UserNotAdminException Occurred: ");
        }
    }
}
