package com.solvd.webOnlineShop.lambda;

@FunctionalInterface
public interface IRegexCompare {
    boolean validateInput(String patter, String input);
}
