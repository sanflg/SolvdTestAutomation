package com.solvd.jaxB.models.individual;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class IndividualAddress{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "number")
    private int number;
    @XmlElement(name = "Addresses_id")
    private int addressId;
    @XmlElement(name = "Individuals_id")
    private int individualId;

    public IndividualAddress(){}

    public IndividualAddress(int id,
                             int number,
                             int addressId,
                             int individualId) {
        this.id = id;
        this.number = number;
        this.addressId = addressId;
        this.individualId = individualId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getIndividualId() {
        return individualId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "IndividualAddress{" +
                "number=" + number +
                ", addressId=" + addressId +
                ", individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualAddress that = (IndividualAddress) o;
        return getNumber() == that.getNumber() &&
                addressId == that.addressId &&
                individualId == that.individualId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), addressId, individualId);
    }
}
