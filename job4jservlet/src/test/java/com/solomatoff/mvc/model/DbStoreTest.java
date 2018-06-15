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

public class DbStoreTest {
    private final static ModelStore DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        ControllerTest.clearAndCreateData();
        Controller.getInstance().getLogic().setPersistent(DB_STORE);
    }

    @Test
    public void addUser() {
        User user7 = new User(7, "name7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()), 1);
        List<User> expected = new ArrayList<>();
        expected.add(user7);

        // Добавляем первый раз user7
        List<User> result = DB_STORE.addUser(user7);
        assertThat(result, is(expected));

        // Добавляем пользователя с пустым id
        User userWithNullId = new User(null, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 1);
        result = DB_STORE.addUser(userWithNullId);
        assertThat(result.size(), is(1));

        // Добавляем второй раз user7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addUser(user7);
        assertThat(result.size(), is(0));

        // Добавляем пользователя с пустым id
        userWithNullId = new User(null, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()), 1);
        result = DB_STORE.addUser(userWithNullId);
        assertThat(result.size(), is(1));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3);
        List<User> expected = DB_STORE.findByIdUser(user);

        // Обновим пользователя с id=3
        User newUser3 = new User(3, "newname3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()), 2);
        List<User> result = DB_STORE.updateUser(newUser3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего пользователя
        user = new User();
        user.setId(8);
        result = DB_STORE.updateUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(2);
        List<User> expected = DB_STORE.findByIdUser(user);

        // Удалим пользователя с id = 2
        List<User> result = DB_STORE.deleteUser(user);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего пользователя
        user = new User();
        user.setId(8);
        result = DB_STORE.deleteUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUserAll() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user1);
        user = new User();
        user.setId(2);
        User user2 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user2);
        user = new User();
        user.setId(3);
        User user3 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user3);
        List<User> result = DB_STORE.deleteUserAll(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdUser() {
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()), 1);
        User result = DB_STORE.findByIdUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findByLoginUser() {
        User user1 = new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()), 2);
        User result = DB_STORE.findByLoginUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findAllUsers() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user1);
        user = new User();
        user.setId(2);
        User user2 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user2);
        user = new User();
        user.setId(3);
        User user3 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user3);
        List<User> result = DB_STORE.findAllUsers(user);
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

        // Добавляем первый раз role7
        List<Role> result = DB_STORE.addRole(role7);
        assertThat(result, is(expected));

        // Добавляем роль с пустым id
        Role roleWithNullId = new Role(null, "name5", false);
        result = DB_STORE.addRole(roleWithNullId);
        assertThat(result.size(), is(1));

        // Добавляем второй раз role7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addRole(role7);
        assertThat(result.size(), is(0));

        // Добавляем роль с пустым id
        roleWithNullId = new Role(null, "name6",  false);
        result = DB_STORE.addRole(roleWithNullId);
        assertThat(result.size(), is(1));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(3);
        List<Role> expected = DB_STORE.findByIdRole(role);

        // Обновим роль с id=3
        Role newRole3 = new Role(3, "newname3",  true);
        List<Role> result = DB_STORE.updateRole(newRole3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего роль
        role = new Role();
        role.setId(8);
        result = DB_STORE.updateRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRole() {
        // Перед удалением роли, удалим пользователя с этой ролью
        User user = new User();
        user.setId(2);
        DB_STORE.deleteUser(user);
        Role role = new Role();
        role.setId(2);
        List<Role> expected = DB_STORE.findByIdRole(role);
        // Удалим роль с id = 2
        List<Role> result = DB_STORE.deleteRole(role);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего роль
        role = new Role();
        role.setId(8);
        result = DB_STORE.deleteRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRoleAll() {
        // Перед удалением ролей, удалим всех пользователей
        DB_STORE.deleteUserAll(new User());
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        Role role1 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role1);
        role = new Role();
        role.setId(2);
        Role role2 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role2);
        role = new Role();
        role.setId(3);
        Role role3 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role3);
        List<Role> result = DB_STORE.deleteRoleAll(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdRole() {
        Role role1 = new Role(1, "role1", false);
        Role result = DB_STORE.findByIdRole(role1).get(0);
        assertThat(result.getName(), is(role1.getName()));
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        Role role1 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role1);
        role = new Role();
        role.setId(2);
        Role role2 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role2);
        role = new Role();
        role.setId(3);
        Role role3 = DB_STORE.findByIdRole(role).get(0);
        expected.add(role3);
        List<Role> result = DB_STORE.findAllRoles(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void isCredentional() {
        String login = "login1";
        String password = "password";
        boolean result = DB_STORE.isCredentional(login, password);
        assertThat(result, is(true));

        login = "login1";
        password = "pass";
        result = DB_STORE.isCredentional(login, password);
        assertThat(result, is(false));

        login = "login7";
        password = "password";
        result = DB_STORE.isCredentional(login, password);
        assertThat(result, is(false));
    }
}