package com.solvd.webOnlineShop.ui.menu.shopMenu.cartMenu;

public enum CartMenuEnum {
    SEE_PRODUCTS("See all Products in the cart"),
    FINISH_PURCHASE("Finish the purchase"),
    DELETE_ITEM("Delete an item"),
    EXIT("Exit");

    private final String value;

    CartMenuEnum(String value) {
        this.value = value;
    }

    public String toString(){
        return this.value;
    }
}
