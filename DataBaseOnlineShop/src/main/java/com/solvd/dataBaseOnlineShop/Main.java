package com.solvd.dataBaseOnlineShop;

import com.solvd.dataBaseOnlineShop.model.location.Country;
import com.solvd.dataBaseOnlineShop.model.location.State;

public class Main {
    public static void main(String[] args) {
        Country a = new Country(5,"asd", "asd");
        State b = new State(3, "asd", 123);

        System.out.println(b.toString());
    }
}
