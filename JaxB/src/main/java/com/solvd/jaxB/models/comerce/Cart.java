package com.solvd.jaxB.models.comerce;

import javax.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType (XmlAccessType.FIELD)
public class Cart{
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "Individual_id")
    private int individualId;

    public Cart(){}

    public Cart(int id, int individualId) {
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
        return "Cart{" +
                "individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return getIndividualId() == cart.getIndividualId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndividualId());
    }
}
