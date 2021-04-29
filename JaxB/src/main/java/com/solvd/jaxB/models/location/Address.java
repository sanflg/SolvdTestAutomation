package com.solvd.jaxB.models.location;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "Cities_id")
    private int cityId;

    public Address(){}

    public Address(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
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

    public int getCityId() {
        return cityId;
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
