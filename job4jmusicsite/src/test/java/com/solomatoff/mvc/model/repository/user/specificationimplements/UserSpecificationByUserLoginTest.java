package com.solomatoff.mvc.model.repository.user.specificationimplements;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UserSpecificationByUserLoginTest {

    @Test
    public void toSqlClauses() {
        UserSpecificationByUserLogin userSpecificationByUserLogin = new UserSpecificationByUserLogin("login1");
        assertThat(userSpecificationByUserLogin.toSqlClauses(), is("login = 'login1'"));
    }

    @Test
    public void testToString() {
        UserSpecificationByUserLogin userSpecificationByUserLogin = new UserSpecificationByUserLogin("login1");
        System.out.println("userSpecificationByUserLogin = " + userSpecificationByUserLogin.toString());
        assertThat(userSpecificationByUserLogin.toString(), is("UserSpecificationByUserLogin{desiredUserLogin = login1}"));
    }

    @Test
    public void equals() {
        UserSpecificationByUserLogin userSpecificationByUserLogin1 = new UserSpecificationByUserLogin("login1");
        UserSpecificationByUserLogin userSpecificationByUserLogin2 = new UserSpecificationByUserLogin("login1");
        assertThat(userSpecificationByUserLogin1.equals(userSpecificationByUserLogin2), is(true));

        UserSpecificationByUserLogin userSpecificationByUserLogin3 = new UserSpecificationByUserLogin("login2");
        assertThat(userSpecificationByUserLogin1.equals(userSpecificationByUserLogin3), is(false));
    }

    @Test
    public void testHashCode() {
        UserSpecificationByUserLogin userSpecificationByUserLogin1 = new UserSpecificationByUserLogin("login1");
        UserSpecificationByUserLogin userSpecificationByUserLogin2 = new UserSpecificationByUserLogin("login1");
        assertThat(userSpecificationByUserLogin1.hashCode(), is(userSpecificationByUserLogin2.hashCode()));

        UserSpecificationByUserLogin userSpecificationByUserLogin3 = new UserSpecificationByUserLogin("login2");
        assertThat(userSpecificationByUserLogin1.hashCode(), not(userSpecificationByUserLogin3.hashCode()));
    }
}