package com.solomatoff.crudservlet;

import java.util.List;

public class ValidateService {
    private static ValidateService ourInstance = null;
    private final Store persistent  = MemoryStore.getInstance();

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

    public List<User> add(User user) {
        if (persistent.findById(user).get(0) == null) {
            return persistent.add(user);
        } else {
            UserServlet.LOG.error(String.format("(ADD) user with id = %4d already exists", user.getId()));
            return null;
        }
    }

    public List<User> update(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.update(user);
        } else {
            UserServlet.LOG.error(String.format("(UPDATE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> delete(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.delete(user);
        } else {
            UserServlet.LOG.error(String.format("(DELETE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findById(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.findById(user);
        } else {
            UserServlet.LOG.error(String.format("(FINDBYID) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findAll(User user) {
        return persistent.findAll(user);
    }
}
