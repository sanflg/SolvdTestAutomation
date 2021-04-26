package com.solvd.dataBaseOnlineShop.models.individual;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.sql.Date;
import java.util.Objects;

public class Individual extends AbstractEntity {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date date;
    private int languageId;

    public Individual(){}

    //Constructor without non final attributes
    public Individual(int id,
                      String username,
                      String password,
                      String email,
                      int languageId) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.languageId = languageId;
    }

    //Full constructor
    public Individual(int id,
                      String username,
                      String password,
                      String email,
                      int languageId,
                      String firstName,
                      String lastName,
                      Date date) {
        super(id);
        this.username = username;
        this.password = password;
        this.email = email;
        this.languageId = languageId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDate() {
        return date;
    }

    public int getLanguage() {
        return languageId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", languageId=" + languageId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return languageId == that.languageId &&
                getUsername().equals(that.getUsername()) &&
                getPassword().equals(that.getPassword()) &&
                getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail(), languageId);
    }
}
