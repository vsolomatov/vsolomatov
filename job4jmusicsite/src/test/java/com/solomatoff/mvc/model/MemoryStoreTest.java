package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class MemoryStoreTest {
    private static final PersistentDAO MEMORY_STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    private List<Address> makeTestAddressAll() {
        List<Address> result = new ArrayList<>();
        Address address = new Address();
            address.setUserId(4);
            Address address1 = MEMORY_STORE.findByIdAddress(address).get(0);
            result.add(address1);
            address = new Address();
            address.setUserId(5);
            Address address2 = MEMORY_STORE.findByIdAddress(address).get(0);
            result.add(address2);
            address = new Address();
            address.setUserId(6);
            Address address3 = MEMORY_STORE.findByIdAddress(address).get(0);
            result.add(address3);
        return result;
    }

    private List<UserRoles> makeTestUserRolesAll() {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRoles = new UserRoles();
            userRoles.setUserId(4);
            UserRoles userRoles1 = MEMORY_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles1);
            userRoles = new UserRoles();
            userRoles.setUserId(5);
            UserRoles userRoles2 = MEMORY_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles2);
            userRoles = new UserRoles();
            userRoles.setUserId(6);
            UserRoles userRoles3 = MEMORY_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles3);
        return result;
    }

    private List<MusicType> makeTestMusicTypeAll() {
        List<MusicType> result = new ArrayList<>();
        MusicType musicType = new MusicType();
            musicType.setId(4);
            MusicType musicType1 = MEMORY_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType1);
            musicType = new MusicType();
            musicType.setId(5);
            MusicType musicType2 = MEMORY_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType2);
            musicType = new MusicType();
            musicType.setId(6);
            MusicType musicType3 = MEMORY_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType3);
        return result;
    }

    private List<UsersMusicTypes> makeTestUsersMusicTypesAll() {
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(4);
            UsersMusicTypes usersMusicTypes1 = MEMORY_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes1);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(5);
            UsersMusicTypes usersMusicTypes2 = MEMORY_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes2);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(6);
            UsersMusicTypes usersMusicTypes3 = MEMORY_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes3);
        return result;
    }


    @Test
    public void addUser() {
        User user7 = new User(7, "name7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()));
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
        User newUser6 = new User(6, "newname6", "newlogin6", "password", "newemail6", new Timestamp(System.currentTimeMillis()));
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
        User user4 = new User(4, "user4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        User result = MEMORY_STORE.findByIdUser(user4).get(0);
        assertThat(result.getName(), is(user4.getName()));
    }

    @Test
    public void findByLoginUser() {
        User user5 = new User(5, "user5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()));
        User result = MEMORY_STORE.findByLoginUser(user5).get(0);
        assertThat(result.getName(), is(user5.getName()));
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
    }

    @Test
    public void deleteRole() {
        Role role = new Role();
        role.setId(6);
        List<Role> expected = MEMORY_STORE.findByIdRole(role);

        // Удалим роль с id = 6
        List<Role> result = MEMORY_STORE.deleteRole(role);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));
    }

    @Test
    public void deleteRoleAll() {
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

    @Test
    public void addAddress() {
        Address address7 = new Address(7, "street7",  "house7", "apartment7", "city7", "zipcode7", "country7");
        List<Address> expected = new ArrayList<>();
        expected.add(address7);
        // Вначале добавим соответствующего User
        User user7 = new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()));
        MEMORY_STORE.addUser(user7);

        // Добавляем первый раз address7
        List<Address> result = MEMORY_STORE.addAddress(address7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Добавляем второй раз address7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MEMORY_STORE.addAddress(address7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(6, "newstreet6",  "newhouse6", "newapartment6", "newcity6", "newzipcode6", "newcountry6");
        expected.add(address);

        MEMORY_STORE.updateAddress(address);
        result = MEMORY_STORE.findByIdAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());
    }

    @Test
    public void deleteAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(6, "street6",  "house6", "apartment6", "city6", "zipcode6", "country6");
        expected.add(address);

        result = MEMORY_STORE.deleteAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());
    }

    @Test
    public void findByIdAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(6, "street6",  "house6", "apartment6", "city6", "zipcode6", "country6");
        expected.add(address);

        result = MEMORY_STORE.findByIdAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());
    }

    @Test
    public void findAllAddresss() {
        List<Address> expected = makeTestAddressAll();
        List<Address> result = MEMORY_STORE.findAllAddresses(new Address());
        int i = 0;
        for (Address addressloop : result) {
            assertEquals(addressloop.getStreet(), expected.get(i).getStreet());
            assertEquals(addressloop.getHouse(), expected.get(i).getHouse());
            assertEquals(addressloop.getApartment(), expected.get(i).getApartment());
            assertEquals(addressloop.getCity(), expected.get(i).getCity());
            assertEquals(addressloop.getZipcode(), expected.get(i).getZipcode());
            assertEquals(addressloop.getCountry(), expected.get(i).getCountry());
            i++;
        }
    }

    @Test
    public void deleteAddressAll() {
        List<Address> expected = makeTestAddressAll();
        List<Address> result = MEMORY_STORE.deleteAllAddresses(new Address());
        int i = 0;
        for (Address addressloop : result) {
            assertEquals(addressloop.getStreet(), expected.get(i).getStreet());
            assertEquals(addressloop.getHouse(), expected.get(i).getHouse());
            assertEquals(addressloop.getApartment(), expected.get(i).getApartment());
            assertEquals(addressloop.getCity(), expected.get(i).getCity());
            assertEquals(addressloop.getZipcode(), expected.get(i).getZipcode());
            assertEquals(addressloop.getCountry(), expected.get(i).getCountry());
            i++;
        }
    }

    @Test
    public void addMusicType() {
        MusicType musicType7 = new MusicType(7, "musictypename7");
        List<MusicType> expected = new ArrayList<>();
        expected.add(musicType7);

        // Добавляем первый раз musicType7
        List<MusicType> result = MEMORY_STORE.addMusicType(musicType7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Добавляем второй раз musicType7, теперь список должен быть пустой, т.к. MusicType второй раз не добавится
        result = MEMORY_STORE.addMusicType(musicType7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(6, "musicType6");
        expected.add(musicType);

        MEMORY_STORE.updateMusicType(musicType);
        result = MEMORY_STORE.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());
    }

    @Test
    public void deleteMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(6, "musicType6");
        expected.add(musicType);

        result = MEMORY_STORE.deleteMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());
    }

    @Test
    public void findByIdMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(6, "musicType6");
        expected.add(musicType);
        result = MEMORY_STORE.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());
    }

    @Test
    public void findAllMusicTypes() {
        List<MusicType> expected = makeTestMusicTypeAll();
        List<MusicType> result = MEMORY_STORE.findAllMusicTypes(new MusicType());
        int i = 0;
        for (MusicType musicTypeloop : result) {
            assertEquals(musicTypeloop.getId(), expected.get(i).getId());
            assertEquals(musicTypeloop.getMusicTypeName(), expected.get(i).getMusicTypeName());
            i++;
        }
    }

    @Test
    public void deleteMusicTypeAll() {
        List<MusicType> expected = makeTestMusicTypeAll();
        List<MusicType> result = MEMORY_STORE.deleteAllMusicTypes(new MusicType());
        int i = 0;
        for (MusicType musicTypeloop : result) {
            assertEquals(musicTypeloop.getId(), expected.get(i).getId());
            assertEquals(musicTypeloop.getMusicTypeName(), expected.get(i).getMusicTypeName());
            i++;
        }
    }

    @Test
    public void addUserRoles() {
        UserRoles userRoles7 = new UserRoles(7, 1);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(userRoles7);

        // Вначале добавим соответствующего User
        MEMORY_STORE.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз userRoles7
        List<UserRoles> result = MEMORY_STORE.addUserRoles(userRoles7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());

        // Добавляем второй раз userRoles7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MEMORY_STORE.addUserRoles(userRoles7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(6, 6);
        expected.add(userRoles);

        MEMORY_STORE.updateUserRoles(userRoles);
        result = MEMORY_STORE.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void deleteUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(6, 6);
        expected.add(userRoles);

        result = MEMORY_STORE.deleteUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void findByIdUserUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(6, 6);
        expected.add(userRoles);

        result = MEMORY_STORE.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void findAllUserRoless() {
        List<UserRoles> expected = makeTestUserRolesAll();
        List<UserRoles> result = MEMORY_STORE.findAllUserRoles(new UserRoles());
        int i = 0;
        for (UserRoles userRolesloop : result) {
            assertEquals(userRolesloop.getUserId(), expected.get(i).getUserId());
            assertEquals(userRolesloop.getRoleId(), expected.get(i).getRoleId());
            i++;
        }
    }

    @Test
    public void deleteUserRolesAll() {
        List<UserRoles> expected = makeTestUserRolesAll();
        List<UserRoles> result = MEMORY_STORE.deleteAllUserRoles(new UserRoles());
        int i = 0;
        for (UserRoles userRolesloop : result) {
            assertEquals(userRolesloop.getUserId(), expected.get(i).getUserId());
            assertEquals(userRolesloop.getRoleId(), expected.get(i).getRoleId());
            i++;
        }
    }

    @Test
    public void addUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes7 = new UsersMusicTypes(7, 1);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(usersMusicTypes7);

        // Вначале добавим соответствующего User
        MEMORY_STORE.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));

        // Добавляем первый раз usersMusicTypes7
        List<UsersMusicTypes> result = MEMORY_STORE.addUsersMusicTypes(usersMusicTypes7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());

        // Добавляем второй раз usersMusicTypes7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MEMORY_STORE.addUsersMusicTypes(usersMusicTypes7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(6, 6);
        expected.add(usersMusicTypes);

        MEMORY_STORE.updateUsersMusicTypes(usersMusicTypes);
        result = MEMORY_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void deleteUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(6, 6);
        expected.add(usersMusicTypes);
        result = MEMORY_STORE.deleteUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void findByIdUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(6, 6);
        expected.add(usersMusicTypes);

        result = MEMORY_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void findAllUsersMusicTypess() {
        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        List<UsersMusicTypes> result = MEMORY_STORE.findAllUsersMusicTypes(new UsersMusicTypes());
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertEquals(usersMusicTypesloop .getUserId(), expected.get(i).getUserId());
            assertEquals(usersMusicTypesloop .getMusicTypeId(), expected.get(i).getMusicTypeId());
            i++;
        }
    }

    @Test
    public void deleteUsersMusicTypesAll() {
        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        List<UsersMusicTypes> result = MEMORY_STORE.deleteAllUsersMusicTypes(new UsersMusicTypes());
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertEquals(usersMusicTypesloop .getUserId(), expected.get(i).getUserId());
            assertEquals(usersMusicTypesloop .getMusicTypeId(), expected.get(i).getMusicTypeId());
            i++;
        }
    }
}