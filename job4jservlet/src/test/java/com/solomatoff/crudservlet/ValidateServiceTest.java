package com.solomatoff.crudservlet;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ValidateServiceTest {

    @Test
    public void getInstance() {
        ValidateService validateService1 = ValidateService.getInstance();
        ValidateService validateService2 = ValidateService.getInstance();
        assertThat(validateService1, is(validateService2));
    }

    @Test
    public void add() {
        ValidateService validateService = ValidateService.getInstance();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        validateService.add(user);
        List<User> list = validateService.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        ValidateService validateService = ValidateService.getInstance();
        validateService.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(1, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        validateService.update(user);
        List<User> list = validateService.findById(new User(1, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        ValidateService validateService = ValidateService.getInstance();
        User user1 = new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        validateService.add(user1);
        validateService.add(user2);
        validateService.add(user3);
        List<User> list = validateService.delete(new User(2, null, null, null, null));
        assertThat(list.get(0).getId(), is(user2.getId()));
        list = validateService.delete(new User(999, null, null, null, null));
        assertThat(list, is(nullValue()));
    }

    @Test
    public void findById() {
        ValidateService validateService = ValidateService.getInstance();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        validateService.add(user);
        List<User> list = validateService.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
        list = validateService.findById(new User(999, null, null, null, null));
        assertThat(list, is(nullValue()));
    }
}