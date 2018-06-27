package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.ControllerTest;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.dbstore.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class DbStoreTest {
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<Address> makeTestAddressAll() {
        List<Address> result = new ArrayList<>();
        Address address = new Address();
            address.setUserId(1);
            Address address1 = DB_STORE.findByIdAddress(address).get(0);
            result.add(address1);
            address = new Address();
            address.setUserId(2);
            Address address2 = DB_STORE.findByIdAddress(address).get(0);
            result.add(address2);
            address = new Address();
            address.setUserId(3);
            Address address3 = DB_STORE.findByIdAddress(address).get(0);
            result.add(address3);
        return result;
    }

    private List<UserRoles> makeTestUserRolesAll() {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRoles = new UserRoles();
            userRoles.setUserId(1);
            UserRoles userRoles1 = DB_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles1);
            userRoles = new UserRoles();
            userRoles.setUserId(2);
            UserRoles userRoles2 = DB_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles2);
            userRoles = new UserRoles();
            userRoles.setUserId(3);
            UserRoles userRoles3 = DB_STORE.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles3);
        return result;
    }

    private List<MusicType> makeTestMusicTypeAll() {
        List<MusicType> result = new ArrayList<>();
        MusicType musicType = new MusicType();
            musicType.setId(1);
            MusicType musicType1 = DB_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType1);
            musicType = new MusicType();
            musicType.setId(2);
            MusicType musicType2 = DB_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType2);
            musicType = new MusicType();
            musicType.setId(3);
            MusicType musicType3 = DB_STORE.findByIdMusicType(musicType).get(0);
            result.add(musicType3);
        return result;
    }

    private List<UsersMusicTypes> makeTestUsersMusicTypesAll() {
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(1);
            UsersMusicTypes usersMusicTypes1 = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes1);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(2);
            UsersMusicTypes usersMusicTypes2 = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes2);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(3);
            UsersMusicTypes usersMusicTypes3 = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes3);
        return result;
    }


    @Test
    public void addUser() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        List<User> expected = new ArrayList<>();
        expected.add(user4);

        // Добавляем первый раз user4
        List<User> result = DB_STORE.addUser(user4);
        assertThat(result, is(expected));

        // Добавляем пользователя с пустым id
        User userWithNullId = new User(null, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()));
        result = DB_STORE.addUser(userWithNullId);
        assertThat(result.size(), is(1));

        // Добавляем второй раз user4, теперь список должен быть пустой, т.к. User второй раз не добавится
        result = DB_STORE.addUser(user4);
        assertThat(result.size(), is(0));

        // Добавляем User с пустым id
        userWithNullId = new User(null, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()));
        result = DB_STORE.addUser(userWithNullId);
        assertThat(result.size(), is(1));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3);
        List<User> expected = DB_STORE.findByIdUser(user);

        // Обновим пользователя с id=3
        User newUser3 = new User(3, "newname3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()));
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

        Address address = new Address();
        UserRoles userRoles = new UserRoles();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        // Вначале удалим ссылающуюся запись
        address.setUserId(2);
        DB_STORE.deleteAddress(address);
        // Вначале удалим ссылающуюся запись
        userRoles.setUserId(2);
        DB_STORE.deleteUserRoles(userRoles);
        // Вначале удалим ссылающуюся запись
        usersMusicTypes.setUserId(2);
        DB_STORE.deleteUsersMusicTypes(usersMusicTypes);
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
        // Удалим все ссылки
        DB_STORE.deleteAllAddresses(new Address());
        DB_STORE.deleteAllUserRoles(new UserRoles());
        DB_STORE.deleteAllUsersMusicTypes(new UsersMusicTypes());

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
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()));
        User result = DB_STORE.findByIdUser(user1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void findByLoginUser() {
        User user1 = new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()));
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
        Role role4 = new Role(4, "name4",  true);
        List<Role> expected = new ArrayList<>();
        expected.add(role4);

        // Добавляем первый раз role4
        List<Role> result = DB_STORE.addRole(role4);
        assertThat(result, is(expected));

        // Добавляем роль с пустым id
        Role roleWithNullId = new Role(null, "name5", false);
        result = DB_STORE.addRole(roleWithNullId);
        assertThat(result.size(), is(1));

        // Добавляем второй раз role4, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addRole(role4);
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
        // Вначале удалим ссылающуюся запись
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(2);
        DB_STORE.deleteUserRoles(userRoles);
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
        // Перед удалением Roles, удалим ссылки
        DB_STORE.deleteAllUserRoles(new UserRoles());

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
        password = "badpassword";
        result = DB_STORE.isCredentional(login, password);
        assertThat(result, is(false));

        login = "login8";
        password = "password";
        result = DB_STORE.isCredentional(login, password);
        assertThat(result, is(false));
    }

    @Test
    public void addAddress() {
        Address address7 = new Address(7, "street7",  "house7", "apartment7", "city7", "zipcode7", "country7");
        List<Address> expected = new ArrayList<>();
        expected.add(address7);
        // Вначале добавим соответствующего User
        DB_STORE.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз address7
        List<Address> result = DB_STORE.addAddress(address7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Добавляем второй раз address7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addAddress(address7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(3, "newstreet3",  "newhouse3", "newapartment3", "newcity3", "newzipcode3", "newcountry3");
        expected.add(address);
        DB_STORE.updateAddress(address);
        result = DB_STORE.findByIdAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Попытаемся обновить не существующего Address
        address = new Address();
        address.setUserId(8);
        result = DB_STORE.updateAddress(address);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(3, "street3",  "house3", "apartment3", "city3", "zipcode3", "country3");
        expected.add(address);
        result = DB_STORE.deleteAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Попытаемся удалить не существующего Address
        address = new Address();
        address.setUserId(8);
        result = DB_STORE.deleteAddress(address);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        address = new Address(3, "street3",  "house3", "apartment3", "city3", "zipcode3", "country3");
        expected.add(address);
        result = DB_STORE.findByIdAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());
    }

    @Test
    public void findAllAddress() {
        List<Address> expected = makeTestAddressAll();
        List<Address> result = DB_STORE.findAllAddresses(new Address());
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
        List<Address> result = DB_STORE.deleteAllAddresses(new Address());
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
        List<MusicType> result = DB_STORE.addMusicType(musicType7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Добавляем второй раз musicType7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addMusicType(musicType7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(3, "musicType3");
        expected.add(musicType);
        DB_STORE.updateMusicType(musicType);
        result = DB_STORE.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Попытаемся обновить не существующего MusicType
        musicType = new MusicType();
        musicType.setId(8);
        result = DB_STORE.updateMusicType(musicType);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteMusicType() {
        // Перед удалением MusicType, удалим ссылки
        DB_STORE.deleteAllUsersMusicTypes(new UsersMusicTypes());
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(3, "musicType3");
        expected.add(musicType);
        result = DB_STORE.deleteMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Попытаемся удалить не существующего MusicType
        musicType = new MusicType();
        musicType.setId(8);
        result = DB_STORE.deleteMusicType(musicType);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        musicType = new MusicType(3, "musicType3");
        expected.add(musicType);
        result = DB_STORE.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());
    }

    @Test
    public void findAllMusicTypes() {
        List<MusicType> expected = makeTestMusicTypeAll();
        List<MusicType> result = DB_STORE.findAllMusicTypes(new MusicType());
        int i = 0;
        for (MusicType musicTypeloop : result) {
            assertEquals(musicTypeloop.getId(), expected.get(i).getId());
            assertEquals(musicTypeloop.getMusicTypeName(), expected.get(i).getMusicTypeName());
            i++;
        }
    }

    @Test
    public void deleteMusicTypeAll() {
        // Перед удалением MusicType, удалим ссылки
        DB_STORE.deleteAllUsersMusicTypes(new UsersMusicTypes());
        List<MusicType> expected = makeTestMusicTypeAll();

        List<MusicType> result = DB_STORE.deleteAllMusicTypes(new MusicType());
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
        DB_STORE.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз userRoles7
        List<UserRoles> result = DB_STORE.addUserRoles(userRoles7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());

        // Добавляем второй раз userRoles7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addUserRoles(userRoles7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(3, 3);
        expected.add(userRoles);
        DB_STORE.updateUserRoles(userRoles);
        result = DB_STORE.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void deleteUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(3, 3);
        expected.add(userRoles);
        result = DB_STORE.deleteUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());


        // Попытаемся удалить не существующего UserRoles
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        result = DB_STORE.deleteUserRoles(userRoles);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUserUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        userRoles = new UserRoles(3, 3);
        expected.add(userRoles);
        result = DB_STORE.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void findAllUserRoless() {
        List<UserRoles> expected = makeTestUserRolesAll();
        List<UserRoles> result = DB_STORE.findAllUserRoles(new UserRoles());
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

        List<UserRoles> result = DB_STORE.deleteAllUserRoles(new UserRoles());
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
        DB_STORE.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз usersMusicTypes7
        List<UsersMusicTypes> result = DB_STORE.addUsersMusicTypes(usersMusicTypes7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());

        // Добавляем второй раз usersMusicTypes7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = DB_STORE.addUsersMusicTypes(usersMusicTypes7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(3, 3);
        expected.add(usersMusicTypes);
        DB_STORE.updateUsersMusicTypes(usersMusicTypes);
        result = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void deleteUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(3, 3);
        expected.add(usersMusicTypes);
        result = DB_STORE.deleteUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());

        // Попытаемся удалить не существующего UsersMusicTypes
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(8);
        result = DB_STORE.deleteUsersMusicTypes(usersMusicTypes);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        usersMusicTypes = new UsersMusicTypes(3, 3);
        expected.add(usersMusicTypes);
        result = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void findAllUsersMusicTypess() {
        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        List<UsersMusicTypes> result = DB_STORE.findAllUsersMusicTypes(new UsersMusicTypes());
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

        List<UsersMusicTypes> result = DB_STORE.deleteAllUsersMusicTypes(new UsersMusicTypes());
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertEquals(usersMusicTypesloop .getUserId(), expected.get(i).getUserId());
            assertEquals(usersMusicTypesloop .getMusicTypeId(), expected.get(i).getMusicTypeId());
            i++;
        }
    }
}