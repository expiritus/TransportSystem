package com.belhard.misha.entity;

import java.io.Serializable;

public class UserToRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idRole;

    public UserToRole() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserToRole that = (UserToRole) o;

        if (id != that.id) return false;
        return idRole == that.idRole;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idRole;
        return result;
    }

    @Override
    public String toString() {
        return "UserToRole{" +
                "id=" + id +
                ", idRole=" + idRole +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
