package com.solomatoff.chaptertrainee003.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {
    @Test
    public void whenUserEquelse() {
        User origin = new User("Олег", 29);
        User expected = new User("Андрей", 29);

        System.out.println(origin);
        System.out.println(expected);

        boolean result = origin.equals(expected);

        assertThat(result, is(false));
    }
}
