package com.solvd.webOnlineShop.user.registeredUser;

import com.solvd.webOnlineShop.GenerateRandomData;
import com.solvd.webOnlineShop.user.AbstractUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Guest extends AbstractUser {
    private static final Logger logger = LogManager.getLogger(GenerateRandomData.class);

    public Guest() {
        super();
        logger.info("Guest created");
    }
}
