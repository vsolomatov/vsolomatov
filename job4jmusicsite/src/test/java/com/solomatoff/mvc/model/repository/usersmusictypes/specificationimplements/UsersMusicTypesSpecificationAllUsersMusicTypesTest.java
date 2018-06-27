package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationAllUsersMusicTypes;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UsersMusicTypesSpecificationAllUsersMusicTypesTest {

    @Test
    public void toSqlClauses() {
        UsersMusicTypesSpecificationAllUsersMusicTypes userRolesSpecificationAllUsersMusicTypess = new UsersMusicTypesSpecificationAllUsersMusicTypes();
        assertThat(userRolesSpecificationAllUsersMusicTypess.toSqlClauses(), is("userid = userid and musictypeid = musictypeid"));
    }

    @Test
    public void testToString() {
        UsersMusicTypesSpecificationAllUsersMusicTypes userRolesSpecificationAllUsersMusicTypess = new UsersMusicTypesSpecificationAllUsersMusicTypes();
        assertThat(userRolesSpecificationAllUsersMusicTypess.toString(), is("UsersMusicTypesSpecificationAllUsersMusicTypes{}"));
    }
}