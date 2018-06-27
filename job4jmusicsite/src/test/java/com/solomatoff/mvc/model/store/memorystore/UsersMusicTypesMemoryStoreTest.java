package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UsersMusicTypesMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes7 = new UsersMusicTypes(7, 7);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(usersMusicTypes7);

        // Добавляем первый раз UsersMusicTypes7
        List<UsersMusicTypes> result = STORE.addUsersMusicTypes(usersMusicTypes7);
        assertThat(result, is(expected));

        // Добавляем второй раз UsersMusicTypes7, теперь список должен быть пустой, т.к. UsersMusicTypes второй раз не добавится
        result = STORE.addUsersMusicTypes(usersMusicTypes7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(6);
        usersMusicTypes.setMusicTypeId(6);
        List<UsersMusicTypes> expected = STORE.findByIdUsersMusicTypes(usersMusicTypes);

        // Обновим UserRole с id=6
        UsersMusicTypes newUsersMusicTypes6 = new UsersMusicTypes(6, 6);
        List<UsersMusicTypes> result = STORE.updateUsersMusicTypes(newUsersMusicTypes6);
        assertThat(result.get(0).getMusicTypeId(), is(expected.get(0).getMusicTypeId()));
    }

    @Test
    public void deleteUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(6);
        usersMusicTypes.setMusicTypeId(6);
        List<UsersMusicTypes> expected = STORE.findByIdUsersMusicTypes(usersMusicTypes);

        // Удалим адрес UsersMusicTypes с id = 6
        List<UsersMusicTypes> result = STORE.deleteUsersMusicTypes(usersMusicTypes);
        //System.out.println("result = " + result);
        assertThat(result.get(0).getMusicTypeId(), is(expected.get(0).getMusicTypeId()));

        // Попытаемся удалить не существующий UsersMusicTypes
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(8);
        usersMusicTypes.setMusicTypeId(8);
        result = STORE.deleteUsersMusicTypes(usersMusicTypes);
        //System.out.println("result = " + result);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes4 = new UsersMusicTypes(4, 4);
        UsersMusicTypes result = STORE.findByIdUsersMusicTypes(usersMusicTypes4).get(0);
        assertThat(result.getMusicTypeId(), is(usersMusicTypes4.getMusicTypeId()));
    }

    @Test
    public void findAllUsersMusicTypes() {
        List<UsersMusicTypes> expected = new ArrayList<>();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(4);
        UsersMusicTypes usersMusicTypes4 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes4);
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(5);
        UsersMusicTypes usersMusicTypes5 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes5);
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(6);
        UsersMusicTypes usersMusicTypes6 = STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
        expected.add(usersMusicTypes6);
        List<UsersMusicTypes> result = STORE.findAllUsersMusicTypes(usersMusicTypes);
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertThat(usersMusicTypesloop.getMusicTypeId(), is(expected.get(i).getMusicTypeId()));
            i++;
        }
    }
}