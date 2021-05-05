package com.solvd.jaxB.services.individual;

import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualAddressDAO;
import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualDAO;
import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualStatusDAO;
import com.solvd.jaxB.dao.jaxB.impl.individual.PhoneNumberDAO;
import com.solvd.jaxB.models.individual.Individual;
import com.solvd.jaxB.models.individual.IndividualAddress;
import com.solvd.jaxB.models.individual.IndividualStatus;
import com.solvd.jaxB.models.individual.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IndividualServ {
    private final static Logger logger = LogManager.getLogger(IndividualServ.class);
    private final IndividualDAO individualDAO = new IndividualDAO();
    private final IndividualAddressDAO individualAddressDAO = new IndividualAddressDAO();
    private final IndividualStatusDAO individualStatusDAO = new IndividualStatusDAO();
    private final PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO();

    //Individual
    public void create(Individual individual,
                       IndividualStatus individualStatus,
                       IndividualAddress individualAddress,
                       PhoneNumber phoneNumber) {
        individualDAO.create(individual);
        individualStatusDAO.create(individualStatus);
        individualAddressDAO.create(individualAddress);
        phoneNumberDAO.create(phoneNumber);
        logger.info("Individual created in DB: " + individual.toString()+ "\n" +
                "IndividualStatus created in DB: " + individualStatus.toString()+"\n" +
                "IndividualAddress created in DB: " + individualAddress.toString()+"\n" +
                "PhoneNumber created in DB: " + phoneNumber.toString());
    }

    public void create(Individual individual,
                       IndividualStatus individualStatus,
                       IndividualAddress individualAddress){
        individualDAO.create(individual);
        individualStatusDAO.create(individualStatus);
        individualAddressDAO.create(individualAddress);
        logger.info("Individual created in DB: " + individual.toString()+ "\n" +
                "IndividualStatus created in DB: " + individualStatus.toString()+"\n" +
                "IndividualAddress created in DB: " + individualAddress.toString());
    }

    public void create(Individual individual,
                       IndividualStatus individualStatus) {
        individualDAO.create(individual);
        individualStatusDAO.create(individualStatus);
        logger.info("Individual created in DB: " + individual.toString()+ "\n" +
                "IndividualStatus created in DB: " + individualStatus.toString());
    }

    public Individual getByID(int id) {
        Individual individual = individualDAO.getByID(id);
        logger.info("Getting individual by " + id + ": "+ individual.toString());
        return individual;
    }

    public void update(Individual individual) {
        individualDAO.update(individual);
        logger.info("Updating individual: " + individual.toString());
    }

    public void delete(int id) {
        individualDAO.delete(id);
        logger.info("Deleting individual with id " + id);
    }

    //Address
    public void createAddress(IndividualAddress individualAddress) {
        individualAddressDAO.create(individualAddress);
        logger.info("IndividualAddress created in DB: " + individualAddress.toString());
    }

    public IndividualAddress getAddressByID(int id) {
        IndividualAddress individualAddress = individualAddressDAO.getByID(id);
        logger.info("Getting individualAddress by " + id + ": "+ individualAddress.toString());
        return individualAddress;
    }

    public void updateAddress(IndividualAddress individualAddress) {
        individualAddressDAO.update(individualAddress);
        logger.info("Updating individualAddress: " + individualAddress.toString());
    }

    public void deleteAddress(int id) {
        individualAddressDAO.delete(id);
        logger.info("Deleting individualAddress with id " + id);
    }

    //Status
    public void createStatus(IndividualStatus individualStatus) {
        individualStatusDAO.create(individualStatus);
        logger.info("IndividualStatus created in DB: " + individualStatus.toString());
    }

    public IndividualStatus getStatusByID(int id) {
        IndividualStatus individualStatus = individualStatusDAO.getByID(id);
        logger.info("Getting individualStatus by " + id + ": "+ individualStatus.toString());
        return individualStatus;
    }

    public void updateStatus(IndividualStatus individualStatus) {
        individualStatusDAO.update(individualStatus);
        logger.info("Updating individualStatus: " + individualStatus.toString());
    }

    public void deleteStatus(int id) {
        individualStatusDAO.delete(id);
        logger.info("Deleting individualStatus with id " + id);
    }

    //PhoneNumber
    public void createPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberDAO.create(phoneNumber);
        logger.info("PhoneNumber created in DB: " + phoneNumber.toString());
    }

    public PhoneNumber getPhoneNumberByID(int id) {
        PhoneNumber phoneNumber = phoneNumberDAO.getByID(id);
        logger.info("Getting phoneNumber by " + id + ": "+ phoneNumber.toString());
        return phoneNumber;
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberDAO.update(phoneNumber);
        logger.info("Updating phoneNumber: " + phoneNumber.toString());
    }

    public void deletePhoneNumber(int id) {
        phoneNumberDAO.delete(id);
        logger.info("Deleting phoneNumber with id " + id);
    }
}
