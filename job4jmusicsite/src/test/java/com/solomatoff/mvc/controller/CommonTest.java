package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;

import java.sql.Timestamp;

public class CommonTest {
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    public static void clearAndCreateDataInDataBase() {
        PersistentDAO persistentDAO = DbStore.getInstance();
        MODEL_LOGIC.setPersistent(persistentDAO);

        // Удаляем все UsersMusicTypes
        MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());
        // Удаляем все UserRoles
        MODEL_LOGIC.deleteAllUserRoles(new UserRoles());
        // Удаляем все Addresses
        MODEL_LOGIC.deleteAllAddresses(new Address());
        // Удаляем всех Users
        MODEL_LOGIC.deleteUserAll(new User());
        // Удаляем все MusicTypes
        MODEL_LOGIC.deleteAllMusicTypes(new MusicType());
        // Удаляем все Roles
        MODEL_LOGIC.deleteRoleAll(new Role());

        // Добавляем новые три Roles
        MODEL_LOGIC.addRole(new Role(1, "role1", true));
        MODEL_LOGIC.addRole(new Role(2, "role2", false));
        MODEL_LOGIC.addRole(new Role(3, "role3", false));

        // Добавляем новых трех Users
        MODEL_LOGIC.addUser(new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis())));
        MODEL_LOGIC.addUser(new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis())));
        MODEL_LOGIC.addUser(new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis())));

        // Добавляем новые три Addresses
        MODEL_LOGIC.addAddress(new Address(1, "street1", "house1", "apartment1", "city1", "zipcode1", "country1"));
        MODEL_LOGIC.addAddress(new Address(2, "street2", "house2", "apartment2", "city2", "zipcode2", "country2"));
        MODEL_LOGIC.addAddress(new Address(3, "street3", "house3", "apartment3", "city3", "zipcode3", "country3"));

        // Добавляем новые три MusicTypes
        MODEL_LOGIC.addMusicType(new MusicType(1, "musicType1"));
        MODEL_LOGIC.addMusicType(new MusicType(2, "musicType2"));
        MODEL_LOGIC.addMusicType(new MusicType(3, "musicType3"));

        // Добавляем новые три UserRoles
        MODEL_LOGIC.addUserRoles(new UserRoles(1, 1));
        MODEL_LOGIC.addUserRoles(new UserRoles(2, 2));
        MODEL_LOGIC.addUserRoles(new UserRoles(3, 3));

        // Добавляем новые три UsersMusicTypes
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(1, 1));
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(2, 2));
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(3, 3));
    }

    public static void clearAndCreateDataInMemory() {
        PersistentDAO persistentDAO = MemoryStore.getInstance();
        MODEL_LOGIC.setPersistent(persistentDAO);

        // Удаляем все UsersMusicTypes
        MODEL_LOGIC.deleteAllUsersMusicTypes(new UsersMusicTypes());
        // Удаляем все UserRoles
        MODEL_LOGIC.deleteAllUserRoles(new UserRoles());
        // Удаляем все Addresses
        MODEL_LOGIC.deleteAllAddresses(new Address());
        // Удаляем всех Users
        MODEL_LOGIC.deleteUserAll(new User());
        // Удаляем все MusicTypes
        MODEL_LOGIC.deleteAllMusicTypes(new MusicType());
        // Удаляем все Roles
        MODEL_LOGIC.deleteRoleAll(new Role());

        // Добавляем новые три Roles
        MODEL_LOGIC.addRole(new Role(4, "role4", true));
        MODEL_LOGIC.addRole(new Role(5, "role5", false));
        MODEL_LOGIC.addRole(new Role(6, "role6", false));

        // Добавляем новых трех Users
        MODEL_LOGIC.addUser(new User(4, "user4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis())));
        MODEL_LOGIC.addUser(new User(5, "user5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis())));
        MODEL_LOGIC.addUser(new User(6, "user6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis())));

        // Добавляем новые три Addresses
        MODEL_LOGIC.addAddress(new Address(4, "street4", "house4", "apartment4", "city4", "zipcode4", "country4"));
        MODEL_LOGIC.addAddress(new Address(5, "street5", "house5", "apartment5", "city5", "zipcode5", "country5"));
        MODEL_LOGIC.addAddress(new Address(6, "street6", "house6", "apartment6", "city6", "zipcode6", "country6"));

        // Добавляем новые три MusicTypes
        MODEL_LOGIC.addMusicType(new MusicType(4, "musicType4"));
        MODEL_LOGIC.addMusicType(new MusicType(5, "musicType5"));
        MODEL_LOGIC.addMusicType(new MusicType(6, "musicType6"));

        // Добавляем новые три UserRoles
        MODEL_LOGIC.addUserRoles(new UserRoles(4, 4));
        MODEL_LOGIC.addUserRoles(new UserRoles(5, 5));
        MODEL_LOGIC.addUserRoles(new UserRoles(6, 6));

        // Добавляем новые три UsersMusicTypes
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(4, 4));
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(5, 5));
        MODEL_LOGIC.addUsersMusicTypes(new UsersMusicTypes(6, 6));
    }
}
