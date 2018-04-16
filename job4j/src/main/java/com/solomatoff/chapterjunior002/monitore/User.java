package com.solomatoff.chapterjunior002.monitore;

import net.jcip.annotations.GuardedBy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

class User implements Comparable<User> {
    int id;
    int amount;

    User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    int getAmount() {
        return this.amount;
    }

    @Override
    public int compareTo(@NotNull User o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
