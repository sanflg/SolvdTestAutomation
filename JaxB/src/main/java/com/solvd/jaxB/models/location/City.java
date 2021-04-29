package com.solvd.jaxB.models.location;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class City{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "States_id")
    private int stateId;

    public City(){}

    public City(int id, String name, int stateId) {
        this.id = id;
        this.name = name;
        this.stateId = stateId;
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

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", stateId=" + stateId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return stateId == city.stateId &&
                getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), stateId);
    }
}
