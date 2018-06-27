package com.solomatoff.mvc.model.repository.role;


import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface RoleRepository {
    void addRole(Role role);
    void removeRole(Role role);
    void updateRole(Role role); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
