package com.solvd.webOnlineShop.generics;

import com.solvd.webOnlineShop.lambda.IRegexCompare;
import com.solvd.webOnlineShop.lambda.IShowDate;
import com.solvd.webOnlineShop.ui.IUi;
import com.solvd.webOnlineShop.user.registeredUser.CustomerTester;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMenu<T extends Enum<T>> implements IUi<T> {
    private static final Logger logger = LogManager.getLogger(AbstractMenu.class);
    private final Date clock = new Date();

    private static IRegexCompare regex = ((pattern, input) -> {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.matches();});

    public void printAllElements(Class<T> options){

        IShowDate stringDate = Date::toString;

        logger.info("Clock: " + stringDate.showDate(clock));
        logger.info("Giving all elements options");
        Arrays.stream(options.getEnumConstants()).forEach(option->logger.info(option.ordinal() + "- " + option.toString() + "."));
    }

    @Override
    public T changeOption(Class<T> options) {
        T newTag = manageOptions(options);
        logger.info("Option selected in changeOption: " );
        return newTag;
    }

    @Override
    public T manageOptions(Class<T> options) {

        logger.info("Choosing Menu ui options");
        printAllElements(options);

        String chosenOption = String.valueOf(CustomerTester.generateRandomNumber(options.getEnumConstants().length));

        logger.info("Chosen option: " + Integer.parseInt(chosenOption));

        String pattern = "[0-9]+";
        if (!regex.validateInput(pattern, chosenOption)) {
            logger.warn("No natural number entered in Menu options");
            return manageOptions(options);
        }
        try {
            return options.getEnumConstants()[Integer.parseInt(chosenOption)];
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        logger.warn("Number entered is not between given options");
        return manageOptions(options);
    }
}
