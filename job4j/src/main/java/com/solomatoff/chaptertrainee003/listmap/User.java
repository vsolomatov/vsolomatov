package com.solomatoff.chaptertrainee003.listmap;

public class User {
    private String id;
    private String name;
    private String city;

    User(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }
}
