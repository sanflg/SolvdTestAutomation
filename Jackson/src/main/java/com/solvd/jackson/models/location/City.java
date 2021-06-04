package com.solvd.jackson.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class City extends AbstractEntity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("States_id")
    private int stateId;

    public City(){}

    public City(int id, String name, int stateId) {
        super(id);
        this.name = name;
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public int getState() {
        return stateId;
    }

    public void setName(String name) {
        this.name = name;
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
