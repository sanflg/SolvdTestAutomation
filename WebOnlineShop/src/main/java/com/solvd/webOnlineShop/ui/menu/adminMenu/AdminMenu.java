package com.solvd.webOnlineShop.ui.menu.adminMenu;

import com.solvd.webOnlineShop.generics.AbstractMenu;
import com.solvd.webOnlineShop.ui.menu.shopMenu.ShopMenu;
import com.solvd.webOnlineShop.user.AbstractUser;
import com.solvd.webOnlineShop.user.registeredUser.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminMenu extends AbstractMenu<AdminMenuEnum> {
    private static final Logger logger = LogManager.getLogger(AdminMenu.class);
    private AbstractUser user;

    public AdminMenu (AbstractUser user){
        logger.info("Printing Admin Menu");
        this.user = user;
        AdminMenuEnum result = changeOption(AdminMenuEnum.class);
        manageCases(result, user);
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public void manageCases(AdminMenuEnum result, AbstractUser user) {
        switch (result){
            case CREATE_ADMIN:
                new Admin();
                new AdminMenu(user);
                break;
            case EXIT:
                new ShopMenu(this.getUser());
                break;
        }
    }

    public void createNewProduct(){

    }
}
