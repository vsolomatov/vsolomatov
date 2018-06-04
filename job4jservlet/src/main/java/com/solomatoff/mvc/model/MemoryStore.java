package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerUtil;
import com.solomatoff.mvc.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements ModelStore {
     /**
     * Contains users
     */
    private static final Map<Integer, User> USER_MAP = new ConcurrentHashMap<>();

    @Override
    public List<User> add(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.put(user.getId(), user)); // add возвращает предыдущее значение  по ключу, значит null
        // Записываем в LOG
        Controller.getInstance().getLog().info(String.format("    Add User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> update(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.put(user.getId(), user));
        // Записываем в LOG
        Controller.getInstance().getLog().info(String.format("    Update User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> delete(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.remove(user.getId()));
        // Записываем в LOG
        Controller.getInstance().getLog().info(String.format("    Delete User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> findById(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.get(user.getId()));
        // Записываем в LOG
        Controller.getInstance().getLog().info(String.format("    Find User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> findAll(User user) {
        List<User> result = new ArrayList<>(USER_MAP.values());
        // Записываем в LOG
        Controller.getInstance().getLog().info("  Find All Users.");
        new LoggerUtil().saveUsersToLog(result, Controller.getInstance().getLog());
        return result;
    }
}
