package com.solvd.jaxB.models.individual;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class IndividualStatus {
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "IsAdmin")
    private boolean isAdmin;
    @XmlElement(name = "IsNew")
    private boolean isNew;
    @XmlElement(name = "IsBanned")
    private boolean isBanned;
    @XmlElement(name = "Individuals_id")
    private int individualId;

    public IndividualStatus(){}

    public IndividualStatus(int id,
                            boolean isAdmin,
                            boolean isNew,
                            boolean isBanned,
                            int individualId) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.isNew = isNew;
        this.isBanned = isBanned;
        this.individualId = individualId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public int getIndividualId() {
        return individualId;
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
