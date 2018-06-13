package com.solomatoff.mvc.entities;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return String.format("Role: [%2d] [%s] [%s]", this.id, this.name, this.isAdmin);
    }
}
