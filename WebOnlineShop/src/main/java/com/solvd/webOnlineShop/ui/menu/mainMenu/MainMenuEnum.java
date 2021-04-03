package com.solvd.webOnlineShop.ui.menu.mainMenu;

public enum MainMenuEnum{
    LOGIN("Login", 1),
    CREATE_USER("Create a User", 2),
    ENTER_GUEST("Enter as a Guest(Can't buy anything)", 3),
    EXIT("Exit", 4);


    MainMenuEnum(String option, int i) {
    }

    public MainMenuEnum printMe(){
        return MainMenuEnum.valueOf(this.name());
    }
}
