package com.solvd.webOnlineShop.user;

import com.solvd.webOnlineShop.GenerateRandomData;
import com.solvd.webOnlineShop.user.registeredUser.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public abstract class AbstractUser{
    private static final Logger logger = LogManager.getLogger(GenerateRandomData.class);
    private static int DIFFERENCE;

    private String userName;
    private String password;
    private String name;
    private String email;


    public AbstractUser() {
        logger.info("User created");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getDIFFERENCE() {
        return DIFFERENCE;
    }

    @Override
    public boolean equals(Object compareUser) {
        if (this == compareUser) return true;
        if (compareUser == null || getClass() != compareUser.getClass()) return false;
        Customer that = (Customer) compareUser;
        return getName().equals(that.getName()) && getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail()) + DIFFERENCE;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "userName='" + this.getUserName() +
                ", name='" + this.getName() +
                ", email='" + this.getEmail() +
                '}';
    }
}
