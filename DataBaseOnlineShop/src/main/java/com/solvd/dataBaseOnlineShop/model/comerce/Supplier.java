package com.solvd.dataBaseOnlineShop.model.comerce;

import com.solvd.dataBaseOnlineShop.model.AbstractEntity;

import java.util.Objects;

public class Supplier extends AbstractEntity {
    private int individualId;

    public Supplier(){}

    public Supplier(int id, int individualId) {
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
