package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

public class UserRolesSpecificationAllUserRoles implements SqlSpecification {

    public UserRolesSpecificationAllUserRoles() {
    }

    @Override
    public String toSqlClauses() {
        return "userid = userid and roleid = roleid";
    }

    @Override
    public String toString() {
        return "UserRolesSpecificationAllUserRoles{}";
    }
}
