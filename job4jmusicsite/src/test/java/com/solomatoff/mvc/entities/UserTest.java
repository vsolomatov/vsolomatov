package com.solomatoff.mvc.entities;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getId() {
        User user = new User();
        int expected = 1;
        user.setId(1);
        int result = user.getId();
        assertEquals(result, expected);
    }

    @Test
    public void getName() {
        User user = new User();
        String expected = "name2";
        user.setName("name2");
        String result = user.getName();
        assertEquals(result, expected);
    }

    @Test
    public void getLogin() {
        User user = new User();
        String expected = "login1";
        user.setLogin("login1");
        String result = user.getLogin();
        assertEquals(result, expected);
    }

    @Test
    public void getEmail() {
        User user = new User();
        String expected = "email1";
        user.setEmail("email1");
        String result = user.getEmail();
        assertEquals(result, expected);
    }

    @Test
    public void getCreateDate() {
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "name1", "login1", "password", "email1", expected);
        Timestamp result = user.getCreateDate();
        assertEquals(result, expected);
    }

    @Test
    public void setName() {
        User user = new User();
        String expected = "name1";
        user.setName("name1");
        String result = user.getName();
        assertEquals(result, expected);
    }

    @Test
    public void setLogin() {
        User user = new User();
        String expected = "login2";
        user.setLogin("login2");
        String result = user.getLogin();
        assertEquals(result, expected);
    }

    @Test
    public void setEmail() {
        User user = new User();
        String expected = "email2";
        user.setEmail("email2");
        String result = user.getEmail();
        assertEquals(result, expected);
    }

    @Test
    public void getPassword() {
        User user = new User();
        String expected = "password2";
        user.setPassword("password2");
        String result = user.getPassword();
        assertEquals(result, expected);
    }

    @Test
    public void setPassword() {
        User user = new User();
        String expected = "password1";
        user.setPassword("password1");
        String result = user.getPassword();
        assertEquals(result, expected);
    }

    @Test
    public void setId() {
        User user = new User();
        int expected = 2;
        user.setId(2);
        int result = user.getId();
        assertEquals(result, expected);
    }

    @Test
    public void equals() {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "name1", "login1", "password", "email1", createDate);
        User expected = new User(1, "name1", "login1", "password", "email1", createDate);
        assertEquals(user, expected);

        user = new User(1, "name1", "login1", "password", "email1", createDate);
        expected = new User(2, "name2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()));
        assertNotEquals(user, expected);
    }

    @Test
    public void testHashCode() {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "name1", "login1", "password", "email1", createDate);
        User expected = new User(1, "name1", "login1", "password", "email1", createDate);
        assertEquals(user.hashCode(), expected.hashCode());
    }

    @Test
    public void testToString() {
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "name1", "login1", "password", "email1", createDate);
        String expected = "User{id=1, name='name1', login='login1', password='password', email='email1', createDate=" + createDate + "}";
        assertEquals(user.toString(), expected);
    }
}