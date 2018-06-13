package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface ModelStore {
    List<User> addUser(User user);
    List<User> updateUser(User user);
    List<User> deleteUser(User user);
    List<User> findByIdUser(User user);
    List<User> findAllUsers(User user);

    List<Role> addRole(Role role);
    List<Role> updateRole(Role role);
    List<Role> deleteRole(Role role);
    List<Role> findByIdRole(Role role);
    List<Role> findAllRoles(Role role);

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
        for (User userloop: users) {
            if (userloop.getLogin().equals(user.getLogin())) {
                result.add(userloop);
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("(findByLoginUser) Find By Login User: %s", result.get(0)));
                break;
            }
        }
        return result;
    }

    default List<User> findByIdRoleUser(User user) {
        List<User> result = new ArrayList<>();
        List<User> users = findAllUsers(new User());
        for (User userloop: users) {
            if (userloop.getIdRole().equals(user.getIdRole())) {
                result.add(userloop);
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("(findByIdRoleUser) Find By IdRole User: %s", result.get(0)));
            }
        }
        return result;
    }

    default List<User> deleteUserAll(User user) {
        List<User> result = new ArrayList<>();
        List<User> users = findAllUsers(new User());
        for (User userloop: users) {
            deleteUser(userloop);
            result.add(userloop);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete User All finish.");
        }
        return result;
    }

    default List<Role> deleteRoleAll(Role role) {
        List<Role> result = new ArrayList<>();
        List<Role> roles = findAllRoles(new Role());
        for (Role roleloop: roles) {
            deleteRole(roleloop);
            result.add(roleloop);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info("Delete Role All finish.");
        }
        return result;
    }
}
