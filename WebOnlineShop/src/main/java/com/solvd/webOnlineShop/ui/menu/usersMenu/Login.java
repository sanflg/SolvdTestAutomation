package com.solvd.webOnlineShop.ui.menu.usersMenu;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.Main;
import com.solvd.webOnlineShop.exceptions.NoObjectException;

import com.solvd.webOnlineShop.generics.AbstractMenu;
import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.ui.menu.shopMenu.ShopMenu;
import com.solvd.webOnlineShop.ui.EnterDataEnum;

import com.solvd.webOnlineShop.user.registeredUser.Customer;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login extends AbstractMenu<EnterDataEnum> {
    private static final Logger logger = LogManager.getLogger(Login.class);

    public Login() {
        logger.info("Printing Login Menu");
        EnterDataEnum result = changeOption(EnterDataEnum.class);
        manageCases(result);
    }

    public void manageCases(EnterDataEnum result) {
        switch (result){
            case ENTER:
                try {
                    Customer user = validateUser();
                    logger.info("Successful login");
                    new ShopMenu(user);
                } catch (NoObjectException e) {
                    logger.warn("No user found in matching");
                    new Login();
                }
                break;
            case EXIT:
                new MainMenu();
                break;
        }
    }

    //Validate the login
    static public Customer validateUser() throws NoObjectException {
        logger.info("entered in validate user");

        String userName;
        String password;

        //When dummy is no longer needed, leave only Customer options with correct input treatment (at the moment
        //is using a BufferReader, check better implementation).
        if (Main.isIsDummyOn()){
            userName = CustomerTester.generateRandomInput();
            password = CustomerTester.generateRandomInput();
        }else{
            userName = Customer.writeAlphaNumeric("Username (Only letters and numbers, without spaces): ");
            password = Customer.writeAlphaNumeric("Password (Only letters and numbers, without spaces): ");
        }

        var wrapper = new Object(){ Customer userExist; };

        DatabaseSimulation.getUsersSet().forEach(user->{
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) wrapper.userExist=user;});

        if (wrapper.userExist == null){
            throw new NoObjectException("NoUserFoundException occurred: ");
        }else{
            logger.info("Correctly logged");
            return wrapper.userExist;
        }
    }
}
