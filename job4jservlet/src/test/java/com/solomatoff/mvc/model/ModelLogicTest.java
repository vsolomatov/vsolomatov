package com.solomatoff.mvc.model;

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

public class ModelLogicTest {
    private static final ModelLogic MODEL_LOGIC = new ModelLogic();

    @Before
    public void setUp() {
        MODEL_LOGIC.setPersistent(new DbStore());

        // Удаляем всех пользователей
        MODEL_LOGIC.deleteUserAll(new User());
        // Удаляем все роли
        MODEL_LOGIC.deleteRoleAll(new Role());

        // Добавляем новые три роли
        MODEL_LOGIC.addRole(new Role(1, "role1", true));
        MODEL_LOGIC.addRole(new Role(2, "role2", false));
        MODEL_LOGIC.addRole(new Role(3, "role3", false));

        // Добавляем новых трех пользователей
        MODEL_LOGIC.addUser(new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()), 1));
        MODEL_LOGIC.addUser(new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()), 2));
        MODEL_LOGIC.addUser(new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()), 3));
    }

    @Test
    public void getPersistent() {
        ModelStore modelStore = MODEL_LOGIC.getPersistent();
        String result = modelStore.getClass().toString();
        assertThat(result, is("class com.solomatoff.mvc.model.DbStore"));
    }

    @Test
    public void setPersistent() {
        ModelStore oldModelStore = MODEL_LOGIC.getPersistent();
        ModelStore newModelStore = new MemoryStore();
        MODEL_LOGIC.setPersistent(newModelStore);
        String result = MODEL_LOGIC.getPersistent().getClass().toString();
        assertThat(result, is("class com.solomatoff.mvc.model.MemoryStore"));
        MODEL_LOGIC.setPersistent(oldModelStore);
    }

    @Test
    public void addUser() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 1);
        List<User> expected = new ArrayList<>();
        expected.add(user4);

        // Добавляем первый раз user4
        List<User> result = MODEL_LOGIC.addUser(user4);
        assertThat(result, is(expected));

        // Добавляем второй раз user4, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addUser(user4);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3);
        List<User> expected = MODEL_LOGIC.findByIdUser(user);

        // Обновим пользователя с id=3
        User newUser3 = new User(3, "newname3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()), 2);
        List<User> result = MODEL_LOGIC.updateUser(newUser3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего пользователя
        user = new User();
        user.setId(8);
        user.setLogin("login8");
        user.setPassword("password8");
        user.setIdRole(0);
        result = MODEL_LOGIC.updateUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(2);
        List<User> expected = MODEL_LOGIC.findByIdUser(user);

        // Удалим пользователя с id = 2
        List<User> result = MODEL_LOGIC.deleteUser(user);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего пользователя
        user = new User();
        user.setId(8);
        result = MODEL_LOGIC.deleteUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUserAll() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user1);
        user = new User();
        user.setId(2);
        User user2 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user2);
        user = new User();
        user.setId(3);
        User user3 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user3);
        List<User> result = MODEL_LOGIC.deleteUserAll(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdUser() {
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()), 1);
        User result = MODEL_LOGIC.findByIdUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findByLoginUser() {
        User user1 = new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()), 2);
        User result = MODEL_LOGIC.findByLoginUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findAllUsers() {
        List<User> expected = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user1);
        user = new User();
        user.setId(2);
        User user2 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user2);
        user = new User();
        user.setId(3);
        User user3 = MODEL_LOGIC.findByIdUser(user).get(0);
        expected.add(user3);
        List<User> result = MODEL_LOGIC.findAllUsers(user);
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void addRole() {
        Role role4 = new Role(4, "name4",  true);
        List<Role> expected = new ArrayList<>();
        expected.add(role4);

        // Добавляем первый раз role4
        List<Role> result = MODEL_LOGIC.addRole(role4);
        assertThat(result, is(expected));

        // Добавляем второй раз role4, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addRole(role4);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(3);
        List<Role> expected = MODEL_LOGIC.findByIdRole(role);

        // Обновим роль с id=3
        Role newRole3 = new Role(3, "newname3",  true);
        List<Role> result = MODEL_LOGIC.updateRole(newRole3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего роль
        role = new Role();
        role.setId(8);
        result = MODEL_LOGIC.updateRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRole() {
        // Перед удалением роли, удалим пользователя с этой ролью
        User user = new User();
        user.setId(2);
        MODEL_LOGIC.deleteUser(user);
        Role role = new Role();
        role.setId(2);
        List<Role> expected = MODEL_LOGIC.findByIdRole(role);
        // Удалим роль с id = 2
        List<Role> result = MODEL_LOGIC.deleteRole(role);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующего роль
        role = new Role();
        role.setId(8);
        result = MODEL_LOGIC.deleteRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRoleAll() {
        // Перед удалением ролей, удалим всех пользователей
        MODEL_LOGIC.deleteUserAll(new User());
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        Role role1 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role1);
        role = new Role();
        role.setId(2);
        Role role2 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role2);
        role = new Role();
        role.setId(3);
        Role role3 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role3);
        List<Role> result = MODEL_LOGIC.deleteRoleAll(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void findByIdRole() {
        Role role1 = new Role(1, "role1", false);
        Role result = MODEL_LOGIC.findByIdRole(role1).get(0);
        assertThat(result.getName(), is(role1.getName()));
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        Role role1 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role1);
        role = new Role();
        role.setId(2);
        Role role2 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role2);
        role = new Role();
        role.setId(3);
        Role role3 = MODEL_LOGIC.findByIdRole(role).get(0);
        expected.add(role3);
        List<Role> result = MODEL_LOGIC.findAllRoles(role);
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
        boolean result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(true));

        login = "login1";
        password = "pass";
        result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(false));

        login = "login4";
        password = "password";
        result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(false));
    }
}