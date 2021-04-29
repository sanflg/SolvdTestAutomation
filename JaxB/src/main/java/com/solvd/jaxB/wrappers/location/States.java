package com.solvd.jaxB.wrappers.location;


import com.solvd.jaxB.models.location.State;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "states")
@XmlAccessorType (XmlAccessType.FIELD)
public class States{
    private final File FILE = new File("src/main/resources/xml/states.xml");

    @XmlElement(name = "state")
    private List<State> states = null;

    public File getFILE() {
        return FILE;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
