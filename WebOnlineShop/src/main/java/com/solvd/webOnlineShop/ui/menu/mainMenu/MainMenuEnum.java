package com.solvd.webOnlineShop.ui.menu.mainMenu;

public enum MainMenuEnum{
    LOGIN("Login"),
    CREATE_USER("Create a User"),
    ENTER_GUEST("Enter as a Guest(Can't buy anything)"),
    EXIT("Exit");

    private final String value;

    MainMenuEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
