package com.solvd.jackson.models.individual;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.jackson.models.AbstractEntity;

import java.util.Objects;

public class Language extends AbstractEntity {
    @JsonProperty("name")
    private String name;
    @JsonProperty("tag")
    private String tag;

    public Language() {
    }

    public Language(int id, String name, String tag) {
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
        return "Language{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return getName().equals(language.getName()) &&
                getTag().equals(language.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTag());
    }
}
