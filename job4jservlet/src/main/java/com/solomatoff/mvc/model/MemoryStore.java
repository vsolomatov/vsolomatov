package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemoryStore implements ModelStore {
    private static MemoryStore singletonInstance = new MemoryStore();
    public static MemoryStore getInstance() {
        return singletonInstance;
    }
     /**
     * Contains users
     */
    private static final Map<Integer, User> USER_MAP = new ConcurrentSkipListMap<>();
    private static final Map<Integer, Role> ROLE_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<User> addUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.get(user.getId());
        // Добавляем только, если еще не существует
        if (userResult == null) {
            USER_MAP.put(user.getId(), user);
            result.add(user); // добавляем нового пользователя в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add: %s", user));
        }
        return result;
    }

    @Override
    public List<User> updateUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.put(user.getId(), user);
        if (userResult != null) {
            //System.out.println(String.format("    Result Upadte. User: <%4d> <%s> <%s> <%s> <%s>", userResult.getId(), userResult.getName(), userResult.getLogin(), userResult.getEmail(), userResult.getIdRole()));
            result.add(userResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Update: %s", userResult));
        }
        return result;
    }

    @Override
    public List<User> deleteUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.remove(user.getId());
        if (userResult != null) {
            //System.out.println(String.format("    Result Delete. User: <%4d> <%s> <%s> <%s> <%s>", userResult.getId(), userResult.getName(), userResult.getLogin(), userResult.getEmail(), userResult.getIdRole()));
            result.add(userResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete: %s", userResult));
        }
        return result;
    }

    @Override
    public List<User> findByIdUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.get(user.getId());
        if (userResult != null) {
            result.add(userResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id: %s", userResult));
        }
        return result;
    }

    @Override
    public List<User> findAllUsers(User user) {
        List<User> result = new ArrayList<>(USER_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Users.");
        LoggerApp.getInstance().saveUsersToLog(result);
        return result;
    }

    @Override
    public List<Role> addRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.get(role.getId());
        // Добавляем только, если еще не существует
        if (roleResult == null) {
            ROLE_MAP.put(role.getId(), role);
            result.add(role); // добавляем нового пользователя в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add: %s", role));
        }
        return result;
    }

    @Override
    public List<Role> updateRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.put(role.getId(), role);
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Update: %s", roleResult));
        }
        return result;
    }

    @Override
    public List<Role> deleteRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.remove(role.getId());
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete: %s", roleResult));
        }
        return result;
    }

    @Override
    public List<Role> findByIdRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.get(role.getId());
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id: %s", roleResult));
        }
        return result;
    }

    @Override
    public List<Role> findAllRoles(Role role) {
        List<Role> result = new ArrayList<>(ROLE_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Roles.");
        return result;
    }
}
