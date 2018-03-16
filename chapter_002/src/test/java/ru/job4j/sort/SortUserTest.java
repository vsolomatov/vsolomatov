package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenSortUser() {
        SortUser sortUser = new SortUser();

        List<User> origin = Arrays.asList(new User("I", 29), new User("V", 47), new User("D", 41), new User("S", 35));
        //System.out.println(origin);

        Set<User> expected = new TreeSet<>();
        expected.add(new User("Vyacheslav", 47));
        expected.add(new User("Ivan", 29));
        expected.add(new User("Stanly", 41));
        expected.add(new User("Dmitriy", 35));

        System.out.println(expected);

        Set<User> result = sortUser.sort(origin);
        System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenSortUserByNameLength() {
        SortUser sortUser = new SortUser();

        List<User> origin = Arrays.asList(new User("Stan", 41), new User("Vyacheslav", 47), new User("Vyacheslav", 27),
                                        new User("Dmitriy", 35), new User("Stan", 29));
        //System.out.println(origin);

        List<User> expected = new ArrayList<>();
        expected.add(new User("Stan", 41));
        expected.add(new User("Stan", 29));
        expected.add(new User("Dmitriy", 35));
        expected.add(new User("Vyacheslav", 47));
        expected.add(new User("Vyacheslav", 27));
        System.out.println(expected);

        List<User> result = sortUser.sortNameLength(origin);
        System.out.println(result);

        assertThat(result, is(expected));
    }

    @Test
    public void whenSortUserByAll() {
        SortUser sortUser = new SortUser();

        List<User> origin = Arrays.asList(new User("Stan", 41), new User("Vyacheslav", 47), new User("Vyacheslav", 27),
                new User("Dmitriy", 35), new User("Stan", 29));
        //System.out.println(origin);

        List<User> expected = new ArrayList<>();
        expected.add(new User("Dmitriy", 35));
        expected.add(new User("Stan", 29));
        expected.add(new User("Stan", 41));
        expected.add(new User("Vyacheslav", 27));
        expected.add(new User("Vyacheslav", 47));
        System.out.println(expected);

        List<User> result = sortUser.sortByAllFields(origin);
        System.out.println(result);

        assertThat(result, is(expected));
    }
}
