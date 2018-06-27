package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UserRolesSpecificationByUserTest {

    @Test
    public void toSqlClauses() {
        UserRolesSpecificationByUser userRolesSpecificationByUser = new UserRolesSpecificationByUser(1);
        assertThat(userRolesSpecificationByUser.toSqlClauses(), is("userid = 1"));
    }

    @Test
    public void testToString() {
        UserRolesSpecificationByUser userRolesSpecificationByUser = new UserRolesSpecificationByUser(1);
        //System.out.println("userRolesSpecificationByUser = " + userRolesSpecificationByUser);
        assertThat(userRolesSpecificationByUser.toString(), is("UserRolesSpecificationByUser{userId = 1}"));
    }

    @Test
    public void equals() {
        UserRolesSpecificationByUser userRolesSpecificationByUser1 = new UserRolesSpecificationByUser(1);
        UserRolesSpecificationByUser userRolesSpecificationByUser2 = new UserRolesSpecificationByUser(1);
        assertThat(userRolesSpecificationByUser1.equals(userRolesSpecificationByUser2), is(true));

        UserRolesSpecificationByUser userRolesSpecificationByUser3 = new UserRolesSpecificationByUser(2);
        assertThat(userRolesSpecificationByUser1.equals(userRolesSpecificationByUser3), is(false));
    }

    @Test
    public void testHashCode() {
        UserRolesSpecificationByUser userRolesSpecificationByUser1 = new UserRolesSpecificationByUser(1);
        UserRolesSpecificationByUser userRolesSpecificationByUser2 = new UserRolesSpecificationByUser(1);
        assertThat(userRolesSpecificationByUser1.hashCode(), is(userRolesSpecificationByUser2.hashCode()));

        UserRolesSpecificationByUser userRolesSpecificationByUser3 = new UserRolesSpecificationByUser(2);
        assertThat(userRolesSpecificationByUser1.hashCode(), not(userRolesSpecificationByUser3.hashCode()));
    }
}