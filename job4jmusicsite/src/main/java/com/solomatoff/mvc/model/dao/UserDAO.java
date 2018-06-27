package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    List<User> addUser(User user);
    List<User> updateUser(User user);
    List<User> deleteUser(User user);
    List<User> findByIdUser(User user);
    List<User> findAllUsers(User user);
}