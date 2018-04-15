package com.solomatoff.chapterjunior001.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddAndFindByIdShouldSuccess() {
        UserStore userStore = new UserStore(3);

        User user0 = new User("Id 0");
        userStore.add(user0);

        User user1 = new User("Id 1");
        userStore.add(user1);

        User user2 = new User("Id 2");
        userStore.add(user2);

        User result = userStore.findById("Id 0");
        assertThat(result, is(user0));

        result = userStore.findById("Id 1");
        assertThat(result, is(user1));

        result = userStore.findById("Id 2");
        assertThat(result, is(user2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddShouldException() {
        UserStore userStore = new UserStore(4);

        User user = new User("Id 0");
        userStore.add(user);

        user = new User("Id 1");
        userStore.add(user);

        user = new User("Id 2");
        userStore.add(user);

        User user3 = new User("Id 3");
        userStore.add(user3);

        user = new User("Id 4");
        userStore.add(user);
    }

    @Test
    public void whenReplaceShouldTrue() {
        UserStore userStore = new UserStore(2);

        User user = new User("Id 0");
        userStore.add(user);

        user = new User("Id 1");
        userStore.add(user);

        user = new User("Id 2");

        boolean result = userStore.replace("Id 0", user);
        assertThat(result, is(true));
    }

    @Test
    public void whenReplaceShouldFalse() {
        UserStore userStore = new UserStore(3);
        User user = new User("Id 0");
        userStore.add(user);

        user = new User("Id 1");
        userStore.add(user);

        user = new User("Id 2");
        userStore.add(user);

        boolean result = userStore.replace("Id 3", user);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteShouldTrue() {
        UserStore userStore = new UserStore(3);
        User user = new User("Id 0");
        userStore.add(user);

        user = new User("Id 1");
        userStore.add(user);

        user = new User("Id 2");
        userStore.add(user);

        boolean result = userStore.delete("Id 1");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteShouldFalse() {
        UserStore userStore = new UserStore(3);
        User user = new User("Id 0");
        userStore.add(user);

        user = new User("Id 1");
        userStore.add(user);

        user = new User("Id 2");
        userStore.add(user);

        boolean result = userStore.delete("Id 3");
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindByIdShouldException() {
        UserStore userStore = new UserStore(3);

        User user0 = new User("Id 0");
        userStore.add(user0);

        User user1 = new User("Id 1");
        userStore.add(user1);

        User user2 = new User("Id 2");
        userStore.add(user2);

        User result = userStore.findById("Id 0");
        assertThat(result, is(user0));

        result = userStore.findById("Id 1");
        assertThat(result, is(user1));

        result = userStore.findById("Id 2");
        assertThat(result, is(user2));

        userStore.findById("Id 3");
    }
}