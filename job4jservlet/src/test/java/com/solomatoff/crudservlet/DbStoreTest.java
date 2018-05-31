package com.solomatoff.crudservlet;

import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

public class DbStoreTest {

    @Test
    public void getInstance() {
        DbStore dbStore1 = DbStore.getInstance();
        DbStore dbStore2 = DbStore.getInstance();
        assertThat(dbStore1, is(dbStore2));
    }

    @Test
    public void add() {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init();
        DbStore dbStore = DbStore.getInstance();
        User user = new User(13, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        dbStore.add(user);
        List<User> list = dbStore.findById(new User(13, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init();
        DbStore dbStore = DbStore.getInstance();
        dbStore.add(new User(21, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(21, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        dbStore.update(user);
        List<User> list = dbStore.findById(new User(1, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init();
        DbStore dbStore = DbStore.getInstance();
        dbStore.add(new User(21, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(22, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(23, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        dbStore.delete(new User(22, "", "", "", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findById(new User(22, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findById() {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init();
        DbStore dbStore = DbStore.getInstance();
        dbStore.add(new User(22, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findById(new User(22, null, null, null, null));
        assertThat(list.get(0).getName(), is("name2"));
        list = dbStore.findById(new User(999, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findAll() {
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init();
        DbStore dbStore = DbStore.getInstance();
        dbStore.add(new User(21, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(22, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(23, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        dbStore.add(new User(24, "name4", "login4", "email4", new Timestamp(System.currentTimeMillis())));
        List<User> list = dbStore.findAll(new User(0, null, null, null, null));
        list.forEach(user -> System.out.printf("     <id=%s> <name=%s> <login=%s> <email=%s> <createDate=%s>%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()));
    }
}