package com.solvd.jaxB.services.individual;

import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualAddressDAO;
import com.solvd.jaxB.models.individual.IndividualAddress;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IndividualAddressServ implements IAbstractServ<IndividualAddress> {
    private final static Logger logger = LogManager.getLogger(IndividualAddressServ.class);
    private final IndividualAddressDAO individualAddressDAO = new IndividualAddressDAO();

    public IndividualAddressServ() {
    }

    @Override
    public void create(IndividualAddress individualAddress) {
        individualAddressDAO.create(individualAddress);
        logger.info("IndividualAddress created in DB: " + individualAddress.toString());
    }

    @Override
    public IndividualAddress getByID(int id) {
        IndividualAddress individualAddress = individualAddressDAO.getByID(id);
        logger.info("Getting individualAddress by " + id + ": "+ individualAddress.toString());
        return individualAddress;
    }

    @Override
    public void update(IndividualAddress individualAddress) {
        individualAddressDAO.update(individualAddress);
        logger.info("Updating individualAddress: " + individualAddress.toString());
    }

    @Override
    public void delete(int id) {
        individualAddressDAO.delete(id);
        logger.info("Deleting individualAddress with id " + id);
    }
}
