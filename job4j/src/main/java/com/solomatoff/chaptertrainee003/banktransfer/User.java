package com.solomatoff.chaptertrainee003.banktransfer;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private String passport;

    User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return this.name;
    }

    public int compareTo(@NotNull User o) {
        return (this.name.equals(o.name)) ? this.passport.compareTo(o.passport) : this.name.compareTo(o.name);
    }

    public String toString() {
        return "User " + this.hashCode() + " (Name=" + this.name + "; Passport=" + this.passport + ")";
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
        return user.name.equals(this.name) && user.passport.equals(this.passport); //сравниваем внутренности
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }

}
