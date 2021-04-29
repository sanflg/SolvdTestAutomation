package com.solvd.jaxB.services.individual;

import com.solvd.jaxB.dao.jaxB.impl.individual.IndividualDAO;
import com.solvd.jaxB.models.individual.Individual;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IndividualServ implements IAbstractServ<Individual> {
    private final static Logger logger = LogManager.getLogger(IndividualServ.class);
    private final IndividualDAO individualDAO = new IndividualDAO();

    public IndividualServ() {
    }

    @Override
    public void create(Individual individual) {
        individualDAO.create(individual);
        logger.info("Individual created in DB: " + individual.toString());
    }

    @Override
    public Individual getByID(int id) {
        Individual individual = individualDAO.getByID(id);
        logger.info("Getting individual by " + id + ": "+ individual.toString());
        return individual;
    }

    @Override
    public void update(Individual individual) {
        individualDAO.update(individual);
        logger.info("Updating individual: " + individual.toString());
    }

    @Override
    public void delete(int id) {
        individualDAO.delete(id);
        logger.info("Deleting individual with id " + id);
    }
}
