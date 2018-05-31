package com.solomatoff.crudservlet;

import java.util.List;

public interface Store {
    List<User> add(User user);
    List<User> update(User user);
    List<User> delete(User user);
    List<User> findById(User user);
    List<User> findAll(User user);
}
