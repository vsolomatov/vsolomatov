package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRolesMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addUserRoles() {
        UserRoles userRoles7 = new UserRoles(7, 7);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(userRoles7);

        // Добавляем первый раз UserRoles7
        List<UserRoles> result = STORE.addUserRoles(userRoles7);
        assertThat(result, is(expected));

        // Добавляем второй раз UserRoles7, теперь список должен быть пустой, т.к. UserRoles второй раз не добавится
        result = STORE.addUserRoles(userRoles7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(6);
        userRoles.setRoleId(6);
        List<UserRoles> expected = STORE.findByIdUserUserRoles(userRoles);

        // Обновим UserRole с id=6
        UserRoles newUserRoles6 = new UserRoles(6, 6);
        List<UserRoles> result = STORE.updateUserRoles(newUserRoles6);
        assertThat(result.get(0).getRoleId(), is(expected.get(0).getRoleId()));
    }

    @Test
    public void deleteUserRoles() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(6);
        userRoles.setRoleId(6);
        List<UserRoles> expected = STORE.findByIdUserUserRoles(userRoles);

        // Удалим адрес UserRoles с id = 6
        List<UserRoles> result = STORE.deleteUserRoles(userRoles);
        assertThat(result.get(0).getRoleId(), is(expected.get(0).getRoleId()));

        // Попытаемся удалить не существующий UserRoles
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        userRoles.setRoleId(8);
        result = STORE.deleteUserRoles(userRoles);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUserUserRoles() {
        UserRoles userRoles4 = new UserRoles(4, 4);
        UserRoles result = STORE.findByIdUserUserRoles(userRoles4).get(0);
        assertThat(result.getRoleId(), is(userRoles4.getRoleId()));
    }

    @Test
    public void findAllUserRoles() {
        List<UserRoles> expected = new ArrayList<>();
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(4);
        UserRoles userRoles4 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles4);
        userRoles = new UserRoles();
        userRoles.setUserId(5);
        UserRoles userRoles5 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles5);
        userRoles = new UserRoles();
        userRoles.setUserId(6);
        UserRoles userRoles6 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles6);
        List<UserRoles> result = STORE.findAllUserRoles(userRoles);
        int i = 0;
        for (UserRoles userRolesloop : result) {
            assertThat(userRolesloop.getRoleId(), is(expected.get(i).getRoleId()));
            i++;
        }
    }
}