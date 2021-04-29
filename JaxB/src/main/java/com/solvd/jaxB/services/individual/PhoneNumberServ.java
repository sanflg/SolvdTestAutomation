package com.solvd.jaxB.services.individual;

import com.solvd.jaxB.dao.jaxB.impl.individual.PhoneNumberDAO;
import com.solvd.jaxB.models.individual.PhoneNumber;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhoneNumberServ implements IAbstractServ<PhoneNumber> {
    private final static Logger logger = LogManager.getLogger(PhoneNumberServ.class);
    private final PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO();

    public PhoneNumberServ() {
    }

    @Override
    public void create(PhoneNumber phoneNumber) {
        phoneNumberDAO.create(phoneNumber);
        logger.info("PhoneNumber created in DB: " + phoneNumber.toString());
    }

    @Override
    public PhoneNumber getByID(int id) {
        PhoneNumber phoneNumber = phoneNumberDAO.getByID(id);
        logger.info("Getting phoneNumber by " + id + ": "+ phoneNumber.toString());
        return phoneNumber;
    }

    @Override
    public void update(PhoneNumber phoneNumber) {
        phoneNumberDAO.update(phoneNumber);
        logger.info("Updating phoneNumber: " + phoneNumber.toString());
    }

    @Override
    public void delete(int id) {
        phoneNumberDAO.delete(id);
        logger.info("Deleting phoneNumber with id " + id);
    }
}
