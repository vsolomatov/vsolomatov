package com.solomatoff.mvc.model.repository.user.specificationimplements;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.repository.SqlSpecification;

public class UserSpecificationAllUsers implements SqlSpecification {

    public UserSpecificationAllUsers() {
    }

    @Override
    public String toSqlClauses() {
        return "id = id";
    }

    @Override
    public String toString() {
        return "UserSpecificationAllUsers{}";
    }
}
