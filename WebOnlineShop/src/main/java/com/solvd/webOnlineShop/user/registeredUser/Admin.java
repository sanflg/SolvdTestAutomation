package com.solvd.webOnlineShop.user.registeredUser;

import com.solvd.webOnlineShop.DatabaseSimulation;
import com.solvd.webOnlineShop.GenerateRandomData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Admin extends Customer {
    private static final Logger logger = LogManager.getLogger(GenerateRandomData.class);
    private static final int DIFFERENCE = 2;

    public Admin() {
        super();
        DatabaseSimulation.getUsersSet().add(this);
        logger.info("Admin created");
    }
}
