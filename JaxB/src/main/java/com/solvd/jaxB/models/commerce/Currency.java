package com.solvd.jaxB.models.commerce;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Currency{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement (name = "name")
    private String name;
    @XmlElement (name = "tag")
    private String tag;

    public Currency(){}

    public Currency(int id, String name, String tag) {
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
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return getName().equals(currency.getName()) &&
                getTag().equals(currency.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTag());
    }
}
