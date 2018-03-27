package com.solomatoff.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStoreOldTest {

    @Test
    public void whenAddAndFindByIdShouldSuccess() {
        UserStoreOld userStoreOld = new UserStoreOld(3);

        User user0 = new User("Id 0");
        userStoreOld.add(user0);

        User user1 = new User("Id 1");
        userStoreOld.add(user1);

        User user2 = new User("Id 2");
        userStoreOld.add(user2);

        User result = userStoreOld.findById("Id 0");
        assertThat(result, is(user0));

        result = userStoreOld.findById("Id 1");
        assertThat(result, is(user1));

        result = userStoreOld.findById("Id 2");
        assertThat(result, is(user2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddShouldException() {
        UserStoreOld userStoreOld = new UserStoreOld(4);

        User user = new User("Id 0");
        userStoreOld.add(user);

        user = new User("Id 1");
        userStoreOld.add(user);

        user = new User("Id 2");
        userStoreOld.add(user);

        User user3 = new User("Id 3");
        userStoreOld.add(user3);

        user = new User("Id 4");
        userStoreOld.add(user);
    }

    @Test
    public void whenReplaceShouldTrue() {
        UserStoreOld userStoreOld = new UserStoreOld(2);

        User user = new User("Id 0");
        userStoreOld.add(user);

        user = new User("Id 1");
        userStoreOld.add(user);

        user = new User("Id 2");

        boolean result = userStoreOld.replace("Id 0", user);
        assertThat(result, is(true));
    }

    @Test
    public void whenReplaceShouldFalse() {
        UserStoreOld userStoreOld = new UserStoreOld(3);
        User user = new User("Id 0");
        userStoreOld.add(user);

        user = new User("Id 1");
        userStoreOld.add(user);

        user = new User("Id 2");
        userStoreOld.add(user);

        boolean result = userStoreOld.replace("Id 3", user);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteShouldTrue() {
        UserStoreOld userStoreOld = new UserStoreOld(3);
        User user = new User("Id 0");
        userStoreOld.add(user);

        user = new User("Id 1");
        userStoreOld.add(user);

        user = new User("Id 2");
        userStoreOld.add(user);

        boolean result = userStoreOld.delete("Id 1");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteShouldFalse() {
        UserStoreOld userStoreOld = new UserStoreOld(3);
        User user = new User("Id 0");
        userStoreOld.add(user);

        user = new User("Id 1");
        userStoreOld.add(user);

        user = new User("Id 2");
        userStoreOld.add(user);

        boolean result = userStoreOld.delete("Id 3");
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindByIdShouldException() {
        UserStoreOld userStoreOld = new UserStoreOld(3);

        User user0 = new User("Id 0");
        userStoreOld.add(user0);

        User user1 = new User("Id 1");
        userStoreOld.add(user1);

        User user2 = new User("Id 2");
        userStoreOld.add(user2);

        User result = userStoreOld.findById("Id 0");
        assertThat(result, is(user0));

        result = userStoreOld.findById("Id 1");
        assertThat(result, is(user1));

        result = userStoreOld.findById("Id 2");
        assertThat(result, is(user2));

        userStoreOld.findById("Id 3");
    }
}