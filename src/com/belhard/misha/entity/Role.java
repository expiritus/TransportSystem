package com.belhard.misha.entity;

import java.io.Serializable;
import java.util.List;

public class Role extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String role;

    private List<User> users;

    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
