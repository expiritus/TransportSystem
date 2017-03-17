package com.belhard.misha.entity;

import java.io.Serializable;

public class UserToRole extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idRole;

    public UserToRole() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserToRole that = (UserToRole) o;

        return idRole == that.idRole;
    }

    @Override
    public int hashCode() {
        return idRole;
    }

    @Override
    public String toString() {
        return "UserToRole{" +
                "idRole=" + idRole +
                '}';
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
