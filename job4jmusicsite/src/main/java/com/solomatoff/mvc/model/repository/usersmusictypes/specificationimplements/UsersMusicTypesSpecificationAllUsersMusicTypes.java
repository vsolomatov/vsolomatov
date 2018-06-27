package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

public class UsersMusicTypesSpecificationAllUsersMusicTypes implements SqlSpecification {

    public UsersMusicTypesSpecificationAllUsersMusicTypes() {
    }

    @Override
    public String toSqlClauses() {
        return "userid = userid and musictypeid = musictypeid";
    }

    @Override
    public String toString() {
        return "UsersMusicTypesSpecificationAllUsersMusicTypes{}";
    }
}
