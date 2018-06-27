package com.solomatoff.mvc.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRolesTest {

    @Test
    public void getUserId() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        int expected = 1;
        int result = userRoles.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void setUserId() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(2);
        int expected = 2;
        int result = userRoles.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void getRoleId() {
        UserRoles userRoles = new UserRoles();
        userRoles.setRoleId(1);
        int expected = 1;
        int result = userRoles.getRoleId();
        assertEquals(result, expected);
    }

    @Test
    public void setRoleId() {
        UserRoles userRoles = new UserRoles();
        userRoles.setRoleId(2);
        int expected = 2;
        int result = userRoles.getRoleId();
        assertEquals(result, expected);
    }

    @Test
    public void equals() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        userRoles.setRoleId(1);

        UserRoles expected = new UserRoles();
        expected.setUserId(1);
        expected.setRoleId(1);

        assertEquals(userRoles, expected);

        expected = new UserRoles();
        expected.setUserId(1);
        expected.setRoleId(2);

        assertNotEquals(userRoles, expected);
    }

    @Test
    public void testHashCode() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        userRoles.setRoleId(1);

        UserRoles expected = new UserRoles();
        expected.setUserId(1);
        expected.setRoleId(1);

        assertEquals(userRoles.hashCode(), expected.hashCode());
    }
    

    @Test
    public void testToString() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        userRoles.setRoleId(2);

        String expected = "UserRoles{userId=1, roleId=2}";
        assertEquals(userRoles.toString(), expected);
    }

    @Test
    public void compareTo() {
        UserRoles userRoles1 = new UserRoles();
        userRoles1.setUserId(1);
        userRoles1.setRoleId(1);

        UserRoles userRoles2 = new UserRoles();
        userRoles2.setUserId(1);
        userRoles2.setRoleId(1);

        int result = userRoles1.compareTo(userRoles2);

        assertEquals(result, 0);

        userRoles2 = new UserRoles();
        userRoles2.setUserId(0);
        userRoles2.setRoleId(1);

        result = userRoles1.compareTo(userRoles2);
        assertEquals(result, 1);

        userRoles2 = new UserRoles();
        userRoles2.setUserId(2);
        userRoles2.setRoleId(1);

        result = userRoles1.compareTo(userRoles2);
        assertEquals(result, -1);

        userRoles2 = new UserRoles();
        userRoles2.setUserId(1);
        userRoles2.setRoleId(2);

        result = userRoles1.compareTo(userRoles2);
        assertEquals(result, -1);

        userRoles2 = new UserRoles();
        userRoles2.setUserId(1);
        userRoles2.setRoleId(0);

        result = userRoles1.compareTo(userRoles2);
        assertEquals(result, 1);
    }
}