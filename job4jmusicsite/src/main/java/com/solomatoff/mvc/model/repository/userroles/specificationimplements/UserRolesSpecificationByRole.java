package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UserRolesSpecificationByRole implements SqlSpecification {
    private int roleId;

    public UserRolesSpecificationByRole(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("roleid = %s", roleId);
    }

    @Override
    public String toString() {
        return "UserRolesSpecificationByRole{roleId = " + roleId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRolesSpecificationByRole that = (UserRolesSpecificationByRole) o;
        return roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
