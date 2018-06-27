package com.solomatoff.mvc.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class UsersMusicTypesTest {

    @Test
    public void getUserId() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        int expected = 1;
        int result = usersMusicTypes.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void setUserId() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(2);
        int expected = 2;
        int result = usersMusicTypes.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void getMusicTypeId() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setMusicTypeId(1);
        int expected = 1;
        int result = usersMusicTypes.getMusicTypeId();
        assertEquals(result, expected);
    }

    @Test
    public void setMusicTypeId() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setMusicTypeId(2);
        int expected = 2;
        int result = usersMusicTypes.getMusicTypeId();
        assertEquals(result, expected);
    }

    @Test
    public void equals() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        usersMusicTypes.setMusicTypeId(1);

        UsersMusicTypes expected = new UsersMusicTypes();
        expected.setUserId(1);
        expected.setMusicTypeId(1);

        assertEquals(usersMusicTypes, expected);

        expected = new UsersMusicTypes();
        expected.setUserId(1);
        expected.setMusicTypeId(2);

        assertNotEquals(usersMusicTypes, expected);
    }

    @Test
    public void testHashCode() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        usersMusicTypes.setMusicTypeId(1);

        UsersMusicTypes expected = new UsersMusicTypes();
        expected.setUserId(1);
        expected.setMusicTypeId(1);

        assertEquals(usersMusicTypes.hashCode(), expected.hashCode());
    }

    @Test
    public void testToString() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        usersMusicTypes.setMusicTypeId(2);

        String expected = "UsersMusicTypes{userId=1, musicTypeId=2}";
        assertEquals(usersMusicTypes.toString(), expected);
    }

    @Test
    public void compareTo() {
        UsersMusicTypes usersMusicTypes1 = new UsersMusicTypes();
        usersMusicTypes1.setUserId(1);
        usersMusicTypes1.setMusicTypeId(1);

        UsersMusicTypes usersMusicTypes2 = new UsersMusicTypes();
        usersMusicTypes2.setUserId(1);
        usersMusicTypes2.setMusicTypeId(1);

        int result = usersMusicTypes1.compareTo(usersMusicTypes2);

        assertEquals(result, 0);

        usersMusicTypes2 = new UsersMusicTypes();
        usersMusicTypes2.setUserId(0);
        usersMusicTypes2.setMusicTypeId(1);

        result = usersMusicTypes1.compareTo(usersMusicTypes2);
        assertEquals(result, 1);

        usersMusicTypes2 = new UsersMusicTypes();
        usersMusicTypes2.setUserId(2);
        usersMusicTypes2.setMusicTypeId(1);

        result = usersMusicTypes1.compareTo(usersMusicTypes2);
        assertEquals(result, -1);

        usersMusicTypes2 = new UsersMusicTypes();
        usersMusicTypes2.setUserId(1);
        usersMusicTypes2.setMusicTypeId(2);

        result = usersMusicTypes1.compareTo(usersMusicTypes2);
        assertEquals(result, -1);

        usersMusicTypes2 = new UsersMusicTypes();
        usersMusicTypes2.setUserId(1);
        usersMusicTypes2.setMusicTypeId(0);

        result = usersMusicTypes1.compareTo(usersMusicTypes2);
        assertEquals(result, 1);
    }
}