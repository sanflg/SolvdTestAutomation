package com.solvd.webOnlineShop.ui.menu.adminMenu;

import com.solvd.webOnlineShop.ui.menu.IMenuEnum;

public enum AdminMenuEnum implements IMenuEnum {
    CREATE_ADMIN("Create a new Admin User"),
    CREATE_PRODUCT("Create a new Product"),
    EXIT("Exit");

    private String value;

    AdminMenuEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
