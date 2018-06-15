package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.ControllerTest;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class MemoryStoreTest {
    private static final ModelStore MEMORY_STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        ControllerTest.clearAndCreateData();
        Controller.getInstance().getLogic().setPersistent(MEMORY_STORE);
    }

    @Test
    public void addUser() {
        User user7 = new User(7, "name7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()), 7);
        List<User> expected = new ArrayList<>();
        expected.add(user7);

        // Добавляем первый раз user7
        List<User> result = MEMORY_STORE.addUser(user7);
        assertThat(result, is(expected));

        // Добавляем второй раз user7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MEMORY_STORE.addUser(user7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(6);
        List<User> expected = MEMORY_STORE.findByIdUser(user);

        // Обновим пользователя с id=6
        User newUser6 = new User(6, "newname6", "newlogin6", "password", "newemail6", new Timestamp(System.currentTimeMillis()), 6);
        List<User> result = MEMORY_STORE.updateUser(newUser6);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(6);
        List<User> expected = MEMORY_STORE.findByIdUser(user);

        // Удалим пользователя с id = 6
        List<User> result = MEMORY_STORE.deleteUser(user);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего пользователя
        user = new User();
        user.setId(8);
        result = MEMORY_STORE.deleteUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUserAll() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(4);
        User user4 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user4);
        user = new User();
        user.setId(5);
        User user5 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user5);
        user = new User();
        user.setId(6);
        User user6 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user6);
        List<User> result = MEMORY_STORE.deleteUserAll(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdUser() {
        User user4 = new User(4, "user4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 4);
        User result = MEMORY_STORE.findByIdUser(user4).get(0);
        assertThat(result.getName(), is(user4.getName()));
    }

    @Test
    public void findByLoginUser() {
        User user4 = new User(5, "user5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 5);
        User result = MEMORY_STORE.findByLoginUser(user4).get(0);
        assertThat(result.getName(), is(user4.getName()));
    }

    @Test
    public void findAllUsers() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(4);
        User user4 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user4);
        user = new User();
        user.setId(5);
        User user5 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user5);
        user = new User();
        user.setId(6);
        User user6 = MEMORY_STORE.findByIdUser(user).get(0);
        expected.add(user6);
        List<User> result = MEMORY_STORE.findAllUsers(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void addRole() {
        Role role7 = new Role(7, "name7",  true);
        List<Role> expected = new ArrayList<>();
        expected.add(role7);

        // Добавляем первый раз role4
        List<Role> result = MEMORY_STORE.addRole(role7);
        assertThat(result, is(expected));

        // Добавляем второй раз role4, теперь список должен быть пустой, т.к. роль второй раз не добавится
        result = MEMORY_STORE.addRole(role7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(6);
        List<Role> expected = MEMORY_STORE.findByIdRole(role);

        // Обновим роль с id=6
        Role newRole6 = new Role(6, "newname6",  true);
        List<Role> result = MEMORY_STORE.updateRole(newRole6);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего роль
        role = new Role();
        role.setId(8);
        result = MEMORY_STORE.updateRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRole() {
        // Перед удалением роли, удалим пользователя с этой ролью
        User user = new User();
        user.setId(6);
        MEMORY_STORE.deleteUser(user);
        Role role = new Role();
        role.setId(6);
        List<Role> expected = MEMORY_STORE.findByIdRole(role);
        // Удалим роль с id = 5
        List<Role> result = MEMORY_STORE.deleteRole(role);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего роль
        role = new Role();
        role.setId(8);
        result = MEMORY_STORE.deleteRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRoleAll() {
        // Перед удалением ролей, удалим всех пользователей
        MEMORY_STORE.deleteUserAll(new User());
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(4);
        Role role4 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role4);
        role = new Role();
        role.setId(5);
        Role role5 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role5);
        role = new Role();
        role.setId(6);
        Role role6 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role6);
        int i = 0;
        List<Role> result = MEMORY_STORE.deleteRoleAll(role);
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdRole() {
        Role role4 = new Role(4, "role4", false);
        Role result = MEMORY_STORE.findByIdRole(role4).get(0);
        assertThat(result.getName(), is(role4.getName()));
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(4);
        Role role4 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role4);
        role = new Role();
        role.setId(5);
        Role role5 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role5);
        role = new Role();
        role.setId(6);
        Role role6 = MEMORY_STORE.findByIdRole(role).get(0);
        expected.add(role6);
        List<Role> result = MEMORY_STORE.findAllRoles(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void isCredentional() {
        String login = "login4";
        String password = "password";
        boolean result = MEMORY_STORE.isCredentional(login, password);
        assertThat(result, is(true));

        login = "login4";
        password = "pass";
        result = MEMORY_STORE.isCredentional(login, password);
        assertThat(result, is(false));

        login = "login8";
        password = "password";
        result = MEMORY_STORE.isCredentional(login, password);
        assertThat(result, is(false));
    }
}