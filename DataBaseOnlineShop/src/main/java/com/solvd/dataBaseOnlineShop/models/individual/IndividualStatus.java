package com.solvd.dataBaseOnlineShop.models.individual;

import com.solvd.dataBaseOnlineShop.models.AbstractEntity;

import java.util.Objects;

public class IndividualStatus extends AbstractEntity {
    private boolean isAdmin;
    private boolean isNew;
    private boolean isBanned;
    private int individualId;

    public IndividualStatus(){}

    public IndividualStatus(int id,
                            boolean isAdmin,
                            boolean isNew,
                            boolean isBanned,
                            int individualId) {
        super(id);
        this.isAdmin = isAdmin;
        this.isNew = isNew;
        this.isBanned = isBanned;
        this.individualId = individualId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public int getIndividualId() {
        return individualId;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setIndividualId(int individualId) {
        this.individualId = individualId;
    }

    @Override
    public String toString() {
        return "IndividualStatus{" +
                "isAdmin=" + isAdmin +
                ", isNew=" + isNew +
                ", isBanned=" + isBanned +
                ", individualId=" + individualId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualStatus that = (IndividualStatus) o;
        return isAdmin() == that.isAdmin() &&
                isNew() == that.isNew() &&
                isBanned() == that.isBanned() &&
                getIndividualId() == that.getIndividualId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAdmin(),
                isNew(),
                isBanned(),
                getIndividualId());
    }
}
