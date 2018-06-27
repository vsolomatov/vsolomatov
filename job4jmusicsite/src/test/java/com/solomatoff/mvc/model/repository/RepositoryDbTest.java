package com.solomatoff.mvc.model.repository;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.RepositoryDb;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationAllAddress;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationById;
import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationAllMusicTypes;
import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationByIdRange;
import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationAllRoles;
import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationByIdRange;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationAllUsers;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationByIdRange;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationByUserLogin;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationAllUserRoles;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByIdId;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationAllUsersMusicTypes;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByIdId;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RepositoryDbTest {
    private final static RepositorySql REPOSITORY_SQL = RepositoryDb.getInstance();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    private final UserSpecificationByIdRange userSpecificationByIdRange1 = new UserSpecificationByIdRange(1, 1);
    private final UserSpecificationByIdRange userSpecificationByIdRange2 = new UserSpecificationByIdRange(2, 2);
    private final UserSpecificationByIdRange userSpecificationByIdRange3 = new UserSpecificationByIdRange(3, 3);
    private final UserSpecificationAllUsers userSpecificationAllUsers = new UserSpecificationAllUsers();
    private final UserSpecificationByUserLogin userSpecificationByUserLogin1 = new UserSpecificationByUserLogin("login1");
    private final UserSpecificationByUserLogin userSpecificationByUserLogin2 = new UserSpecificationByUserLogin("login2");
    private final UserSpecificationByUserLogin userSpecificationByUserLogin3 = new UserSpecificationByUserLogin("login3");

    private final AddressSpecificationById addressSpecificationById1 = new AddressSpecificationById(1);
    private final AddressSpecificationById addressSpecificationById2 = new AddressSpecificationById(2);
    private final AddressSpecificationById addressSpecificationById3 = new AddressSpecificationById(3);
    private final AddressSpecificationAllAddress addressSpecificationAllAddress = new AddressSpecificationAllAddress();

    private final RoleSpecificationByIdRange roleSpecificationByIdRange1 = new RoleSpecificationByIdRange(1, 1);
    private final RoleSpecificationByIdRange roleSpecificationByIdRange2 = new RoleSpecificationByIdRange(2, 2);
    private final RoleSpecificationByIdRange roleSpecificationByIdRange3 = new RoleSpecificationByIdRange(3, 3);
    private final RoleSpecificationAllRoles roleSpecificationAllRole = new RoleSpecificationAllRoles();

    private final MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange1 = new MusicTypeSpecificationByIdRange(1, 1);
    private final MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange2 = new MusicTypeSpecificationByIdRange(2, 2);
    private final MusicTypeSpecificationByIdRange musicTypeSpecificationByIdRange3 = new MusicTypeSpecificationByIdRange(3, 3);
    private final MusicTypeSpecificationAllMusicTypes musicTypeSpecificationAllMusicType = new MusicTypeSpecificationAllMusicTypes();

    private final UserRolesSpecificationByIdId userRolesSpecificationByIdId1 = new UserRolesSpecificationByIdId(1, 1);
    private final UserRolesSpecificationByIdId userRolesSpecificationByIdId2 = new UserRolesSpecificationByIdId(2, 2);
    private final UserRolesSpecificationByIdId userRolesSpecificationByIdId3 = new UserRolesSpecificationByIdId(3, 3);
    private final UserRolesSpecificationAllUserRoles userRolesSpecificationAllUserRoles = new UserRolesSpecificationAllUserRoles();

    private final UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId1 = new UsersMusicTypesSpecificationByIdId(1, 1);
    private final UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId2 = new UsersMusicTypesSpecificationByIdId(2, 2);
    private final UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId3 = new UsersMusicTypesSpecificationByIdId(3, 3);
    private final UsersMusicTypesSpecificationAllUsersMusicTypes usersMusicTypesSpecificationAllUsersMusicTypes = new UsersMusicTypesSpecificationAllUsersMusicTypes();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<Address> makeTestAddressAll() {
        return DB_STORE.findAllAddresses(new Address());
    }

    private List<User> makeTestUserAll() {
        return DB_STORE.findAllUsers(new User());
    }

    private List<Role> makeTestRoleAll() {
        return DB_STORE.findAllRoles(new Role());
    }

    private List<UserRoles> makeTestUserRolesAll() {
        return DB_STORE.findAllUserRoles(new UserRoles());
    }

    private List<MusicType> makeTestMusicTypeAll() {
        return DB_STORE.findAllMusicTypes(new MusicType());
    }

    private List<UsersMusicTypes> makeTestUsersMusicTypesAll() {
        return DB_STORE.findAllUsersMusicTypes(new UsersMusicTypes());
    }


    @Test
    public void addUser() {
        SqlSpecification specification = new UserSpecificationByIdRange(4, 4);
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        List<User> expected = new ArrayList<>();
        expected.add(user4);

        // Добавляем первый раз user4
        REPOSITORY_SQL.addUser(user4);

        List result = REPOSITORY_SQL.queryUser(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз user4
        REPOSITORY_SQL.addUser(user4);
        result = REPOSITORY_SQL.queryUser(specification);
        assertThat(result.size(), is(1)); // Так и остался один User
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(3);

        // Обновим пользователя с id=3
        User newUser3 = new User(3, "newname3", "newlogin3", "password", "newemail3", new Timestamp(System.currentTimeMillis()));
        REPOSITORY_SQL.updateUser(newUser3);
        List<User> expected = new ArrayList<>();
        expected.add(newUser3);

        // Проверяем
        List<User> result = REPOSITORY_SQL.queryUser(userSpecificationByIdRange3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего пользователя
        user = new User();
        user.setId(8);
        REPOSITORY_SQL.updateUser(user);

        // Проверяем
        SqlSpecification specification = new UserSpecificationByIdRange(8, 8);
        List list =  REPOSITORY_SQL.queryUser(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeUser() {
        SqlSpecification specification = new UserSpecificationByIdRange(4, 4);
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));

        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        // Удалим пользователя с id = 4
        REPOSITORY_SQL.removeUser(user4);

        // Проверяем
        List result = REPOSITORY_SQL.queryUser(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryUser1() {
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()));
        User result = (User) REPOSITORY_SQL.queryUser(userSpecificationByIdRange1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void queryUser2() {
        User user2 = new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()));
        User result = (User) REPOSITORY_SQL.queryUser(userSpecificationByIdRange2).get(0);
        assertThat(result.getName(), is(user2.getName()));
    }

    @Test
    public void queryUserByLogin1() {
        User user1 = new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()));
        User result = (User) REPOSITORY_SQL.queryUser(userSpecificationByUserLogin1).get(0);
        assertThat(result.getName(), is(user1.getName()));
    }

    @Test
    public void queryUserByLogin2() {
        User user2 = new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()));
        User result = (User) REPOSITORY_SQL.queryUser(userSpecificationByUserLogin2).get(0);
        assertThat(result.getName(), is(user2.getName()));
    }

    @Test
    public void queryUserByLogin3() {
        User user3 = new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()));
        User result = (User) REPOSITORY_SQL.queryUser(userSpecificationByUserLogin3).get(0);
        assertThat(result.getName(), is(user3.getName()));
    }

    @Test
    public void queryUserAll() {
        List result = REPOSITORY_SQL.queryUser(userSpecificationAllUsers);
        //System.out.println("result = " + result);
        List<User> expected = makeTestUserAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }


    @Test
    public void addRole() {
        SqlSpecification specification = new RoleSpecificationByIdRange(4, 4);
        Role role4 = new Role(4, "name4", false);
        List<Role> expected = new ArrayList<>();
        expected.add(role4);

        // Добавляем первый раз role4
        REPOSITORY_SQL.addRole(role4);

        List result = REPOSITORY_SQL.queryRole(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз role4
        REPOSITORY_SQL.addRole(role4);
        result = REPOSITORY_SQL.queryRole(specification);
        assertThat(result.size(), is(1)); // Так и остался один Role
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setId(3);

        // Обновим пользователя с id=3
        Role newRole3 = new Role(3, "newname3", false);
        REPOSITORY_SQL.updateRole(newRole3);
        List<Role> expected = new ArrayList<>();
        expected.add(newRole3);

        // Проверяем
        List<Role> result = REPOSITORY_SQL.queryRole(roleSpecificationByIdRange3);
        assertThat(result.get(0).getName(), is(expected.get(0).getName()));

        // Попытаемся обновить не существующего пользователя
        role = new Role();
        role.setId(8);
        REPOSITORY_SQL.updateRole(role);

        // Проверяем
        SqlSpecification specification = new RoleSpecificationByIdRange(8, 8);
        List list =  REPOSITORY_SQL.queryRole(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeRole() {
        SqlSpecification specification = new RoleSpecificationByIdRange(4, 4);
        Role role4 = new Role(4, "name4", false);

        // Добавляем role4
        REPOSITORY_SQL.addRole(role4);

        // Удалим пользователя с id = 4
        REPOSITORY_SQL.removeRole(role4);

        // Проверяем
        List result = REPOSITORY_SQL.queryRole(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryRole1() {
        Role role1 = new Role(1, "role1", true);
        Role result = (Role) REPOSITORY_SQL.queryRole(roleSpecificationByIdRange1).get(0);
        assertThat(result.getName(), is(role1.getName()));
    }

    @Test
    public void queryRole2() {
        Role role2 = new Role(2, "role2", false);
        Role result = (Role) REPOSITORY_SQL.queryRole(roleSpecificationByIdRange2).get(0);
        assertThat(result.getName(), is(role2.getName()));
    }

    @Test
    public void queryRoleAll() {
        List result = REPOSITORY_SQL.queryRole(roleSpecificationAllRole);
        //System.out.println("result = " + result);
        List<Role> expected = makeTestRoleAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }


    @Test
    public void addMusicType() {
        SqlSpecification specification = new MusicTypeSpecificationByIdRange(4, 4);
        MusicType musicType4 = new MusicType(4, "musicType4");
        List<MusicType> expected = new ArrayList<>();
        expected.add(musicType4);

        // Добавляем первый раз musicType4
        REPOSITORY_SQL.addMusicType(musicType4);

        List result = REPOSITORY_SQL.queryMusicType(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз musicType4
        REPOSITORY_SQL.addMusicType(musicType4);
        result = REPOSITORY_SQL.queryMusicType(specification);
        assertThat(result.size(), is(1)); // Так и остался один MusicType
    }

    @Test
    public void updateMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(3);

        // Обновим пользователя с id=3
        MusicType newMusicType3 = new MusicType(3, "newmusicType3");
        REPOSITORY_SQL.updateMusicType(newMusicType3);
        List<MusicType> expected = new ArrayList<>();
        expected.add(newMusicType3);

        // Проверяем
        List<MusicType> result = REPOSITORY_SQL.queryMusicType(musicTypeSpecificationByIdRange3);
        assertThat(result.get(0).getMusicTypeName(), is(expected.get(0).getMusicTypeName()));

        // Попытаемся обновить не существующего пользователя
        musicType = new MusicType();
        musicType.setId(8);
        REPOSITORY_SQL.updateMusicType(musicType);

        // Проверяем
        SqlSpecification specification = new MusicTypeSpecificationByIdRange(8, 8);
        List list =  REPOSITORY_SQL.queryMusicType(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeMusicType() {
        SqlSpecification specification = new MusicTypeSpecificationByIdRange(4, 4);
        MusicType musicType4 = new MusicType(4, "musicType4");

        // Добавляем musicType4
        REPOSITORY_SQL.addMusicType(musicType4);

        // Удалим пользователя с id = 4
        REPOSITORY_SQL.removeMusicType(musicType4);

        // Проверяем
        List result = REPOSITORY_SQL.queryMusicType(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryMusicType1() {
        MusicType musicType1 = new MusicType(1, "musicType1");
        MusicType result = (MusicType) REPOSITORY_SQL.queryMusicType(musicTypeSpecificationByIdRange1).get(0);
        assertThat(result.getMusicTypeName(), is(musicType1.getMusicTypeName()));
    }

    @Test
    public void queryMusicType2() {
        MusicType musicType2 = new MusicType(2, "musicType2");
        MusicType result = (MusicType) REPOSITORY_SQL.queryMusicType(musicTypeSpecificationByIdRange2).get(0);
        assertThat(result.getMusicTypeName(), is(musicType2.getMusicTypeName()));
    }

    @Test
    public void queryMusicTypeAll() {
        List result = REPOSITORY_SQL.queryMusicType(musicTypeSpecificationAllMusicType);
        //System.out.println("result = " + result);
        List<MusicType> expected = makeTestMusicTypeAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }


    @Test
    public void addUserRoles() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        SqlSpecification specification = new UserRolesSpecificationByIdId(4, 3);
        UserRoles userRoles4 = new UserRoles(4, 3);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(userRoles4);

        // Добавляем первый раз userRoles4
        REPOSITORY_SQL.addUserRoles(userRoles4);

        List result = REPOSITORY_SQL.queryUserRoles(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз userRoles4
        REPOSITORY_SQL.addUserRoles(userRoles4);
        result = REPOSITORY_SQL.queryUserRoles(specification);
        assertThat(result.size(), is(1)); // Так и остался один UserRoles
    }

    @Test
    public void updateUserRoles() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(3);

        // Обновим пользователя с id=3
        UserRoles newUserRoles3 = new UserRoles(3, 3);
        REPOSITORY_SQL.updateUserRoles(newUserRoles3);
        List<UserRoles> expected = new ArrayList<>();
        expected.add(newUserRoles3);

        // Проверяем
        List<UserRoles> result = REPOSITORY_SQL.queryUserRoles(userRolesSpecificationByIdId3);
        assertThat(result.get(0).getRoleId(), is(expected.get(0).getRoleId()));

        // Попытаемся обновить не существующего пользователя
        userRoles = new UserRoles();
        userRoles.setUserId(8);
        REPOSITORY_SQL.updateUserRoles(userRoles);

        // Проверяем
        SqlSpecification specification = new UserRolesSpecificationByIdId(8, 8);
        List list =  REPOSITORY_SQL.queryUserRoles(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeUserRoles() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        SqlSpecification specification = new UserRolesSpecificationByIdId(4, 3);
        UserRoles userRoles4 = new UserRoles(4, 3);

        // Добавляем userRoles4
        REPOSITORY_SQL.addUserRoles(userRoles4);

        // Удалим пользователя с id = 4
        REPOSITORY_SQL.removeUserRoles(userRoles4);

        // Проверяем
        List result = REPOSITORY_SQL.queryUserRoles(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryUserRoles1() {
        UserRoles userRoles1 = new UserRoles(1, 1);
        UserRoles result = (UserRoles) REPOSITORY_SQL.queryUserRoles(userRolesSpecificationByIdId1).get(0);
        assertThat(result.getRoleId(), is(userRoles1.getRoleId()));
    }

    @Test
    public void queryUserRoles2() {
        UserRoles userRoles2 = new UserRoles(2, 2);
        UserRoles result = (UserRoles) REPOSITORY_SQL.queryUserRoles(userRolesSpecificationByIdId2).get(0);
        assertThat(result.getRoleId(), is(userRoles2.getRoleId()));
    }

    @Test
    public void queryUserRolesAll() {
        List result = REPOSITORY_SQL.queryUserRoles(userRolesSpecificationAllUserRoles);
        //System.out.println("result = " + result);
        List<UserRoles> expected = makeTestUserRolesAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }


    @Test
    public void addUsersMusicTypes() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(4, 3);
        UsersMusicTypes usersMusicTypes4 = new UsersMusicTypes(4, 3);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(usersMusicTypes4);

        // Добавляем первый раз usersMusicTypes4
        REPOSITORY_SQL.addUsersMusicTypes(usersMusicTypes4);

        List result = REPOSITORY_SQL.queryUsersMusicTypes(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз usersMusicTypes4
        REPOSITORY_SQL.addUsersMusicTypes(usersMusicTypes4);
        result = REPOSITORY_SQL.queryUsersMusicTypes(specification);
        assertThat(result.size(), is(1)); // Так и остался один UsersMusicTypes
    }

    @Test
    public void updateUsersMusicTypes() {
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);

        // Обновим пользователя с id=3
        UsersMusicTypes newUsersMusicTypes3 = new UsersMusicTypes(3, 3);
        REPOSITORY_SQL.updateUsersMusicTypes(newUsersMusicTypes3);
        List<UsersMusicTypes> expected = new ArrayList<>();
        expected.add(newUsersMusicTypes3);

        // Проверяем
        List<UsersMusicTypes> result = REPOSITORY_SQL.queryUsersMusicTypes(usersMusicTypesSpecificationByIdId3);
        assertThat(result.get(0).getMusicTypeId(), is(expected.get(0).getMusicTypeId()));

        // Попытаемся обновить не существующего пользователя
        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(8);
        REPOSITORY_SQL.updateUsersMusicTypes(usersMusicTypes);

        // Проверяем
        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(8, 8);
        List list =  REPOSITORY_SQL.queryUsersMusicTypes(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeUsersMusicTypes() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        SqlSpecification specification = new UsersMusicTypesSpecificationByIdId(4, 3);
        UsersMusicTypes usersMusicTypes4 = new UsersMusicTypes(4, 3);

        // Добавляем usersMusicTypes4
        REPOSITORY_SQL.addUsersMusicTypes(usersMusicTypes4);

        // Удалим пользователя с id = 4
        REPOSITORY_SQL.removeUsersMusicTypes(usersMusicTypes4);

        // Проверяем
        List result = REPOSITORY_SQL.queryUsersMusicTypes(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryUsersMusicTypes1() {
        UsersMusicTypes usersMusicTypes1 = new UsersMusicTypes(1, 1);
        UsersMusicTypes result = (UsersMusicTypes) REPOSITORY_SQL.queryUsersMusicTypes(usersMusicTypesSpecificationByIdId1).get(0);
        assertThat(result.getMusicTypeId(), is(usersMusicTypes1.getMusicTypeId()));
    }

    @Test
    public void queryUsersMusicTypes2() {
        UsersMusicTypes usersMusicTypes2 = new UsersMusicTypes(2, 2);
        UsersMusicTypes result = (UsersMusicTypes) REPOSITORY_SQL.queryUsersMusicTypes(usersMusicTypesSpecificationByIdId2).get(0);
        assertThat(result.getMusicTypeId(), is(usersMusicTypes2.getMusicTypeId()));
    }

    @Test
    public void queryUsersMusicTypesAll() {
        List result = REPOSITORY_SQL.queryUsersMusicTypes(usersMusicTypesSpecificationAllUsersMusicTypes);
        //System.out.println("result = " + result);
        List<UsersMusicTypes> expected = makeTestUsersMusicTypesAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }


    @Test
    public void addAddress() {
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));
        // Добавляем user4
        REPOSITORY_SQL.addUser(user4);

        SqlSpecification specification = new AddressSpecificationById(4);
        Address address4 = new Address(4, "street4", "house4", "apartment4", "city4", "zipcode4", "country4");
        List<Address> expected = new ArrayList<>();
        expected.add(address4);

        // Добавляем первый раз address4
        REPOSITORY_SQL.addAddress(address4);

        List result = REPOSITORY_SQL.queryAddress(specification);
        assertThat(result, is(expected));

        // Добавляем второй раз address4
        REPOSITORY_SQL.addAddress(address4);
        result = REPOSITORY_SQL.queryAddress(specification);
        assertThat(result.size(), is(1)); // Так и остался один Address
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setUserId(3);

        // Обновим пользователя с id=3
        Address newAddress3 = new Address(3, "street3", "house3", "apartment3", "city3", "zipcode3", "country3");
        REPOSITORY_SQL.updateAddress(newAddress3);
        List<Address> expected = new ArrayList<>();
        expected.add(newAddress3);

        // Проверяем
        List<Address> result = REPOSITORY_SQL.queryAddress(addressSpecificationById3);
        assertThat(result.get(0).getStreet(), is(expected.get(0).getStreet()));

        // Попытаемся обновить не существующего пользователя
        address = new Address();
        address.setUserId(8);
        REPOSITORY_SQL.updateAddress(address);

        // Проверяем
        SqlSpecification specification = new AddressSpecificationById(8);
        List list =  REPOSITORY_SQL.queryAddress(specification);
        assertThat(list.size(), is(0));
    }

    @Test
    public void removeAddress() {
        SqlSpecification specification = new AddressSpecificationById(3);
        Address address3 = new Address(3, "street3", "house3", "apartment3", "city3", "zipcode3", "country3");

        // Удалим Address с id = 3
        REPOSITORY_SQL.removeAddress(address3);

        // Проверяем
        List result = REPOSITORY_SQL.queryAddress(specification);
        assertThat(result.size(), is(0));
    }

    @Test
    public void queryAddress1() {
        Address address1 = new Address(1, "street1", "house1", "apartment1", "city1", "zipcode1", "country1");
        Address result = (Address) REPOSITORY_SQL.queryAddress(addressSpecificationById1).get(0);
        assertThat(result.getStreet(), is(address1.getStreet()));
    }

    @Test
    public void queryAddress2() {
        Address address2 = new Address(2, "street2", "house2", "apartment2", "city2", "zipcode2", "country2");
        Address result = (Address) REPOSITORY_SQL.queryAddress(addressSpecificationById2).get(0);
        assertThat(result.getStreet(), is(address2.getStreet()));
    }

    @Test
    public void queryAddressAll() {
        List result = REPOSITORY_SQL.queryAddress(addressSpecificationAllAddress);
        //System.out.println("result = " + result);
        List<Address> expected = makeTestAddressAll();
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUserAllRelatedEntities1() {
        List expected = new ArrayList();

        Address address = new Address();
        address.setUserId(1);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        List<UserRoles> userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        List<UsersMusicTypes> usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);
        //System.out.println("expected = " + expected);

        List result = REPOSITORY_SQL.queryUserAllRelatedEntities(userSpecificationByIdRange1);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryUserAllRelatedEntities2() {
        List expected = new ArrayList();

        Address address = new Address();
        address.setUserId(2);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(2);
        List<UserRoles> userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(2);
        List<UsersMusicTypes> usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);
        //System.out.println("expected = " + expected);

        List result = REPOSITORY_SQL.queryUserAllRelatedEntities(userSpecificationByIdRange2);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryUserAllRelatedEntities3() {
        List expected = new ArrayList();

        Address address = new Address();
        address.setUserId(3);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(3);
        List<UserRoles> userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        List<UsersMusicTypes> usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);
        //System.out.println("expected = " + expected);

        List result = REPOSITORY_SQL.queryUserAllRelatedEntities(userSpecificationByIdRange3);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryUserAllRelatedEntitiesAll() {
        List expected = new ArrayList();

        Address address = new Address();
        address.setUserId(1);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(1);
        List<UserRoles> userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        UsersMusicTypes usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(1);
        List<UsersMusicTypes> usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);

        address = new Address();
        address.setUserId(2);
        addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        userRoles = new UserRoles();
        userRoles.setUserId(2);
        userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(2);
        usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);

        address = new Address();
        address.setUserId(3);
        addressList = DB_STORE.findByIdAddress(address);
        expected.add(addressList);

        userRoles = new UserRoles();
        userRoles.setUserId(3);
        userRolesList = DB_STORE.findByIdUserUserRoles(userRoles);
        expected.add(userRolesList);

        usersMusicTypes = new UsersMusicTypes();
        usersMusicTypes.setUserId(3);
        usersMusicTypesList = DB_STORE.findByIdUsersMusicTypes(usersMusicTypes);
        expected.add(usersMusicTypesList);

        //System.out.println("expected = " + expected);

        List result = REPOSITORY_SQL.queryUserAllRelatedEntities(userSpecificationAllUsers);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void addUserWithAllRelatedEntities() {
        List expected = new ArrayList();
        User user4 = new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()));

        List<Address> addressList = new ArrayList<>();
        Address address = new Address(4, "street4", "house4", "apartment4", "city4", "zipcode4", "country4");
        addressList.add(address);
        expected.add(addressList);

        List<UserRoles> userRolesList = new ArrayList<>();
        UserRoles userRoles41 = new UserRoles(4, 1);
        userRolesList.add(userRoles41);
        UserRoles userRoles42 = new UserRoles(4, 2);
        userRolesList.add(userRoles42);
        UserRoles userRoles43 = new UserRoles(4, 3);
        userRolesList.add(userRoles43);
        expected.add(userRolesList);

        List<UsersMusicTypes> usersMusicTypesList = new ArrayList<>();
        UsersMusicTypes usersMusicTypes41 = new UsersMusicTypes(4, 1);
        usersMusicTypesList.add(usersMusicTypes41);
        UsersMusicTypes usersMusicTypes42 = new UsersMusicTypes(4, 2);
        usersMusicTypesList.add(usersMusicTypes42);
        UsersMusicTypes usersMusicTypes43 = new UsersMusicTypes(4, 3);
        usersMusicTypesList.add(usersMusicTypes43);
        expected.add(usersMusicTypesList);

        //System.out.println("expected = " + expected);

        // Собственно вызов проверяемого метода
        REPOSITORY_SQL.addUserWithAllRelatedEntities(user4, address, userRolesList, usersMusicTypesList);

        // Проверяем
        UserSpecificationByIdRange userSpecificationByIdRange4 = new UserSpecificationByIdRange(4, 4);
        List result = REPOSITORY_SQL.queryUserAllRelatedEntities(userSpecificationByIdRange4);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }


    @Test
    public void queryRoleAllRelatedEntities1() {
        List expected = new ArrayList();
        User user = new User();
        user.setId(1);
        User user1 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user1);
        //System.out.println("expected = " + expected);

        RoleSpecificationByIdRange roleSpecificationByIdRange = new RoleSpecificationByIdRange(1, 1);
        List result = REPOSITORY_SQL.queryRoleAllRelatedEntities(roleSpecificationByIdRange1);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryRoleAllRelatedEntities2() {
        List expected = new ArrayList();
        User user = new User();
        user.setId(2);
        User user2 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user2);
        //System.out.println("expected = " + expected);

        RoleSpecificationByIdRange roleSpecificationByIdRange = new RoleSpecificationByIdRange(2, 2);
        List result = REPOSITORY_SQL.queryRoleAllRelatedEntities(roleSpecificationByIdRange2);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryRoleAllRelatedEntities3() {
        List expected = new ArrayList();
        User user = new User();
        user.setId(3);
        User user3 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user3);
        //System.out.println("expected = " + expected);

        RoleSpecificationByIdRange roleSpecificationByIdRange = new RoleSpecificationByIdRange(3, 3);
        List result = REPOSITORY_SQL.queryRoleAllRelatedEntities(roleSpecificationByIdRange3);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }

    @Test
    public void queryRoleAllRelatedEntitiesAll() {
        List expected = new ArrayList();
        User user = new User();
        user.setId(1);
        User user1 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user1);

        user.setId(2);
        User user2 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user2);

        user.setId(3);
        User user3 = DB_STORE.findByIdUser(user).get(0);
        expected.add(user3);
        //System.out.println("expected = " + expected);

        RoleSpecificationAllRoles roleSpecificationAllRoles = new RoleSpecificationAllRoles();
        List result = REPOSITORY_SQL.queryRoleAllRelatedEntities(roleSpecificationAllRoles);
        //System.out.println("result = " + result);

        assertThat(result, is(expected));
    }


    @Test
    public void queryUsersByAddress1() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange1);
        //System.out.println("expected = " + expected);

        Address address = new Address();
        address.setUserId(1);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        List result = REPOSITORY_SQL.queryUsersByAddresses(addressList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByAddress2() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange2);
        //System.out.println("expected = " + expected);

        Address address = new Address();
        address.setUserId(2);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        List result = REPOSITORY_SQL.queryUsersByAddresses(addressList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByAddress3() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange3);
        //System.out.println("expected = " + expected);

        Address address = new Address();
        address.setUserId(3);
        List<Address> addressList = DB_STORE.findByIdAddress(address);
        List result = REPOSITORY_SQL.queryUsersByAddresses(addressList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByAddressAll() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationAllUsers);
        //System.out.println("expected = " + expected);

        List<Address> addressList = DB_STORE.findAllAddresses(new Address());
        List result = REPOSITORY_SQL.queryUsersByAddresses(addressList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }


    @Test
    public void queryUsersByRole1() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange1);
        //System.out.println("expected = " + expected);

        Role role = new Role();
        role.setId(1);
        List<Role> roleList = DB_STORE.findByIdRole(role);
        List result = REPOSITORY_SQL.queryUsersByRoles(roleList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByRole2() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange2);
        //System.out.println("expected = " + expected);

        Role role = new Role();
        role.setId(2);
        List<Role> roleList = DB_STORE.findByIdRole(role);
        List result = REPOSITORY_SQL.queryUsersByRoles(roleList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByRole3() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange3);
        //System.out.println("expected = " + expected);

        Role role = new Role();
        role.setId(3);
        List<Role> roleList = DB_STORE.findByIdRole(role);
        List result = REPOSITORY_SQL.queryUsersByRoles(roleList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByRoleAll() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationAllUsers);
        //System.out.println("expected = " + expected);

        List<Role> roleList = DB_STORE.findAllRoles(new Role());
        List result = REPOSITORY_SQL.queryUsersByRoles(roleList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByMusicType1() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange1);
        //System.out.println("expected = " + expected);

        MusicType musicType = new MusicType();
        musicType.setId(1);
        List<MusicType> musicTypeList = DB_STORE.findByIdMusicType(musicType);
        List result = REPOSITORY_SQL.queryUsersByMusicTypes(musicTypeList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByMusicType2() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange2);
        //System.out.println("expected = " + expected);

        MusicType musicType = new MusicType();
        musicType.setId(2);
        List<MusicType> musicTypeList = DB_STORE.findByIdMusicType(musicType);
        List result = REPOSITORY_SQL.queryUsersByMusicTypes(musicTypeList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByMusicType3() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationByIdRange3);
        //System.out.println("expected = " + expected);

        MusicType musicType = new MusicType();
        musicType.setId(3);
        List<MusicType> musicTypeList = DB_STORE.findByIdMusicType(musicType);
        List result = REPOSITORY_SQL.queryUsersByMusicTypes(musicTypeList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }

    @Test
    public void queryUsersByMusicTypeAll() {
        List expected = REPOSITORY_SQL.queryUser(userSpecificationAllUsers);
        //System.out.println("expected = " + expected);

        List<MusicType> musicTypeList = DB_STORE.findAllMusicTypes(new MusicType());
        List result = REPOSITORY_SQL.queryUsersByMusicTypes(musicTypeList);
        //System.out.println("result = " + result);
        assertThat(result, is(expected));
    }
}