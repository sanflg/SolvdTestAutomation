package com.solvd.webOnlineShop.ui.menu.usersMenu;

import com.solvd.webOnlineShop.generics.AbstractMenu;
import com.solvd.webOnlineShop.ui.menu.mainMenu.MainMenu;
import com.solvd.webOnlineShop.ui.EnterDataEnum;
import com.solvd.webOnlineShop.user.registeredUser.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUser extends AbstractMenu<EnterDataEnum> {
    private static final Logger logger = LogManager.getLogger(CreateUser.class);

    public CreateUser() {
        logger.info("Printing Create User Menu");
        EnterDataEnum result = changeOption(EnterDataEnum.class);
        manageCases(result);
    }

    public void manageCases(EnterDataEnum result) {
        switch (result){
            case ENTER:
                new Customer();
                break;
            case EXIT:
                new MainMenu();
                break;
        }
    }
}
