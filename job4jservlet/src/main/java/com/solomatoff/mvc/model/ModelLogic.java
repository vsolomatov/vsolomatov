package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.User;

import java.util.List;

/**
 * Класс представляет собой слой логики, является частью Model модели mvc
 */
public class ModelLogic {
    // Переменная для объекта слоя Persistant
    private final ModelStore persistent = new DbStore(); // new MemoryStore()

    public List<User> add(User user) {
        if (persistent.findById(user).get(0) == null) {
            return persistent.add(user);
        } else {
            Controller.getInstance().getLog().error(String.format("(ADD) user with id = %4d already exists", user.getId()));
            return null;
        }
    }

    public List<User> update(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.update(user);
        } else {
            Controller.getInstance().getLog().error(String.format("(UPDATE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> delete(User user) {
        if (persistent.findById(user).get(0) != null) {
            return persistent.delete(user);
        } else {
            Controller.getInstance().getLog().error(String.format("(DELETE) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findById(User user) {
        List<User> users = persistent.findById(user);
        if (users.get(0) != null) {
            return users;
        } else {
            Controller.getInstance().getLog().error(String.format("(FINDBYID) user with id = %4d not exists", user.getId()));
            return null;
        }
    }

    public List<User> findAll(User user) {
        return persistent.findAll(user);
    }
}
