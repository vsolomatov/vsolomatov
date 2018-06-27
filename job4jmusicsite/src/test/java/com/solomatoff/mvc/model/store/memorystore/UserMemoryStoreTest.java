package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addUser() {
        User user7 = new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()));
        List<User> expected = new ArrayList<>();
        expected.add(user7);

        // Добавляем первый раз User7
        List<User> result = STORE.addUser(user7);
        assertThat(result, is(expected));

        // Добавляем второй раз User7, теперь список должен быть пустой, т.к. User второй раз не добавится
        result = STORE.addUser(user7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(6);
        List<User> expected = STORE.findByIdUser(user);

        // Обновим User с id=6
        User newUser6 = new User(6, "newuser6", "newlogin6", "password", "newemail6", new Timestamp(System.currentTimeMillis()));
        List<User> result = STORE.updateUser(newUser6);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(6);
        List<User> expected = STORE.findByIdUser(user);

        // Удалим User с id = 6
        List<User> result = STORE.deleteUser(user);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующий User
        user = new User();
        user.setId(8);
        result = STORE.deleteUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUser() {
        User user4 = new User(4, "user4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        User result = STORE.findByIdUser(user4).get(0);
        assertThat(result.getName(), is(user4.getName()));
    }

    @Test
    public void findAllUsers() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(4);
        User user4 = STORE.findByIdUser(user).get(0);
        expected.add(user4);
        user = new User();
        user.setId(5);
        User user5 = STORE.findByIdUser(user).get(0);
        expected.add(user5);
        user = new User();
        user.setId(6);
        User user6 = STORE.findByIdUser(user).get(0);
        expected.add(user6);
        List<User> result = STORE.findAllUsers(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }
}