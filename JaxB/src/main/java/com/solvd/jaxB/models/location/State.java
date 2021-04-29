package com.solvd.jaxB.models.location;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class State{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "Countries_id")
    private int countryId;

    public State(){}

    public State(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return countryId == state.countryId &&
                getName().equals(state.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), countryId);
    }
}
