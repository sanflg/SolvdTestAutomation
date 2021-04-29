package com.solvd.jaxB.models.individual;

import com.solvd.jaxB.adapters.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import java.sql.Date;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Individual{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "username")
    private String username;
    @XmlElement(name = "password")
    private String password;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    @XmlElement(name = "birth_date") @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
    @XmlElement(name = "Languages_id")
    private int languageId;

    public Individual(){}

    //Constructor without non final attributes
    public Individual(int id,
                      String username,
                      String password,
                      String email,
                      int languageId) {
        this.id = id;
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
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.languageId = languageId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
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
