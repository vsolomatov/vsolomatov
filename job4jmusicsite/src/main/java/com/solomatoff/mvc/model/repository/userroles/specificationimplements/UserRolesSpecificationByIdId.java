package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UserRolesSpecificationByIdId implements SqlSpecification {
    private int userId;
    private int roleId;

    public UserRolesSpecificationByIdId(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("userid = %s and roleid = %s", userId, roleId);
    }

    @Override
    public String toString() {
        return "UserRolesSpecificationByIdId{userId = " + userId + ", roleId = " + roleId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRolesSpecificationByIdId that = (UserRolesSpecificationByIdId) o;
        return userId == that.userId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
