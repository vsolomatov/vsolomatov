package com.solomatoff.mvc.model.repository.role.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

public class RoleSpecificationAllRoles implements SqlSpecification {

    public RoleSpecificationAllRoles() {
    }

    @Override
    public String toSqlClauses() {
        return "id = id";
    }

    @Override
    public String toString() {
        return "RoleSpecificationAllRoles{}";
    }
}
