package com.solomatoff.mvc.entities;

import java.util.Objects;

public class Role {
    private Integer id;
    private String name;
    private Boolean isAdmin;

    public Role() {
    }

    public Role(Integer id, String name, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(isAdmin, role.isAdmin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, isAdmin);
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + ", isAdmin=" + isAdmin + '}';
    }
}
