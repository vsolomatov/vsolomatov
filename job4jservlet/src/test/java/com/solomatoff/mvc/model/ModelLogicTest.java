package com.solomatoff.mvc.model;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ModelLogicTest {

    @Test
    public void add() {
        ModelLogic modelLogic = new ModelLogic();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        modelLogic.add(user);
        List<User> list = modelLogic.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        ModelLogic modelLogic = new ModelLogic();
        modelLogic.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(1, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        modelLogic.update(user);
        List<User> list = modelLogic.findById(new User(1, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        ModelLogic modelLogic = new ModelLogic();
        User user1 = new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        modelLogic.add(user1);
        modelLogic.add(user2);
        modelLogic.add(user3);
        List<User> list = modelLogic.delete(new User(2, null, null, null, null));
        assertThat(list.get(0).getId(), is(user2.getId()));
        list = modelLogic.delete(new User(999, null, null, null, null));
        assertThat(list, is(nullValue()));
    }

    @Test
    public void findById() {
        ModelLogic modelLogic = new ModelLogic();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        modelLogic.add(user);
        List<User> list = modelLogic.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
        list = modelLogic.findById(new User(999, null, null, null, null));
        assertThat(list, is(nullValue()));
    }
}