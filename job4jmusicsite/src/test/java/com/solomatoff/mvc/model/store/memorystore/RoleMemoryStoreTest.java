package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addRole() {
        Role role7 = new Role(7, "Crole7", false);
        List<Role> expected = new ArrayList<>();
        expected.add(role7);

        // Добавляем первый раз Role7
        List<Role> result = STORE.addRole(role7);
        assertThat(result, is(expected));

        // Добавляем второй раз Role7, теперь список должен быть пустой, т.к. Role второй раз не добавится
        result = STORE.addRole(role7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(6);
        List<Role> expected = STORE.findByIdRole(role);

        // Обновим Role с id=6
        Role newRole6 = new Role(6, "newrole6", false);
        List<Role> result = STORE.updateRole(newRole6);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteRole() {
        Role role = new Role();
        role.setId(6);
        List<Role> expected = STORE.findByIdRole(role);

        // Удалим Role с id = 6
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
        Role role4 = new Role(4, "role4", false);
        Role result = STORE.findByIdRole(role4).get(0);
        assertThat(result.getName(), is(role4.getName()));
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = new ArrayList<>();
        Role role = new Role();
        role.setId(4);
        Role role4 = STORE.findByIdRole(role).get(0);
        expected.add(role4);
        role = new Role();
        role.setId(5);
        Role role5 = STORE.findByIdRole(role).get(0);
        expected.add(role5);
        role = new Role();
        role.setId(6);
        Role role6 = STORE.findByIdRole(role).get(0);
        expected.add(role6);
        List<Role> result = STORE.findAllRoles(role);
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }
}