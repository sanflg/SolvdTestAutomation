package com.solvd.jackson.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class Address extends AbstractEntity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("Cities_id")
    private int cityId;

    public Address(){}

    public Address(int id, String name, int cityId) {
        super(id);
        this.name = name;
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public int getCity() {
        return cityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return cityId == address.cityId &&
                getName().equals(address.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), cityId);
    }
}
