package com.solomatoff.crudservlet;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String email;
    private Timestamp createDate;

    public User() {
    }

    public User(Integer id, String name, String login, String email, Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
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
}
