package com.solvd.dataBaseOnlineShop.model.individual;

import com.solvd.dataBaseOnlineShop.model.AbstractEntity;

import java.util.Objects;

public class PhoneNumber extends AbstractEntity {
    private int number;
    private int individualId;

    public PhoneNumber() {}

    public PhoneNumber(int id, int number, int individualId) {
        super(id);
        this.number = number;
        this.individualId = individualId;
    }

    public int getNumber() {
        return number;
    }

    public int getIndividualId() {
        return individualId;
    }

    public void setNumber(int number) {
        this.number = number;
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
