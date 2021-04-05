package com.solvd.webOnlineShop.ui;

import com.solvd.webOnlineShop.ui.menu.IMenuEnum;

public enum EnterDataEnum implements IMenuEnum {
    ENTER("Enter Data"),
    EXIT("Exit");

    private String value;

    EnterDataEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
