package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UsersMusicTypesDbStoreTest {
    private static final PersistentDAO STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes3 = new UsersMusicTypes(3, 3);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(usersMusicTypes3);

        // Вначале удалим
        STORE.deleteUsersMusicTypes(usersMusicTypes3);
        // Добавляем первый раз UsersMusicTypes4
        List<UsersMusicTypes> result = STORE.addUsersMusicTypes(usersMusicTypes3);
        assertThat(result, is(expected));

        // Добавляем второй раз UsersMusicTypes3, теперь список должен быть пустой, т.к. UsersMusicTypes второй раз не добавится
        result = STORE.addUsersMusicTypes(usersMusicTypes3);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes(3, 2);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(usersMusicTypes);

        // Обновим UsersMusicTypes с userid=3 musicType=2

        List<UsersMusicTypes> result = STORE.updateUsersMusicTypes(usersMusicTypes);
        assertThat(result.get(0).getUserId(), is(expected.get(0).getUserId()));
        assertThat(result.get(0).getMusicTypeId(), is(expected.get(0).getMusicTypeId()));
    }

    @Test
    public void deleteUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        usersMusicTypes.setMusicTypeId(3);
        List<UsersMusicTypes> expected = STORE.findByIdUsersMusicTypes(usersMusicTypes);

        // Удалим адрес UsersMusicTypes с id = 3
        List<UsersMusicTypes> result = STORE.deleteUsersMusicTypes(usersMusicTypes);
        assertThat(result.get(0).getUserId(), is(expected.get(0).getUserId()));
        assertThat(result.get(0).getMusicTypeId(), is(expected.get(0).getMusicTypeId()));
    }

    @Test
    public void findByIdUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes1 = new UsersMusicTypes(1, 1);
        UsersMusicTypes result = STORE.findByIdUsersMusicTypes(usersMusicTypes1).get(0);
        assertThat(result.getMusicTypeId(), is(usersMusicTypes1.getMusicTypeId()));
    }

    @Test
    public void findAllUsersMusicTypess() {
        List<UsersMusicTypes> expected = new ArrayList<>();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        UsersMusicTypes usersMusicTypes1 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes1);
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(2);
        UsersMusicTypes usersMusicTypes2 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes2);
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        UsersMusicTypes usersMusicTypes3 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes3);
        List<UsersMusicTypes> result = STORE.findAllUsersMusicTypes(usersMusicTypes);
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertThat(usersMusicTypesloop.getMusicTypeId(), is(expected.get(i).getMusicTypeId()));
            i++;
        }
    }
}