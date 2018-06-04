package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.MemoryStore;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

public class MemoryStoreTest {

    @Test
    public void add() {
        Controller controller = Controller.getInstance();
        MemoryStore memoryStore = new MemoryStore();
        User user = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        memoryStore.add(user);
        List<User> list = memoryStore.findById(new User(3, null, null, null, null));
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void update() {
        Controller controller = Controller.getInstance();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        User user = new User(1, "newname1", "newlogin1", "newemail1", new Timestamp(System.currentTimeMillis()));
        memoryStore.update(user);
        List<User> list = memoryStore.findById(new User(1, null, null, null, null));
        assertThat(list.get(0).getName(), is("newname1"));
    }

    @Test
    public void delete() {
        Controller controller = Controller.getInstance();
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
        Controller controller = Controller.getInstance();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        List<User> list = memoryStore.findById(new User(2, null, null, null, null));
        assertThat(list.get(0).getName(), is("name2"));
        list = memoryStore.findById(new User(999, null, null, null, null));
        assertThat(list.get(0), is(nullValue()));
    }

    @Test
    public void findAll() {
        Controller controller = Controller.getInstance();
        MemoryStore memoryStore = new MemoryStore();
        memoryStore.add(new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis())));
        memoryStore.add(new User(4, "name4", "login4", "email4", new Timestamp(System.currentTimeMillis())));
        List<User> list = memoryStore.findAll(new User(0, null, null, null, null));
        list.forEach(user -> System.out.printf("     <id=%s> <name=%s> <login=%s> <email=%s> <createDate=%s>%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate()));
    }
}