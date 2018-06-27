package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserDbStoreTest {
    private static final PersistentDAO STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addUser() {
        User user4 = new User(4, "user4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        List<User> expected = new ArrayList<>();
        expected.add(user4);

        // Добавляем первый раз User4
        List<User> result = STORE.addUser(user4);
        assertThat(result, is(expected));

        // Добавляем второй раз User4, теперь список должен быть пустой, т.к. User второй раз не добавится
        result = STORE.addUser(user4);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3);
        List<User> expected = STORE.findByIdUser(user);

        // Обновим User с id=3
        User newUser3 = new User(3, "newuser3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()));
        List<User> result = STORE.updateUser(newUser3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(3);
        List<User> expected = STORE.findByIdUser(user);

        // Вначале удалим ссылающуюся запись
        Address address = new Address();
        address.setUserId(3);
        STORE.deleteAddress(address);
        // Вначале удалим ссылающуюся запись
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(3);
        STORE.deleteUserRoles(userRoles);
        // Вначале удалим ссылающуюся запись
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        STORE.deleteUsersMusicTypes(usersMusicTypes);

        // Удалим User с id = 3
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
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()));
        User result = STORE.findByIdUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findAllUsers() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = STORE.findByIdUser(user).get(0);
        expected.add(user1);
        user = new User();
        user.setId(2);
        User user2 = STORE.findByIdUser(user).get(0);
        expected.add(user2);
        user = new User();
        user.setId(3);
        User user3 = STORE.findByIdUser(user).get(0);
        expected.add(user3);
        List<User> result = STORE.findAllUsers(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }
}