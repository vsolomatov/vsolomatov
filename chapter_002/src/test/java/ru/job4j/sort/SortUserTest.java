package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void WhenSortUser() {
        SortUser sortUser = new SortUser();

        List<User> origin = Arrays.asList(new User("Ivan", 29), new User("Vyacheslav", 47), new User("Dmitriy", 41), new User("Stan", 35));
        //System.out.println(origin);

        Set<User> expected = new TreeSet<>();
        expected.add(new User("Ivan", 29));
        expected.add(new User("Stan", 35));
        expected.add(new User("Dmitriy", 41));
        expected.add(new User("Vyacheslav", 47));
        //System.out.println(expected);

        Set<User> result = sortUser.sort(origin);
        //System.out.println(result);

        assertThat(result, is(expected));
    }
}
