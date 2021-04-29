package com.solvd.jaxB.services.location;

import com.solvd.jaxB.dao.jaxB.impl.location.StateDAO;
import com.solvd.jaxB.models.location.State;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateServ implements IAbstractServ<State> {
    private final static Logger logger = LogManager.getLogger(StateServ.class);
    private final StateDAO stateDAO = new StateDAO();

    public StateServ() {
    }

    @Override
    public void create(State state) {
        stateDAO.create(state);
        logger.info("State created in DB: " + state.toString());
    }

    @Override
    public State getByID(int id) {
        State state = stateDAO.getByID(id);
        logger.info("Getting state by " + id + ": "+ state.toString());
        return state;
    }

    @Override
    public void update(State state) {
        stateDAO.update(state);
        logger.info("Updating state: " + state.toString());
    }

    @Override
    public void delete(int id) {
        stateDAO.delete(id);
        logger.info("Deleting state with id " + id);
    }
}
