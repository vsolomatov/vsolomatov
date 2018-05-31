package com.solomatoff.crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Класс представляет собой слой Logic
 */
public class ValidateService {
    // CorePresentation - слой Presentation
    private static final Logger LOGGER = CorePresentation.getLOG();
    // Слой Persistent
    private final Store persistent  = DbStore.getInstance();

    private static ValidateService ourInstance = null;
    public static ValidateService getInstance() {
        if (ourInstance == null) {
            synchronized (ValidateService.class) {
                if (ourInstance == null) {
                    ourInstance = new ValidateService();
                }
            }
        }
        return ourInstance;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public List<User> add(User user) {
        if (persistent.findById(user).get(0) == null) {
            return persistent.add(user);
        } else {
            LOGGER.error(String.format("(ADD) user with id = %4d already exists", user.getId()));
            return null;
        }
    }

    public List<User> update(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.update(user);
        } else {
            LOGGER.error(String.format("(UPDATE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> delete(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.delete(user);
        } else {
            LOGGER.error(String.format("(DELETE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findById(User user) {
        List<User> users = persistent.findById(user);
        if (users.get(0) != null) {
            return users;
        } else {
            LOGGER.error(String.format("(FINDBYID) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findAll(User user) {
        return persistent.findAll(user);
    }
}
