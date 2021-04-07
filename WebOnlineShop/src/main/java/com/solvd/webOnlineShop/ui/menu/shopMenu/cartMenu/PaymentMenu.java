package com.solvd.webOnlineShop.ui.menu.shopMenu.cartMenu;

import com.solvd.webOnlineShop.exceptions.ZeroCartDimensionException;
import com.solvd.webOnlineShop.payment.Cart;
import com.solvd.webOnlineShop.payment.PaymentEnum;
import com.solvd.webOnlineShop.payment.Ticket;
import com.solvd.webOnlineShop.payment.systems.Credit;
import com.solvd.webOnlineShop.payment.systems.Debit;
import com.solvd.webOnlineShop.product.Product;
import com.solvd.webOnlineShop.generics.AbstractMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PaymentMenu extends AbstractMenu<PaymentEnum>  {
    private static final Logger logger = LogManager.getLogger(PaymentMenu.class);

    public PaymentMenu(Cart cart) throws ZeroCartDimensionException{
        logger.info("Printing PaymentMenu");
        if (cart.getCartProductsList().size() == 0){
            throw new ZeroCartDimensionException("ZeroCartDimensionException Occurred: ");
        }
        PaymentEnum option = changeOption(PaymentEnum.class);
        manageCases(option);
        Ticket newTicket = new Ticket((ArrayList<Product>) cart.getCartProductsList());
        newTicket.printMe();        cart.setCartProductsList(new ArrayList<>());
        logger.info("Cart cleared of products");
    }

    public void manageCases(PaymentEnum result) {
        switch (result) {
            case CREDIT:
                Credit newCredit = new Credit();
                logger.info("Credit card Number" + newCredit.getCardNumber());
                logger.info("Credit card security Number" + newCredit.getSecurityNumber());
                newCredit.payMe();
                break;
            case DEBIT:
                Debit newDebit = new Debit();
                logger.info("Debit card Number" + newDebit.getCardNumber());
                logger.info("Debit card security Number" + newDebit.getSecurityNumber());
                newDebit.payMe();
                break;
        }
    }
}
