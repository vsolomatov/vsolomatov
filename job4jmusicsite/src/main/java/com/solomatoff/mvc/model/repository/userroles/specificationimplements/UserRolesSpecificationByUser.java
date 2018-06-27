package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UserRolesSpecificationByUser implements SqlSpecification {
    private int userId;

    public UserRolesSpecificationByUser(int userId) {
        this.userId = userId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("userid = %s", userId);
    }

    @Override
    public String toString() {
        return "UserRolesSpecificationByUser{userId = " + userId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRolesSpecificationByUser that = (UserRolesSpecificationByUser) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
