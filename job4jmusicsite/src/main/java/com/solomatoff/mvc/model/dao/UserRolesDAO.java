package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.UserRoles;

import java.util.ArrayList;
import java.util.List;

public interface UserRolesDAO {
    List<UserRoles> addUserRoles(UserRoles userRoles);
    List<UserRoles> updateUserRoles(UserRoles userRoles);
    List<UserRoles> deleteUserRoles(UserRoles userRoles);
    List<UserRoles> findByIdUserUserRoles(UserRoles userRoles);
    List<UserRoles> findAllUserRoles(UserRoles userRoles);
}