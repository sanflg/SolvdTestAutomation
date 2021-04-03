package com.solvd.webOnlineShop.user.registeredUser;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.GenerateRandomData;
import com.solvd.webOnlineShop.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class CustomerTester extends Customer{
    private static final Logger logger = LogManager.getLogger(GenerateRandomData.class);
    static Random rand = new Random();
    private String userName;
    private String password;
    private String name;
    private String email;

    public CustomerTester() {
        super();
        this.userName = "asdasd";
        this.password = "asdasd";
        this.name = "asdasd";
        this.email = "asdasd@asdasd";
        DatabaseSimulation.getUsersSet().add(this);
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public static int generateRandomNumber(){
        int number = rand.nextInt(5);
        logger.info("Number written by the dummy(between 4: " + number);
        return number;
    }

    public static int generateRandomNumber(int bound){
        int number = rand.nextInt(bound);
        logger.info("Number written by the dummy(Bounded): " + number);
        return number;
    }

    public static String generateRandomInput(){
        String[] options = {"sansan", "123123", "asdasd"};
        return generateRandomOption(options, "Input");
    }

    public static String generateRandomEmail(){
        String[] options = {"sansan@com", "123123@com", "asdasd@com"};
        return generateRandomOption(options, "Email");
    }

    public static String generateRandomSearch(){
        String[] options = {"book", "shelve", "bo", "li"};
        return generateRandomOption(options, "Search");
    }

    public static String generateRandomOption(String[] options, String field){
        int number = rand.nextInt(options.length);
        String data = options[number];
        logger.info(field + " written by the dummy: " + data);
        return data;
    }

    public static int generateTicketOption(){
        Random random = new Random();
        return random.nextInt(2);
    }


}
