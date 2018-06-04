package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.DbStore;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

public class DbStoreTest {

    @Test
    public void add() {
        Controller.getInstance();
        DbStore dbStore = new DbStore();
        User user = new User(13, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        dbStore.add(user);
        List<User> list = dbStore.findById(new User(13, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        Controller.getInstance();
        DbStore dbStore = new DbStore();
        dbStore.add(new User(20, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(20, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        dbStore.update(user);
        List<User> list = dbStore.findById(new User(20, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        Controller.getInstance();
        DbStore dbStore = new DbStore();
        dbStore.add(new User(21, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(22, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(23, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        dbStore.delete(new User(22, "", "", "", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findById(new User(22, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findById() {
        Controller.getInstance();
        DbStore dbStore = new DbStore();
        dbStore.add(new User(22, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findById(new User(22, null, null, null, null));
        assertThat(list.get(0).getName(), is("name2"));

        list = dbStore.findById(new User(999, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findAll() {
        Controller.getInstance();
        DbStore dbStore = new DbStore();
        dbStore.add(new User(31, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(32, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(33, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(34, "name4", "login4", "email4", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findAll(new User(0, null, null, null, null));
        list.forEach(user -> System.out.printf("     <id=%s> <name=%s> <login=%s> <email=%s> <createDate=%s>%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()));
    }
}