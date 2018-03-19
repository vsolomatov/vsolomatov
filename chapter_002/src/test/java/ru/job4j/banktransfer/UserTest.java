package ru.job4j.banktransfer;

import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class UserTest {
    @Test
    public void whenUserEquelse() {
        User origin = new User("Олег", "6502 261212");
        User expected = new User("Олег", "6502 261212");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.equals(expected);
        assertThat(result, is(true));

        int res = origin.compareTo(expected);
        assertThat(res, is(0));
    }

    @Test
    public void whenUserNotEquelse() {
        User origin = new User("Олег", "6502 261212");
        User expected = new User("Олег", "6502 261214");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.equals(expected);

        assertThat(result, is(false));

        int res = origin.compareTo(expected);
        assertThat(res, lessThan(0));
    }
}
