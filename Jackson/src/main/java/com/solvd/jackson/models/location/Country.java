package com.solvd.jackson.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class Country extends AbstractEntity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("tag")
    private String tag;

    public Country(){}

    public Country(int id, String name, String tag) {
        super(id);
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return getName().equals(country.getName()) &&
                getTag().equals(country.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTag());
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
