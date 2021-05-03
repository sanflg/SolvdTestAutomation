package com.solvd.jackson.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class State extends AbstractEntity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("Countries_id")
    private int countryId;

    public State(){}

    public State(int id, String name, int countryId) {
        super(id);
        this.name = name;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public int getCountry() {
        return countryId;
    }

    public void setName(String name) {
        this.name = name;
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
