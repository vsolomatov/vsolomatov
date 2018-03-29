package com.solomatoff.map;

import java.util.Calendar;
import java.util.Objects;

public class User {
    String name;
    int children;
    Calendar birthday;

    public User(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
