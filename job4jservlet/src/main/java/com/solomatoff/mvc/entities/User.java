package com.solomatoff.mvc.entities;

import com.solomatoff.mvc.controller.Controller;

import java.sql.Timestamp;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Timestamp createDate;
    private Integer idRole;
    private String nameRole;

    public User() {
    }

    public User(Integer id, String name, String login, String password, String email, Timestamp createDate, Integer idRole) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.idRole = idRole;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public String getNameRole() {
        Controller controller = Controller.getInstance();
        List<Role> roles = controller.getLogic().findByIdRole(new Role(this.idRole, null, null));
        if (roles.size() > 0) {
            return roles.get(0).getName();
        } else {
            return null;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        try {
            return String.format("User: [%2d] [%s] [%s] [%s] [%s] [%s] [%s] <%s>", this.id, this.name, this.login, this.password, this.email, this.createDate, this.idRole, this.getNameRole());
        } catch (Exception e) {
            return String.format("User Error getNameRole(): [%2d] [%s] [%s] [%s] [%s] [%s] [%s]", this.id, this.name, this.login, this.password, this.email, this.createDate, this.idRole);
        }
    }
}
