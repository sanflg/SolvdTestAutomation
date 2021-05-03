package com.solvd.jaxB.models.commerce;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier {
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "Individuals_id")
    private int individualId;

    public Supplier(){}

    public Supplier(int id, int individualId) {
        this.id = id;
        this.individualId = individualId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndividualId() {
        return individualId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return getIndividualId() == supplier.getIndividualId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndividualId());
    }
}
