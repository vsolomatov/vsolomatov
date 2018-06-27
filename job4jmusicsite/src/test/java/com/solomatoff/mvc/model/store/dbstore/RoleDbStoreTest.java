package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleDbStoreTest {
    private static final PersistentDAO STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addRole() {
        Role role4 = new Role(4, "Crole4", false);
        List<Role> expected = new ArrayList<>();
        expected.add(role4);

        // Добавляем первый раз Role4
        List<Role> result = STORE.addRole(role4);
        assertThat(result, is(expected));

        // Добавляем второй раз Role4, теперь список должен быть пустой, т.к. Role второй раз не добавится
        result = STORE.addRole(role4);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(3);
        List<Role> expected = STORE.findByIdRole(role);

        // Обновим Role с id=3
        Role newRole3 = new Role(3, "newrole3", false);
        List<Role> result = STORE.updateRole(newRole3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteRole() {
        Role role = new Role();
        role.setId(3);
        List<Role> expected = STORE.findByIdRole(role);

        // Вначале удалим ссылающуюся запись
        STORE.deleteUserRoles(new UserRoles(3, 3));
        // Удалим Role с id = 3
        List<Role> result = STORE.deleteRole(role);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся удалить не существующий Role
        role = new Role();
        role.setId(8);
        result = STORE.deleteRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdRole() {
        Role role1 = new Role(1, "role1", false);
        Role result = STORE.findByIdRole(role1).get(0);
        assertThat(result.getName(), is(role1.getName()));
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(1);
        Role role1 = STORE.findByIdRole(role).get(0);
        expected.add(role1);
        role = new Role();
        role.setId(2);
        Role role2 = STORE.findByIdRole(role).get(0);
        expected.add(role2);
        role = new Role();
        role.setId(3);
        Role role3 = STORE.findByIdRole(role).get(0);
        expected.add(role3);
        List<Role> result = STORE.findAllRoles(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }
}