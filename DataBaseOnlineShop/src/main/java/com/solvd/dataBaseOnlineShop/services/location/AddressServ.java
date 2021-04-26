package com.solvd.dataBaseOnlineShop.services.location;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.location.AddressDAO;
import com.solvd.dataBaseOnlineShop.models.location.Address;
import com.solvd.dataBaseOnlineShop.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressServ implements IAbstractServ<Address> {
    private final static Logger logger = LogManager.getLogger(AddressServ.class);
    private final AddressDAO addressDAO = new AddressDAO();

    public AddressServ() {
    }

    @Override
    public void create(Address address) {
        addressDAO.create(address);
        logger.info("Address created in DB: " + address.toString());
    }

    @Override
    public Address getByID(int id) {
        Address address = addressDAO.getByID(id);
        logger.info("Getting address by " + id + ": "+ address.toString());
        return address;
    }

    @Override
    public void update(Address address) {
        addressDAO.update(address);
        logger.info("Updating address: " + address.toString());
    }

    @Override
    public void delete(int id) {
        addressDAO.delete(id);
        logger.info("Deleting address with id " + id);
    }
}
