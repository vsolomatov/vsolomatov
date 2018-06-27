package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ModelLogicTest {
    private static final ModelLogic MODEL_LOGIC = Controller.getInstance().getLogic();

    @Before
    public void setUp() {
        //CommonTest.clearAndCreateDataInMemory();
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<User> makeTestUserAll() {
        List<User> result = new ArrayList<>();
        User user;
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            user = new User();
            user.setId(1);
            User user1 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user1);
            user = new User();
            user.setId(2);
            User user2 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user2);
            user = new User();
            user.setId(3);
            User user3 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user3);
        } else {
            user = new User();
            user.setId(4);
            User user1 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user1);
            user = new User();
            user.setId(5);
            User user2 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user2);
            user = new User();
            user.setId(6);
            User user3 = MODEL_LOGIC.findByIdUser(user).get(0);
            result.add(user3);
        }
        return result;
    }

    private List<Role> makeTestRoleAll() {
        List<Role> result = new ArrayList<>();
        Role role = new Role();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            role.setId(1);
            Role role1 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role1);
            role = new Role();
            role.setId(2);
            Role role2 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role2);
            role = new Role();
            role.setId(3);
            Role role3 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role3);
        } else {
            role.setId(4);
            Role role1 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role1);
            role = new Role();
            role.setId(5);
            Role role2 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role2);
            role = new Role();
            role.setId(6);
            Role role3 = MODEL_LOGIC.findByIdRole(role).get(0);
            result.add(role3);
        }
        return result;
    }

    private List<Address> makeTestAddressAll() {
        List<Address> result = new ArrayList<>();
        Address address = new Address();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            address.setUserId(1);
            Address address1 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address1);
            address = new Address();
            address.setUserId(2);
            Address address2 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address2);
            address = new Address();
            address.setUserId(3);
            Address address3 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address3);
        } else {
            address.setUserId(4);
            Address address1 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address1);
            address = new Address();
            address.setUserId(5);
            Address address2 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address2);
            address = new Address();
            address.setUserId(6);
            Address address3 = MODEL_LOGIC.findByIdAddress(address).get(0);
            result.add(address3);
        }
        return result;
    }

    private List<UserRoles> makeTestUserRolesAll() {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRoles = new UserRoles();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            userRoles.setUserId(1);
            UserRoles userRoles1 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles1);
            userRoles = new UserRoles();
            userRoles.setUserId(2);
            UserRoles userRoles2 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles2);
            userRoles = new UserRoles();
            userRoles.setUserId(3);
            UserRoles userRoles3 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles3);
        } else {
            userRoles.setUserId(4);
            UserRoles userRoles1 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles1);
            userRoles = new UserRoles();
            userRoles.setUserId(5);
            UserRoles userRoles2 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles2);
            userRoles = new UserRoles();
            userRoles.setUserId(6);
            UserRoles userRoles3 = MODEL_LOGIC.findByIdUserUserRoles(userRoles).get(0);
            result.add(userRoles3);
        }
        return result;
    }

    private List<MusicType> makeTestMusicTypeAll() {
        List<MusicType> result = new ArrayList<>();
        MusicType musicType = new MusicType();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            musicType.setId(1);
            MusicType musicType1 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType1);
            musicType = new MusicType();
            musicType.setId(2);
            MusicType musicType2 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType2);
            musicType = new MusicType();
            musicType.setId(3);
            MusicType musicType3 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType3);
        } else {
            musicType.setId(4);
            MusicType musicType1 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType1);
            musicType = new MusicType();
            musicType.setId(5);
            MusicType musicType2 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType2);
            musicType = new MusicType();
            musicType.setId(6);
            MusicType musicType3 = MODEL_LOGIC.findByIdMusicType(musicType).get(0);
            result.add(musicType3);
        }
        return result;
    }

    private List<UsersMusicTypes> makeTestUsersMusicTypesAll() {
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            usersMusicTypes.setUserId(1);
            UsersMusicTypes usersMusicTypes1 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes1);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(2);
            UsersMusicTypes usersMusicTypes2 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes2);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(3);
            UsersMusicTypes usersMusicTypes3 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes3);
        } else {
            usersMusicTypes.setUserId(4);
            UsersMusicTypes usersMusicTypes1 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes1);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(5);
            UsersMusicTypes usersMusicTypes2 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes2);
            usersMusicTypes = new UsersMusicTypes();
            usersMusicTypes.setUserId(6);
            UsersMusicTypes usersMusicTypes3 = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes).get(0);
            result.add(usersMusicTypes3);
        }
        return result;
    }

    @Test
    public void isCredentional() {
        String login;
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            login = "login1";
        } else {
            login = "login4";
        }
        String password = "password";
        boolean result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(true));

        password = "badpassword";
        result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(false));

        login = "notexistlogin8";
        password = "password";
        result = MODEL_LOGIC.isCredentional(login, password);
        assertThat(result, is(false));
    }

    @Test
    public void getPersistent() {
        PersistentDAO persistentDAO = MODEL_LOGIC.getPersistent();
        String result = persistentDAO.getClass().getName();
        assertThat(result.substring(0, 30), is("com.solomatoff.mvc.model.store"));
    }

    @Test
    public void setPersistent() {
        PersistentDAO oldPersistentDAO = MODEL_LOGIC.getPersistent();
        PersistentDAO newPersistentDAO = new MemoryStore();
        MODEL_LOGIC.setPersistent(newPersistentDAO);
        String result = MODEL_LOGIC.getPersistent().getClass().getName();
        assertThat(result, is("com.solomatoff.mvc.model.store.MemoryStore"));
        MODEL_LOGIC.setPersistent(oldPersistentDAO);
    }

    @Test
    public void addUser() {
        User user7 = new User(7, "name7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis()));
        List<User> expected = new ArrayList<>();
        expected.add(user7);

        // Добавляем первый раз user7
        List<User> result = MODEL_LOGIC.addUser(user7);
        assertThat(result, is(expected));

        // Добавляем второй раз user7, теперь список должен быть пустой, т.к. User второй раз не добавится
        result = MODEL_LOGIC.addUser(user7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateUser() {
        User user;
        List<User> result;
        List<User> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            user = new User(3, "newname3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()));
        } else {
            user = new User(6, "newname6", "newlogin6", "password", "newemail6", new Timestamp(System.currentTimeMillis()));
        }
        expected.add(user);
        MODEL_LOGIC.updateUser(user);
        result = MODEL_LOGIC.findByIdUser(user);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getLogin(), expected.get(0).getLogin());
        assertEquals(result.get(0).getPassword(), expected.get(0).getPassword());
        assertEquals(result.get(0).getEmail(), expected.get(0).getEmail());

        // Попытаемся обновить не существующего User
        user = new User();
        user.setId(8);
        user.setLogin("login8");
        user.setPassword("password8");
        result = MODEL_LOGIC.updateUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUser() {
        User user;
        Address address = new Address();
        UserRoles userRoles = new UserRoles();
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        List<User> result;
        List<User> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            // Вначале удалим ссылающуюся запись
            address.setUserId(3);
            MODEL_LOGIC.deleteAddress(address);
            // Вначале удалим ссылающуюся запись
            userRoles.setUserId(3);
            MODEL_LOGIC.deleteUserRoles(userRoles);
            // Вначале удалим ссылающуюся запись
            usersMusicTypes.setUserId(3);
            MODEL_LOGIC.deleteUsersMusicTypes(usersMusicTypes);
            user = new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()));
        } else {
            // Вначале удалим ссылающуюся запись
            address.setUserId(6);
            MODEL_LOGIC.deleteAddress(address);
            // Вначале удалим ссылающуюся запись
            userRoles.setUserId(6);
            MODEL_LOGIC.deleteUserRoles(userRoles);
            // Вначале удалим ссылающуюся запись
            usersMusicTypes.setUserId(6);
            MODEL_LOGIC.deleteUsersMusicTypes(usersMusicTypes);
            user = new User(6, "user6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()));
        }
        expected.add(user);
        result = MODEL_LOGIC.deleteUser(user);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getLogin(), expected.get(0).getLogin());
        assertEquals(result.get(0).getPassword(), expected.get(0).getPassword());
        assertEquals(result.get(0).getEmail(), expected.get(0).getEmail());

        // Попытаемся удалить не существующего User
        user = new User();
        user.setId(8);
        result = MODEL_LOGIC.deleteUser(user);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUser() {
        User user;
        List<User> result;
        List<User> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            user = new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()));
        } else {
            user = new User(6, "user6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()));
        }
        expected.add(user);
        result = MODEL_LOGIC.findByIdUser(user);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getLogin(), expected.get(0).getLogin());
        assertEquals(result.get(0).getPassword(), expected.get(0).getPassword());
        assertEquals(result.get(0).getEmail(), expected.get(0).getEmail());
    }

    @Test
    public void findByLoginUser() {
        User user;
        List<User> result;
        List<User> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            user = new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()));
        } else {
            user = new User(6, "user6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()));
        }
        expected.add(user);
        result = MODEL_LOGIC.findByLoginUser(user);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getLogin(), expected.get(0).getLogin());
        assertEquals(result.get(0).getPassword(), expected.get(0).getPassword());
        assertEquals(result.get(0).getEmail(), expected.get(0).getEmail());
    }

    @Test
    public void findAllUsers() {
        List<User> expected = makeTestUserAll();
        List<User> result = MODEL_LOGIC.findAllUsers(new User());
        int i = 0;
        for (User userloop : result) {
            assertThat(userloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void deleteUserAll() {
        // Удалим все ссылки
        MODEL_LOGIC.deleteAllAddresses(new Address());
        MODEL_LOGIC.deleteAllUserRoles(new UserRoles());
        MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());

        List<User> expected = makeTestUserAll();
        List<User> result = MODEL_LOGIC.deleteUserAll(new User());
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
        List<Role> result = MODEL_LOGIC.addRole(role7);
        assertThat(result, is(expected));

        // Добавляем второй раз role7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addRole(role7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateRole() {
        Role role;
        List<Role> result;
        List<Role> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            role = new Role(3, "newrole3", true);
        } else {
            role = new Role(6, "newrole6", true);
        }
        expected.add(role);
        MODEL_LOGIC.updateRole(role);
        result = MODEL_LOGIC.findByIdRole(role);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getIsAdmin(), expected.get(0).getIsAdmin());

        // Попытаемся обновить не существующего Role
        role = new Role();
        role.setId(8);
        result = MODEL_LOGIC.updateRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteRole() {
        UserRoles userRoles = new UserRoles();
        Role role;
        List<Role> result;
        List<Role> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            // Вначале удалим ссылающуюся запись
            userRoles.setUserId(3);
            MODEL_LOGIC.deleteUserRoles(userRoles);
            role = new Role(3, "role3", false);
        } else {
            // Вначале удалим ссылающуюся запись
            userRoles.setUserId(6);
            MODEL_LOGIC.deleteUserRoles(userRoles);
            role = new Role(6, "role6", false);
        }
        expected.add(role);
        result = MODEL_LOGIC.deleteRole(role);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getIsAdmin(), expected.get(0).getIsAdmin());

        // Попытаемся удалить не существующего Role
        role = new Role();
        role.setId(8);
        result = MODEL_LOGIC.deleteRole(role);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdRole() {
        Role role;
        List<Role> result;
        List<Role> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            role = new Role(3, "role3", false);
        } else {
            role = new Role(6, "role6", false);
        }
        expected.add(role);
        result = MODEL_LOGIC.findByIdRole(role);
        assertEquals(result.get(0).getName(), expected.get(0).getName());
        assertEquals(result.get(0).getIsAdmin(), expected.get(0).getIsAdmin());
    }

    @Test
    public void findAllRoles() {
        List<Role> expected = makeTestRoleAll();
        List<Role> result = MODEL_LOGIC.findAllRoles(new Role());
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }

    @Test
    public void deleteRoleAll() {
        // Перед удалением Roles, удалим ссылки
        MODEL_LOGIC.deleteAllUserRoles(new UserRoles());
        List<Role> expected = makeTestRoleAll();

        List<Role> result = MODEL_LOGIC.deleteRoleAll(new Role());
        int i = 0;
        for (Role roleloop : result) {
            assertThat(roleloop.getName(), is(expected.get(i).getName()));
            i++;
        }
    }


    @Test
    public void addAddress() {
        Address address7 = new Address(7, "street7",  "house7", "apartment7", "city7", "zipcode7", "country7");
        List<Address> expected = new ArrayList<>();
        expected.add(address7);
        // Вначале добавим соответствующего User
        MODEL_LOGIC.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз address7
        List<Address> result = MODEL_LOGIC.addAddress(address7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Добавляем второй раз address7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addAddress(address7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            address = new Address(3, "newstreet3",  "newhouse3", "newapartment3", "newcity3", "newzipcode3", "newcountry3");
        } else {
            address = new Address(6, "newstreet6",  "newhouse6", "newapartment6", "newcity6", "newzipcode6", "newcountry6");
        }
        expected.add(address);
        MODEL_LOGIC.updateAddress(address);
        result = MODEL_LOGIC.findByIdAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Попытаемся обновить не существующего Address
        address = new Address();
        address.setUserId(8);
        result = MODEL_LOGIC.updateAddress(address);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            address = new Address(3, "street3",  "house3", "apartment3", "city3", "zipcode3", "country3");
        } else {
            address = new Address(6, "street6",  "house6", "apartment6", "city6", "zipcode6", "country6");
        }
        expected.add(address);
        result = MODEL_LOGIC.deleteAddress(address);
        assertEquals(result.get(0).getStreet(), expected.get(0).getStreet());
        assertEquals(result.get(0).getHouse(), expected.get(0).getHouse());
        assertEquals(result.get(0).getApartment(), expected.get(0).getApartment());
        assertEquals(result.get(0).getCity(), expected.get(0).getCity());
        assertEquals(result.get(0).getZipcode(), expected.get(0).getZipcode());
        assertEquals(result.get(0).getCountry(), expected.get(0).getCountry());

        // Попытаемся удалить не существующего Address
        address = new Address();
        address.setUserId(8);
        result = MODEL_LOGIC.deleteAddress(address);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdAddress() {
        Address address;
        List<Address> result;
        List<Address> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            address = new Address(3, "street3",  "house3", "apartment3", "city3", "zipcode3", "country3");
        } else {
            address = new Address(6, "street6",  "house6", "apartment6", "city6", "zipcode6", "country6");
        }
        expected.add(address);
        result = MODEL_LOGIC.findByIdAddress(address);
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
        List<Address> result = MODEL_LOGIC.findAllAddress(new Address());
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
        List<Address> result = MODEL_LOGIC.deleteAllAddresses(new Address());
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
        List<MusicType> result = MODEL_LOGIC.addMusicType(musicType7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Добавляем второй раз musicType7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addMusicType(musicType7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            musicType = new MusicType(3, "musicType3");
        } else {
            musicType = new MusicType(6, "musicType6");
        }
        expected.add(musicType);
        MODEL_LOGIC.updateMusicType(musicType);
        result = MODEL_LOGIC.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Попытаемся обновить не существующего MusicType
        musicType = new MusicType();
        musicType.setId(8);
        result = MODEL_LOGIC.updateMusicType(musicType);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteMusicType() {
        // Перед удалением MusicType, удалим ссылки
        MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            musicType = new MusicType(3, "musicType3");
        } else {
            musicType = new MusicType(6, "musicType6");
        }
        expected.add(musicType);
        result = MODEL_LOGIC.deleteMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());

        // Попытаемся удалить не существующего MusicType
        musicType = new MusicType();
        musicType.setId(8);
        result = MODEL_LOGIC.deleteMusicType(musicType);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdMusicType() {
        MusicType musicType;
        List<MusicType> result;
        List<MusicType> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            musicType = new MusicType(3, "musicType3");
        } else {
            musicType = new MusicType(6, "musicType6");
        }
        expected.add(musicType);
        result = MODEL_LOGIC.findByIdMusicType(musicType);
        assertEquals(result.get(0).getId(), expected.get(0).getId());
        assertEquals(result.get(0).getMusicTypeName(), expected.get(0).getMusicTypeName());
    }

    @Test
    public void findAllMusicTypes() {
        List<MusicType> expected = makeTestMusicTypeAll();
        List<MusicType> result = MODEL_LOGIC.findAllMusicTypes(new MusicType());
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
        MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());
        List<MusicType> expected = makeTestMusicTypeAll();

        List<MusicType> result = MODEL_LOGIC.deleteAllMusicTypes(new MusicType());
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
        MODEL_LOGIC.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз userRoles7
        List<UserRoles> result = MODEL_LOGIC.addUserRoles(userRoles7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());

        // Добавляем второй раз userRoles7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addUserRoles(userRoles7);
        assertThat(result.size(), is(0));

        // Добавляем третий раз userRoles7 с другим значением roleId
        userRoles7 = new UserRoles(7, 2);
        result = MODEL_LOGIC.addUserRoles(userRoles7);
        assertEquals(result.get(0).getUserId(), userRoles7.getUserId());
        assertEquals(result.get(0).getRoleId(), userRoles7.getRoleId());
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            userRoles = new UserRoles(3, 3);
        } else {
            userRoles = new UserRoles(6, 6);
        }
        expected.add(userRoles);
        MODEL_LOGIC.updateUserRoles(userRoles);
        result = MODEL_LOGIC.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());


        // Попытаемся обновить не существующего UserRoles
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        result = MODEL_LOGIC.updateUserRoles(userRoles);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            userRoles = new UserRoles(3, 3);
        } else {
            userRoles = new UserRoles(6, 6);
        }
        expected.add(userRoles);
        result = MODEL_LOGIC.deleteUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());


        // Попытаемся удалить не существующего UserRoles
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        result = MODEL_LOGIC.deleteUserRoles(userRoles);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUserUserRoles() {
        UserRoles userRoles;
        List<UserRoles> result;
        List<UserRoles> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            userRoles = new UserRoles(3, 3);
        } else {
            userRoles = new UserRoles(6, 6);
        }
        expected.add(userRoles);
        result = MODEL_LOGIC.findByIdUserUserRoles(userRoles);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getRoleId(), expected.get(0).getRoleId());
    }

    @Test
    public void findAllUserRoless() {
        List<UserRoles> expected = makeTestUserRolesAll();
        List<UserRoles> result = MODEL_LOGIC.findAllUserRoles(new UserRoles());
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

        List<UserRoles> result = MODEL_LOGIC.deleteAllUserRoles(new UserRoles());
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
        MODEL_LOGIC.addUser(new User(7, "user7", "login7", "password", "email7", new Timestamp(System.currentTimeMillis())));
        // Добавляем первый раз usersMusicTypes7
        List<UsersMusicTypes> result = MODEL_LOGIC.addUsersMusicTypes(usersMusicTypes7);
        //System.out.println("result = " + result);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());

        // Добавляем второй раз usersMusicTypes7, теперь список должен быть пустой, т.к. пользователь второй раз не добавится
        result = MODEL_LOGIC.addUsersMusicTypes(usersMusicTypes7);
        assertThat(result.size(), is(0));

        // Добавляем третий раз usersMusicTypes7 с другим musicTypeId
        usersMusicTypes7 = new UsersMusicTypes(7, 2);
        result = MODEL_LOGIC.addUsersMusicTypes(usersMusicTypes7);
        assertEquals(result.get(0).getUserId(), usersMusicTypes7.getUserId());
        assertEquals(result.get(0).getMusicTypeId(), usersMusicTypes7.getMusicTypeId());
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            usersMusicTypes = new UsersMusicTypes(3, 3);
        } else {
            usersMusicTypes = new UsersMusicTypes(6, 6);
        }
        expected.add(usersMusicTypes);
        MODEL_LOGIC.updateUsersMusicTypes(usersMusicTypes);
        result = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());


        // Попытаемся обновить не существующего UsersMusicTypes
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(8);
        result = MODEL_LOGIC.updateUsersMusicTypes(usersMusicTypes);
        assertThat(result.size(), is(0));
    }

    @Test
    public void deleteUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            usersMusicTypes = new UsersMusicTypes(3, 3);
        } else {
            usersMusicTypes = new UsersMusicTypes(6, 6);
        }
        expected.add(usersMusicTypes);
        result = MODEL_LOGIC.deleteUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());


        // Попытаемся удалить не существующего UsersMusicTypes
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(8);
        result = MODEL_LOGIC.deleteUsersMusicTypes(usersMusicTypes);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes;
        List<UsersMusicTypes> result;
        List<UsersMusicTypes> expected = new ArrayList<>();
        if (MODEL_LOGIC.getPersistent().getClass().getName().equals("com.solomatoff.mvc.model.store.DbStore")) {
            usersMusicTypes = new UsersMusicTypes(3, 3);
        } else {
            usersMusicTypes = new UsersMusicTypes(6, 6);
        }
        expected.add(usersMusicTypes);
        result = MODEL_LOGIC.findByIdUsersMusicTypes(usersMusicTypes);
        assertEquals(result.get(0).getUserId(), expected.get(0).getUserId());
        assertEquals(result.get(0).getMusicTypeId(), expected.get(0).getMusicTypeId());
    }

    @Test
    public void findAllUsersMusicTypess() {
        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        List<UsersMusicTypes> result = MODEL_LOGIC.findAllUsersMusicTypes(new UsersMusicTypes());
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

        List<UsersMusicTypes> result = MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());
        int i = 0;
        for (UsersMusicTypes usersMusicTypesloop : result) {
            assertEquals(usersMusicTypesloop .getUserId(), expected.get(i).getUserId());
            assertEquals(usersMusicTypesloop .getMusicTypeId(), expected.get(i).getMusicTypeId());
            i++;
        }
    }
}