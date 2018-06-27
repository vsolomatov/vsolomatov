package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.UsersMusicTypes;

import java.util.List;

public interface UsersMusicTypesDAO {
        List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes usersMusicTypes);
        List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes usersMusicTypes);
        List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes usersMusicTypes);
        List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes usersMusicTypes);
        List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes usersMusicTypes);
}
