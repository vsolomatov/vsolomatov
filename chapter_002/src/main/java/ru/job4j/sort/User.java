package ru.job4j.sort;

import org.jetbrains.annotations.NotNull;

public class User implements Comparable<User> {
    private String name;
    private Integer age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int compareTo(@NotNull User o) {
        return this.age.compareTo(o.age);
    }

    public String toString() {
        return "User" + this.hashCode() + "(Name=" + this.name+"; Age=" + this.age + ")";
    }
}
