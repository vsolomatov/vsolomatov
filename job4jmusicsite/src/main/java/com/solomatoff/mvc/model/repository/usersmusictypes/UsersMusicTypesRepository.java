package com.solomatoff.mvc.model.repository.usersmusictypes;


import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface UsersMusicTypesRepository {
    void addUsersMusicTypes(UsersMusicTypes usersMusicTypes);
    void removeUsersMusicTypes(UsersMusicTypes usersMusicTypes);
    void updateUsersMusicTypes(UsersMusicTypes usersMusicTypes); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
