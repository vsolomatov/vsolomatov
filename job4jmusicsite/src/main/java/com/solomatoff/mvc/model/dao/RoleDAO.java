package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.Role;

import java.util.ArrayList;
import java.util.List;

public interface RoleDAO {
    List<Role> addRole(Role role);
    List<Role> updateRole(Role role);
    List<Role> deleteRole(Role role);
    List<Role> findByIdRole(Role role);
    List<Role> findAllRoles(Role role);
}