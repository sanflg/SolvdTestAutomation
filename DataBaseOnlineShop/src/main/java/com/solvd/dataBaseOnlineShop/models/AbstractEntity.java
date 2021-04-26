package com.solvd.dataBaseOnlineShop.models;

public abstract class AbstractEntity {
    private int id;

    protected AbstractEntity(int id) {
        this.id = id;
    }

    protected AbstractEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
