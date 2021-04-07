package com.solvd.webOnlineShop.ui.menu.shopMenu;

import com.solvd.webOnlineShop.ui.menu.IMenuEnum;

public enum ShopMenuEnum implements IMenuEnum {
    SEE_PRODUCTS("See all Products"),
    SEARCH_PRODUCTS("Search a Product"),
    SEE_CART("See Cart"),
    ADMIN_OPTIONS("Admin Options"),
    CLOSE_SESSION("Close Session");

    private final String value;

    ShopMenuEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
