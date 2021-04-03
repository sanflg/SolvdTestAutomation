package com.solvd.webOnlineShop.exceptions;

public class UserNotAdminException extends Exception{
    public UserNotAdminException (String message){
        super(message);
    }
}
