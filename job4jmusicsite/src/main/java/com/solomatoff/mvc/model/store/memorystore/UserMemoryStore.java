package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.dao.UserDAO;
import com.solomatoff.mvc.model.store.MemoryStore;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class UserMemoryStore implements UserDAO {
    /**
     * Contains users
     */
    private static final Map<Integer, User> USER_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<User> addUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.get(user.getId());
        // Добавляем только, если еще не существует
        if (userResult == null) {
            USER_MAP.put(user.getId(), user);
            result.add(user); // добавляем нового пользователя в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", user));
        }
        return result;
    }

    @Override
    public List<User> updateUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.put(user.getId(), user);
        if (userResult != null) {
            result.add(userResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", userResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", user));
        }
        return result;
    }

    @Override
    public List<User> deleteUser(User user) {
        List<User> result = new ArrayList<>();
        User userResult = USER_MAP.remove(user.getId());
        if (userResult != null) {
            result.add(userResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", userResult));
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
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", userResult));
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

}
