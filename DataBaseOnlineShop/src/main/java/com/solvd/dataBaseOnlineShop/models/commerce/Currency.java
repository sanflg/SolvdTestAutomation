package com.solvd.dataBaseOnlineShop.models.commerce;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.util.Objects;

public class Currency extends AbstractEntity {
    private String name;
    private String tag;

    public Currency(){}

    public Currency(int id, String name, String tag) {
        super(id);
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public void setName(String name) {
        this.name = name;
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
