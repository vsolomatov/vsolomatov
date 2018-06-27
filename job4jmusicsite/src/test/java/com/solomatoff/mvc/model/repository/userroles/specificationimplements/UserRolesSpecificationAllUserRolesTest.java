package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationAllUserRoles;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserRolesSpecificationAllUserRolesTest {

    @Test
    public void toSqlClauses() {
        UserRolesSpecificationAllUserRoles userRolesSpecificationAllUserRoless = new UserRolesSpecificationAllUserRoles();
        assertThat(userRolesSpecificationAllUserRoless.toSqlClauses(), is("userid = userid and roleid = roleid"));
    }

    @Test
    public void testToString() {
        UserRolesSpecificationAllUserRoles userRolesSpecificationAllUserRoless = new UserRolesSpecificationAllUserRoles();
        assertThat(userRolesSpecificationAllUserRoless.toString(), is("UserRolesSpecificationAllUserRoles{}"));
    }
}