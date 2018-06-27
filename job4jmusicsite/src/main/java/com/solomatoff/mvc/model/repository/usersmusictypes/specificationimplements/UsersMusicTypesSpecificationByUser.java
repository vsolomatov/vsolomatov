package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UsersMusicTypesSpecificationByUser implements SqlSpecification {
    private int userId;
    private int roleId;

    public UsersMusicTypesSpecificationByUser(int userId) {
        this.userId = userId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("userid = %s", userId);
    }

    @Override
    public String toString() {
        return "UsersMusicTypesSpecificationByUser{userId = " + userId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersMusicTypesSpecificationByUser that = (UsersMusicTypesSpecificationByUser) o;
        return userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
