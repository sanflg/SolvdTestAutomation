package com.solvd.webOnlineShop.ui.menu.mainMenu;

import com.solvd.webOnlineShop.generics.AbstractMenu;
import com.solvd.webOnlineShop.ui.menu.usersMenu.CreateUser;
import com.solvd.webOnlineShop.ui.menu.usersMenu.Login;
import com.solvd.webOnlineShop.ui.menu.shopMenu.ShopMenu;
import com.solvd.webOnlineShop.user.registeredUser.Guest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMenu extends AbstractMenu<MainMenuEnum> {
    private static final Logger logger = LogManager.getLogger(MainMenu.class);

    public MainMenu() {
        logger.info("Printing Main Menu");
        MainMenuEnum option = changeOption(MainMenuEnum.class);
        manageCases(option);
    }

    public void manageCases(MainMenuEnum result) {
        switch (result) {
            case LOGIN:
                new Login();
                break;
            case CREATE_USER:
                new CreateUser();
                break;
            case ENTER_GUEST:
                new ShopMenu(new Guest());
                break;
            case EXIT:
                break;
        }
    }
}
