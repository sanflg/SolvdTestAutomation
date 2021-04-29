package com.solvd.jaxB.models.location;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Country{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "tag")
    private String tag;

    public Country(){}

    public Country(int id, String name, String tag) {
        this.id = id;
        this.name = name;
        this.tag = tag;
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

    public String getTag() {
        return tag;
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
