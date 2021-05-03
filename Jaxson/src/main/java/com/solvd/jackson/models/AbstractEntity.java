package com.solvd.jackson.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractEntity {
    @JsonProperty("id")
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
