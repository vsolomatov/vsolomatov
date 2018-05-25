package com.solomatoff.crudservlet;

import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

public class MemoryStoreTest {

    @Test
    public void getInstance() {
        MemoryStore memoryStore1 = MemoryStore.getInstance();
        MemoryStore memoryStore2 = MemoryStore.getInstance();
        assertThat(memoryStore1, is(memoryStore2));
    }

    @Test
    public void add() {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        MemoryStore memoryStore = new MemoryStore();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        memoryStore.add(user);
        List<User> list = memoryStore.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(1, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        memoryStore.update(user);
        List<User> list = memoryStore.findById(new User(1, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        memoryStore.delete(new User(2, "", "", "", new Timestamp(System.currentTimeMillis())));
        List<User> list = memoryStore.findById(new User(2, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findById() {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        List<User> list = memoryStore.findById(new User(2, null, null, null, null));
        assertThat(list.get(0).getName(), is("name2"));
        list = memoryStore.findById(new User(10, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findAll() {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(4, "name4", "login4", "email4", new Timestamp(System.currentTimeMillis())));
        List<User> list = memoryStore.findAll(new User(0, null, null, null, null));
        int i = 0;
        for (User user : list) {
            assertThat(user.getId(), is(++i));
        }
    }
}