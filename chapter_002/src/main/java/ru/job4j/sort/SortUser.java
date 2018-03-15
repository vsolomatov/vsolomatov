package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }
}
