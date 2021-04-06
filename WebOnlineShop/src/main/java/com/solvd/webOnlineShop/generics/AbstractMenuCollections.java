package com.solvd.webOnlineShop.generics;

import com.solvd.webOnlineShop.lambda.IRegexCompare;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class
    AbstractMenuCollections<T extends Enum<T>, L extends Collection<M>, M>
    extends AbstractMenu<T>{

    private static final Logger logger = LogManager.getLogger(AbstractMenuCollections.class);

    private static IRegexCompare regex = ((pattern, input) -> {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.matches();});

    public void printAllElementsCollection(L options){
        logger.info("Giving all elements options with String[] parameter");
        AtomicInteger number = new AtomicInteger();
        options.forEach((m)->logger.info(number.getAndIncrement() + "- " + m.toString()));}

    public int manageOptionsCollection(L options) {

        logger.info("Choosing Menu UI options with ArrayList of Products parameter");
        printAllElementsCollection(options);

        String chosenOption = String.valueOf(CustomerTester.generateRandomNumber(options.size()));

        int newOption;

        String pattern = "[0-9]+";
        if (!regex.validateInput(pattern, chosenOption)){
            logger.warn("No natural number entered in Menu options");
            newOption = manageOptionsCollection(options);
        }else{
            int chosenOptionInt = Integer.parseInt(chosenOption);
            if(chosenOptionInt > options.size() || chosenOptionInt <= 0){
                logger.warn("Number entered is not between given options");
                newOption = manageOptionsCollection(options) ;
            }else{
                newOption = chosenOptionInt;
            }
        }
        return newOption;
    }
}
