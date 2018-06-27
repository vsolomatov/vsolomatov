package com.solomatoff.mvc.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void getId() {
        Role role = new Role();
        role.setId(1);
        int expected = 1;
        int result = role.getId();
        assertEquals(result, expected);
    }

    @Test
    public void getName() {
        Role role = new Role();
        role.setName("Director");
        String expected = "Director";
        String result = role.getName();
        assertEquals(result, expected);
    }

    @Test
    public void setId() {
        Role role = new Role();
        role.setId(2);
        int expected = 2;
        int result = role.getId();
        assertEquals(result, expected);
    }

    @Test
    public void setName() {
        Role role = new Role();
        role.setName("Manager");
        String expected = "Manager";
        String result = role.getName();
        assertEquals(result, expected);
    }

    @Test
    public void getIsAdmin() {
        Role role = new Role(1, "Administrator", true);
        boolean result = role.getIsAdmin();
        assertEquals(result, true);
    }

    @Test
    public void equals() {
        Role musicType = new Role(1, "Editor", false);
        Role expected = new Role(1, "Editor", false);

        assertEquals(musicType, expected);

        musicType = new Role(1, "Editor", false);
        expected = new Role(1, "Subscriber", false);

        assertNotEquals(musicType, expected);
    }

    @Test
    public void testHashCode() {
        Role musicType = new Role(1, "Editor", false);
        Role expected = new Role(1, "Editor", false);

        assertEquals(musicType.hashCode(), expected.hashCode());
    }

    @Test
    public void testToString() {
        Role role = new Role(1, "Participant", false);

        String expected = "Role{id=1, name='Participant', isAdmin=false}";
        assertEquals(role.toString(), expected);
    }
}