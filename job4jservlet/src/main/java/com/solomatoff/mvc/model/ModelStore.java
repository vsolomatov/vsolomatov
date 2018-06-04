package com.solomatoff.mvc.model;

import com.solomatoff.mvc.entities.User;

import java.util.List;

public interface ModelStore {
    List<User> add(User user);
    List<User> update(User user);
    List<User> delete(User user);
    List<User> findById(User user);
    List<User> findAll(User user);
}
