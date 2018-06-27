package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UserRolesSpecificationByRoleTest {

    @Test
    public void toSqlClauses() {
        UserRolesSpecificationByRole userRolesSpecificationByRole = new UserRolesSpecificationByRole(1);
        assertThat(userRolesSpecificationByRole.toSqlClauses(), is("roleid = 1"));
    }

    @Test
    public void testToString() {
        UserRolesSpecificationByRole userRolesSpecificationByRole = new UserRolesSpecificationByRole(1);
        //System.out.println("userRolesSpecificationByRole = " + userRolesSpecificationByRole);
        assertThat(userRolesSpecificationByRole.toString(), is("UserRolesSpecificationByRole{roleId = 1}"));
    }

    @Test
    public void equals() {
        UserRolesSpecificationByRole userRolesSpecificationByRole1 = new UserRolesSpecificationByRole(1);
        UserRolesSpecificationByRole userRolesSpecificationByRole2 = new UserRolesSpecificationByRole(1);
        assertThat(userRolesSpecificationByRole1.equals(userRolesSpecificationByRole2), is(true));

        UserRolesSpecificationByRole userRolesSpecificationByRole3 = new UserRolesSpecificationByRole(2);
        assertThat(userRolesSpecificationByRole1.equals(userRolesSpecificationByRole3), is(false));
    }

    @Test
    public void testHashCode() {
        UserRolesSpecificationByRole userRolesSpecificationByRole1 = new UserRolesSpecificationByRole(1);
        UserRolesSpecificationByRole userRolesSpecificationByRole2 = new UserRolesSpecificationByRole(1);
        assertThat(userRolesSpecificationByRole1.hashCode(), is(userRolesSpecificationByRole2.hashCode()));

        UserRolesSpecificationByRole userRolesSpecificationByRole3 = new UserRolesSpecificationByRole(2);
        assertThat(userRolesSpecificationByRole1.hashCode(), not(userRolesSpecificationByRole3.hashCode()));
    }
}