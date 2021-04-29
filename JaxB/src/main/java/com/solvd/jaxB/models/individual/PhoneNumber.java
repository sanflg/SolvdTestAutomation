package com.solvd.jaxB.models.individual;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumber{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "number")
    private int number;
    @XmlElement(name = "Individuals_id")
    private int individualId;

    public PhoneNumber() {}

    public PhoneNumber(int id, int number, int individualId) {
        this.id = id;
        this.number = number;
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

    public int getIndividualId() {
        return individualId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number=" + number +
                ", individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return getNumber() == that.getNumber() &&
                getIndividualId() == that.getIndividualId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getIndividualId());
    }
}
