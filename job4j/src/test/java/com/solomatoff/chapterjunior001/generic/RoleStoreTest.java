package com.solomatoff.chapterjunior001.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindByIdShouldSuccess() {
        RoleStore roleStore = new RoleStore(3);

        Role role0 = new Role("Id 0");
        roleStore.add(role0);

        Role role1 = new Role("Id 1");
        roleStore.add(role1);

        Role role2 = new Role("Id 2");
        roleStore.add(role2);

        Role result = roleStore.findById("Id 0");
        assertThat(result, is(role0));

        result = roleStore.findById("Id 1");
        assertThat(result, is(role1));

        result = roleStore.findById("Id 2");
        assertThat(result, is(role2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddShouldException() {
        RoleStore roleStore = new RoleStore(4);

        Role role = new Role("Id 0");
        roleStore.add(role);

        role = new Role("Id 1");
        roleStore.add(role);

        role = new Role("Id 2");
        roleStore.add(role);

        Role role3 = new Role("Id 3");
        roleStore.add(role3);

        role = new Role("Id 4");
        roleStore.add(role);
    }

    @Test
    public void whenReplaceShouldTrue() {
        RoleStore roleStore = new RoleStore(2);

        Role role = new Role("Id 0");
        roleStore.add(role);

        role = new Role("Id 1");
        roleStore.add(role);

        role = new Role("Id 2");

        boolean result = roleStore.replace("Id 0", role);
        assertThat(result, is(true));
    }

    @Test
    public void whenReplaceShouldFalse() {
        RoleStore roleStore = new RoleStore(3);
        Role role = new Role("Id 0");
        roleStore.add(role);

        role = new Role("Id 1");
        roleStore.add(role);

        role = new Role("Id 2");
        roleStore.add(role);

        boolean result = roleStore.replace("Id 3", role);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteShouldTrue() {
        RoleStore roleStore = new RoleStore(3);
        Role role = new Role("Id 0");
        roleStore.add(role);

        role = new Role("Id 1");
        roleStore.add(role);

        role = new Role("Id 2");
        roleStore.add(role);

        boolean result = roleStore.delete("Id 1");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteShouldFalse() {
        RoleStore roleStore = new RoleStore(3);
        Role role = new Role("Id 0");
        roleStore.add(role);

        role = new Role("Id 1");
        roleStore.add(role);

        role = new Role("Id 2");
        roleStore.add(role);

        boolean result = roleStore.delete("Id 3");
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindByIdShouldException() {
        RoleStore roleStore = new RoleStore(3);

        Role role0 = new Role("Id 0");
        roleStore.add(role0);

        Role role1 = new Role("Id 1");
        roleStore.add(role1);

        Role role2 = new Role("Id 2");
        roleStore.add(role2);

        Role result = roleStore.findById("Id 0");
        assertThat(result, is(role0));

        result = roleStore.findById("Id 1");
        assertThat(result, is(role1));

        result = roleStore.findById("Id 2");
        assertThat(result, is(role2));

        roleStore.findById("Id 3");
    }
}