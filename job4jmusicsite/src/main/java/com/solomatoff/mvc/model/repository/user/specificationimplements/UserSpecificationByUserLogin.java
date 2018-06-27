package com.solomatoff.mvc.model.repository.user.specificationimplements;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UserSpecificationByUserLogin implements SqlSpecification {
    private String desiredUserLogin;

    public UserSpecificationByUserLogin(String desiredUserLogin) {
        this.desiredUserLogin = desiredUserLogin;
    }

    @Override
    public String toSqlClauses() {
        return String.format("login = '%s'", desiredUserLogin);
    }

    @Override
    public String toString() {
        return "UserSpecificationByUserLogin{desiredUserLogin = " + desiredUserLogin + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSpecificationByUserLogin that = (UserSpecificationByUserLogin) o;
        return Objects.equals(desiredUserLogin, that.desiredUserLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desiredUserLogin);
    }
}
