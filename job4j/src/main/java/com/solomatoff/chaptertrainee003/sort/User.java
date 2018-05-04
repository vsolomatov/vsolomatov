package com.solomatoff.chaptertrainee003.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
        return "User " + this.hashCode() + " (Name=" + this.name + "; Age=" + this.age + ")";
    }

    /**
     * Метод сравнения
     * @param obj объект сравнения
     * @return true - если объекты равны, иначе false
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true; //ссылки идентичные, сравнение не требуется
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) obj;
        return user.name.equals(this.name) && user.age.equals(this.age); //сравниваем внутренности
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}
