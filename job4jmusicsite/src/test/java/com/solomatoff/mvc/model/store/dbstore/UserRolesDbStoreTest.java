package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
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

public class UserRolesDbStoreTest {
    private static final PersistentDAO STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addUserRoles() {
        UserRoles userRoles3 = new UserRoles(3, 3);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(userRoles3);

        // Вначале удалим
        STORE.deleteUserRoles(userRoles3);
        // Добавляем первый раз UserRoles4
        List<UserRoles> result = STORE.addUserRoles(userRoles3);
        assertThat(result, is(expected));

        // Добавляем второй раз UserRoles3, теперь список должен быть пустой, т.к. UserRoles второй раз не добавится
        result = STORE.addUserRoles(userRoles3);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles = new UserRoles(3, 2);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(userRoles);

        // Обновим UserRoles с userId=3 roleId=2

        List<UserRoles> result = STORE.updateUserRoles(userRoles);
        assertThat(result.get(0).getUserId(), is(expected.get(0).getUserId()));
        assertThat(result.get(0).getRoleId(), is(expected.get(0).getRoleId()));
    }

    @Test
    public void deleteUserRoles() {
        UserRoles userRoles = new UserRoles(3, 3);
        List<UserRoles> expected = STORE.findByIdUserUserRoles(userRoles);

        // Удалим адрес UserRoles с userid = 3, roleid=3
        List<UserRoles> result = STORE.deleteUserRoles(userRoles);
        assertThat(result.get(0).getUserId(), is(expected.get(0).getUserId()));
        assertThat(result.get(0).getRoleId(), is(expected.get(0).getRoleId()));

        // Попытаемся удалить не существующий UserRoles
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        result = STORE.deleteUserRoles(userRoles);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUserUserRoles() {
        UserRoles userRoles1 = new UserRoles(1, 1);
        UserRoles result = STORE.findByIdUserUserRoles(userRoles1).get(0);
        assertThat(result.getRoleId(), is(userRoles1.getRoleId()));
    }

    @Test
    public void findAllUserRoless() {
        List<UserRoles> expected = new ArrayList<>();
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        UserRoles userRoles1 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles1);
        userRoles = new UserRoles();
        userRoles.setUserId(2);
        UserRoles userRoles2 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles2);
        userRoles = new UserRoles();
        userRoles.setUserId(3);
        UserRoles userRoles3 = STORE.findByIdUserUserRoles(userRoles).get(0);
        expected.add(userRoles3);
        List<UserRoles> result = STORE.findAllUserRoles(userRoles);
        int i = 0;
        for (UserRoles userRolesloop : result) {
            assertThat(userRolesloop.getRoleId(), is(expected.get(i).getRoleId()));
            i++;
        }
    }
}