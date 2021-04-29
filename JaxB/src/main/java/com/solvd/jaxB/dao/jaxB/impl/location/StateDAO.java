package com.solvd.jaxB.dao.jaxB.impl.location;

import com.solvd.jaxB.dao.interfaces.location.IStateDAO;
import com.solvd.jaxB.models.location.State;
import com.solvd.jaxB.wrappers.location.States;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class StateDAO implements IStateDAO {
    private static final Logger logger = LogManager.getLogger(StateDAO.class);
    private static final File FILE = new File("src/main/resources/xml/states.xml");

    @Override
    public void create(State state) {
        States states = new States();
        states.setStates(unmarshall());
        states.getStates().add(state);
        marshall(states);
    }

    @Override
    public State getByID(int id) {
        States states = new States();
        states.setStates(unmarshall());
        return states.getStates().stream()
                .filter(state -> state.getId()==id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void update(State state) {
        States states = new States();
        states.setStates(unmarshall());
        states.getStates().stream()
                .filter(stateStream -> stateStream.getId() == state.getId())
                .findAny()
                .ifPresent(editState ->{
                    editState.setName(state.getName());
                    editState.setCountryId(state.getCountryId());
                });
        marshall(states);
    }

    @Override
    public void delete(int id) {
        States states = new States();
        states.setStates(unmarshall());
        states.getStates().stream()
                .filter(stateStream -> stateStream.getId() == id)
                .findAny()
                .ifPresent(editState ->
                        states.getStates().remove(editState));
        marshall(states);
    }

    private static synchronized List<State> unmarshall(){
        States states = new States();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(States.class);
            Unmarshaller jaxUnmarshaller = jaxbContext.createUnmarshaller();
            states = (States) jaxUnmarshaller.unmarshal(FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
        return states.getStates();
    }

    private static synchronized void marshall(States states){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(States.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(states, FILE);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }
}
