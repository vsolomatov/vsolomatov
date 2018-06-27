package com.solomatoff.mvc.model.repository.userroles;


import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface UserRolesRepository {
    void addUserRoles(UserRoles userRoles);
    void removeUserRoles(UserRoles userRoles);
    void updateUserRoles(UserRoles userRoles); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
