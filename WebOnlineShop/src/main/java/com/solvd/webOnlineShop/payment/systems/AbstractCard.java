package com.solvd.webOnlineShop.payment.systems;

import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public abstract class AbstractCard implements Payable{
    private static final Logger logger = LogManager.getLogger(MainMenu.class);
    private static int DIFFERENCE;
    private String cardNumber;
    private String securityNumber;

    public AbstractCard() {
        logger.info("New card generated");
        this.cardNumber = getDataCardNumber();
        this.securityNumber = getDataSecurityNumber();
    }

    public static int getDIFFERENCE() {
        return DIFFERENCE;
    }

    public static void setDIFFERENCE(int DIFFERENCE) {
        AbstractCard.DIFFERENCE = DIFFERENCE;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    @Override
    public boolean equals(Object comparedPayable) {
        if (this == comparedPayable) return true;
        if (comparedPayable == null || getClass() != comparedPayable.getClass()) return false;
        AbstractCard card = (AbstractCard) comparedPayable;
        return getCardNumber().equals(card.getCardNumber()) && getSecurityNumber().equals(card.getSecurityNumber());
    }

    @Override
    public int hashCode() {
        return MULTIPLY * Objects.hash(getCardNumber(), getSecurityNumber() + DIFFERENCE);
    }

    @Override
    public void payMe() {
        logger.info("Processing payment...");
        logger.info("payment was successful! ");
    }

    public String getDataCardNumber() {
        logger.info("Enter the card number: ");
        return String.valueOf(CustomerTester.generateRandomNumber());
    }

    public String getDataSecurityNumber() {
        logger.info("Enter the security code: ");
        return String.valueOf(CustomerTester.generateRandomNumber());
    }
}
