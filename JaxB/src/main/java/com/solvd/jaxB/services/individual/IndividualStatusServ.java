package com.solvd.jaxB.services.individual;

import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualStatusDAO;
import com.solvd.jaxB.models.individual.IndividualStatus;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IndividualStatusServ implements IAbstractServ<IndividualStatus> {
    private final static Logger logger = LogManager.getLogger(IndividualStatusServ.class);
    private final IndividualStatusDAO individualStatusDAO = new IndividualStatusDAO();

    public IndividualStatusServ() {
    }

    @Override
    public void create(IndividualStatus individualStatus) {
        individualStatusDAO.create(individualStatus);
        logger.info("IndividualStatus created in DB: " + individualStatus.toString());
    }

    @Override
    public IndividualStatus getByID(int id) {
        IndividualStatus individualStatus = individualStatusDAO.getByID(id);
        logger.info("Getting individualStatus by " + id + ": "+ individualStatus.toString());
        return individualStatus;
    }

    @Override
    public void update(IndividualStatus individualStatus) {
        individualStatusDAO.update(individualStatus);
        logger.info("Updating individualStatus: " + individualStatus.toString());
    }

    @Override
    public void delete(int id) {
        individualStatusDAO.delete(id);
        logger.info("Deleting individualStatus with id " + id);
    }
}
