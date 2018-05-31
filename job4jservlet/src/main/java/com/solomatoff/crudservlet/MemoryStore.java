package com.solomatoff.crudservlet;

import org.slf4j.Logger;
import org.w3c.dom.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    // ValidateService - cлой Logic
    private static final Logger LOG = ValidateService.getLOGGER();

    private static MemoryStore ourInstance = null;

    public static MemoryStore getInstance() {
        if (ourInstance == null) {
            synchronized (MemoryStore.class) {
                if (ourInstance == null) {
                    ourInstance = new MemoryStore();
                }
            }
        }
        return ourInstance;
    }
    /**
     * Contains users
     */
    private static final Map<Integer, User> USER_MAP = new ConcurrentHashMap<>();

    @Override
    public List<User> add(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.put(user.getId(), user)); // add возвращает предыдущее значение  по ключу, значит null
        // Записываем в LOG
        LOG.info(String.format("    Add User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> update(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.put(user.getId(), user));
        // Записываем в LOG
        LOG.info(String.format("    Update User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> delete(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.remove(user.getId()));
        // Записываем в LOG
        LOG.info(String.format("    Delete User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> findById(User user) {
        List<User> result = new ArrayList<>();
        result.add(USER_MAP.get(user.getId()));
        // Записываем в LOG
        LOG.info(String.format("    Find User: <%4d> <%s> <%s> <%s>", user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        return result;
    }

    @Override
    public List<User> findAll(User user) {
        List<User> result = new ArrayList<>(USER_MAP.values());
        // Записываем в LOG
        LOG.info("  Find All Users.");
        return result;
    }
}
