package com.solvd.jackson.models.commerce;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class Cart extends AbstractEntity {
    @JsonProperty("Individuals_id")
    private int individualId;

    public Cart(){}

    public Cart(int id, int individualId) {
        super(id);
        this.individualId = individualId;
    }

    public int getIndividualId() {
        return individualId;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "Cart " + getId() + "{" +
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
