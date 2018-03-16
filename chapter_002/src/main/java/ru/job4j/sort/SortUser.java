package ru.job4j.sort;

import java.util.*;

public class SortUser {

    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return ((Integer) o1.getName().length()).compareTo(o2.getName().length());
            }
        }
        );
        return list;
    }

    public List<User> sortByAllFields (List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return (o1.getName().equals(o2.getName())) ? Integer.compare(o1.getAge(),o2.getAge()) : o1.getName().compareTo(o2.getName());
            }
        }
        );
        return list;
    }
}
