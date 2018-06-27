package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.dao.*;
import com.solomatoff.mvc.model.store.memorystore.*;

import java.util.ArrayList;
import java.util.List;

public interface PersistentDAO extends AddressDAO, MusicTypeDAO, RoleDAO, UserDAO, UserRolesDAO, UsersMusicTypesDAO {

    default boolean isCredentional(String login, String password) {
        boolean exists = false;
        List<User> users = findAllUsers(new User());
        for (User user: users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exists = true;
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info("(isCredentional) Find user with login = " + login);
                break;
            }
        }
        return exists;
    }

    default List<User> findByLoginUser(User user) {
        List<User> result = new ArrayList<>();
        List<User> users = findAllUsers(new User());
        if (users != null && users.size() > 0) {
            for (User userloop : users) {
                if (userloop.getLogin().equals(user.getLogin())) {
                    result.add(userloop);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("(findByLoginUser) Find By Login User: %s", result.get(0)));
                    break;
                }
            }
        }
        return result;
    }

    default List<User> findByIdRoleUser(Role role) {
        List<User> result = new ArrayList<>();
        List<UserRoles> userRoles = findAllUserRoles(new UserRoles());
        for (UserRoles userRolesloop: userRoles) {
            if (userRolesloop.getRoleId().equals(role.getId())) {
                User user1 = new User();
                user1.setId(userRolesloop.getUserId());
                result.add(findByIdUser(user1).get(0));
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("(findByIdRoleUser) Find By IdRole User: %s", result.get(0)));
            }
        }
        return result;
    }

    default List<Role> findByIdUserRoles(User user) {
        List<Role> result = new ArrayList<>();
        List<UserRoles> userRoles = findAllUserRoles(new UserRoles());
        for (UserRoles userRolesloop : userRoles) {
            if (userRolesloop.getUserId().equals(user.getId())) {
                Role role = new Role();
                role.setId(userRolesloop.getRoleId());
                result.add(findByIdRole(role).get(0));
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("(findByIdUserRoles) Find By IdUser Role: %s", result.get(0)));
            }
        }
        return result;
    }


    default List<User> deleteUserAll(User user) {
        List<User> result = new ArrayList<>();
        List<User> users = findAllUsers(new User());
        //System.out.println("users = " + users);
        if (users != null && users.size() > 0) {
            for (User userloop : users) {
                deleteUser(userloop);
                result.add(userloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete User All finish.");
        }
        return result;
    }

    default List<Role> deleteRoleAll(Role role) {
        List<Role> result = new ArrayList<>();
        List<Role> roles = findAllRoles(new Role());
        //System.out.println("roles = " + roles);
        if (roles != null && roles.size() > 0) {
            for (Role roleloop : roles) {
                deleteRole(roleloop);
                result.add(roleloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete Role All finish.");
        }
        return result;
    }

    default List<Address> deleteAllAddresses(Address address) {
        List<Address> result = new ArrayList<>();
        List<Address> addresses = findAllAddresses(new Address());
        //System.out.println("addresses = " + addresses);
        if (addresses != null && addresses.size() > 0) {
            for (Address addressloop: addresses) {
                deleteAddress(addressloop);
                result.add(addressloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete Address All finish.");
        }
        return result;
    }

    default List<MusicType> deleteAllMusicTypes(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        List<MusicType> musicTypes = findAllMusicTypes(new MusicType());
        //System.out.println("musicTypes = " + musicTypes);
        if (musicTypes != null && musicTypes.size() > 0) {
            for (MusicType musicTypeloop: musicTypes) {
                deleteMusicType(musicTypeloop);
                result.add(musicTypeloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete MusicType All finish.");
        }
        return result;
    }

    default List<UserRoles> deleteAllUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        List<UserRoles> userRoleses = findAllUserRoles(new UserRoles());
        //System.out.println("userRoleses = " + userRoleses);
        if (userRoleses != null && userRoleses.size() > 0) {
            for (UserRoles userRolesloop: userRoleses) {
                deleteUserRoles(userRolesloop);
                result.add(userRolesloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete UserRoles All finish.");
        }
        return result;
    }

    default List<UsersMusicTypes> deleteAllUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        List<UsersMusicTypes> usersMusicTypeses = findAllUsersMusicTypes(new UsersMusicTypes());
        //System.out.println("usersMusicTypeses = " + usersMusicTypeses);
        if (usersMusicTypeses != null && usersMusicTypeses.size() > 0) {
            for (UsersMusicTypes usersMusicTypesloop: usersMusicTypeses) {
                deleteUsersMusicTypes(usersMusicTypesloop);
                result.add(usersMusicTypesloop);
            }
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete UsersMusicTypes All finish.");
        }
        return result;
    }
}
