package com.solvd.dataBaseOnlineShop.model.location;

import com.solvd.dataBaseOnlineShop.model.AbstractEntity;

import java.util.Objects;

public class City extends AbstractEntity {
    private String name;
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
