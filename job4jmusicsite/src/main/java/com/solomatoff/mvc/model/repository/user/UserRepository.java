package com.solomatoff.mvc.model.repository.user;


import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
